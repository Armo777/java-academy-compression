package ws.academy.huffman;

import ws.academy.Archiver;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HuffmanArchiver implements Archiver {
    @Override
    public void compress(Reader source, Writer destination) {
        try (BufferedReader reader = new BufferedReader(source);
             BufferedWriter writer = new BufferedWriter(destination)){

            StringBuilder text = new StringBuilder();
            int ch;
            while ((ch = reader.read()) != -1) {
                text.append((char) ch);
            }

            HuffmanTree tree = new HuffmanTree(buildFrequencyMap(text.toString()));
            HuffmanEncoder encoder = new HuffmanEncoder(tree.getHuffmanCode());
            encoder.encode(text.toString(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<Character, Integer> buildFrequencyMap(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        return frequencyMap;
    }
}
