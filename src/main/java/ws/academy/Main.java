package ws.academy;

import ws.academy.huffman.HuffmanArchiver;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Settings settings = new Settings(args);

        if (args.length == 0) {
            System.out.println("Введите команды для сжатия или распаковки. Пример: -c -input <путь_к_файлу> -output <путь_к_выходному_файлу>");
        } else {
            System.out.println("Аргументы командной строки: " + String.join(" ", args));
        }

        Archiver archiver = new HuffmanArchiver(settings);

        processOperation(archiver, settings, args);

        while (true) {
            System.out.println("Введите команды (или 'exit' для выхода):");
            String in = scanner.nextLine();
            if (in.equalsIgnoreCase("exit")) {
                break;
            }

            String[] commandArgs = in.split(" ");
            settings = new Settings(commandArgs);

            System.out.println("Аргументы командной строки: " + Arrays.toString(commandArgs));
            archiver = new HuffmanArchiver(settings);

            processOperation(archiver, settings, args);

            //System.out.println(Arrays.toString(commandArgs));
        }
    }

    private static void processOperation(Archiver archiver, Settings settings, String[] args) {
        if (settings.isCompressOperation()) {
            try (Reader input = new FileReader(settings.getInputFilePath());
                 Writer output = new FileWriter(settings.getOutputFilePath())) {
                archiver.compress(input, output);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (settings.isExtractOperation()) {
            try (Reader input = new FileReader(settings.getInputFilePath());
                 Writer output = new PrintWriter(System.out)) {
                archiver.extract(input, output);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /*public static Archiver getArchiver() {
        return new HuffmanArchiver();
    }*/
}
