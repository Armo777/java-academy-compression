package ws.academy.huffman;

import ws.academy.Archiver;
import ws.academy.Settings;

import java.io.*;
import java.util.*;

public class HuffmanArchiver implements Archiver {
    private Settings settings;

    public HuffmanArchiver(Settings settings) {
        this.settings = settings;
    }

    @Override
    public void compress(Reader source, Writer destination) {
        try {
            BufferedReader reader = new BufferedReader(source);
            StringBuilder content = new StringBuilder();

            int c;
            while ((c = reader.read()) != -1) {
                content.append((char) c);
            }

            TreeMap<Character, Integer> frequencies = countFrequency(content.toString());
            HuffmanTreeBuilder builder = new HuffmanTreeBuilder();
            HuffmanTree tree = builder.buildTree(createNodes(frequencies));

            HuffmanEncoder encoder = new HuffmanEncoder(tree);
            String encoded = encoder.encode(content.toString());

            // Преобразуем строковое представление битов в BitArray
            BitArray bitArray = new BitArray(encoded.length());
            for (int i = 0; i < encoded.length(); i++) {
                bitArray.set(i, encoded.charAt(i) == '1' ? 1 : 0);
            }

            //BufferedWriter writer = new BufferedWriter(destination);
            destination.write(new String(bitArray.getBytes()));
            //writer.write(encoded);
            destination.flush();

            String outputPath = settings.getOutputFilePath();
            saveToFile(outputPath, frequencies, bitArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void extract(Reader source, Writer destination) {
        try {
            TreeMap<Character, Integer> frequencies = new TreeMap<>();
            StringBuilder encoded = new StringBuilder();

            String inputPath = settings.getInputFilePath();
            loadFromFile(inputPath, frequencies, encoded);

            HuffmanTreeBuilder builder = new HuffmanTreeBuilder();
            HuffmanTree tree = builder.buildTree(createNodes(frequencies));

            HuffmanDecoder decoder = new HuffmanDecoder(tree);
            String decoded = decoder.decode(encoded.toString());

            destination.write(decoded);
            destination.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadFromFile(String inputFile, Map<Character, Integer> frequencies, StringBuilder encoded) {
        try (DataInputStream is = new DataInputStream(new FileInputStream(inputFile))) {
            // Чтение частот символов
            int frequencySize = is.readInt();
            for (int i = 0; i < frequencySize; i++) {
                char character = is.readChar();
                int frequency = is.readInt();
                frequencies.put(character, frequency);
            }

            // Чтение сжатых данных (битового массива)
            int bitArraySize = is.readInt();
            byte[] compressedData = new byte[bitArraySize / 8 + (bitArraySize % 8 == 0 ? 0 : 1)];
            is.readFully(compressedData);

            // Преобразуем байты обратно в строковое представление битов
            BitArray bitArray = new BitArray(bitArraySize);
            bitArray.fromBytes(compressedData);
            for (int i = 0; i < bitArraySize; i++) {
                encoded.append(bitArray.get(i) == 1 ? '1' : '0');
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла: " + inputFile, e);
        }
    }

    private TreeMap<Character, Integer> countFrequency(String content) {
        TreeMap<Character, Integer> frequencyMap = new TreeMap<>();
        for (char c : content.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        return frequencyMap;
    }

    private ArrayList<HuffmanTree> createNodes(TreeMap<Character, Integer> frequencies) {
        ArrayList<HuffmanTree> nodes = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            nodes.add(new HuffmanTree(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    private void saveToFile(String output, Map<Character, Integer> frequencies, BitArray bitArray) {
        try (DataOutputStream os = new DataOutputStream(new FileOutputStream(output))) {
            os.writeInt(frequencies.size());
            for (Character character : frequencies.keySet()) {
                os.writeChar(character);
                os.writeInt(frequencies.get(character));
            }

            os.writeInt(bitArray.getSize());
            os.write(bitArray.getBytes(), 0, bitArray.getSizeInBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
