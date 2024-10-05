package com.example.HuffmanArchiverTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ws.academy.Settings;
import ws.academy.huffman.BitArray;
import ws.academy.huffman.HuffmanArchiver;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HuffmanArchiverTest {
    private HuffmanArchiver archiver;
    private Settings settings;


    @BeforeEach
    public void setUp() {
        String[] args = {"-c", "-input", "docs/romeo-and-juliet_Shakespeare.txt", "-output", "docs/compressed_file.bin"};
        settings = new Settings(args);
        archiver = new HuffmanArchiver(settings);
    }

    @Test
    public void testCompress() throws IOException {
        try (FileReader inputFile = new FileReader(settings.getInputFilePath())) {
            // Используем ByteArrayOutputStream для захвата вывода
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            OutputStreamWriter outputWriter = new OutputStreamWriter(byteArrayOutputStream);

            // Выполняем сжатие
            archiver.compress(inputFile, outputWriter);

            // Закрываем writer для получения данных
            outputWriter.close();

            long originalLength = new File(settings.getInputFilePath()).length();

            // Получаем закодированные данные из BitArray
            BitArray bitArray = new BitArray(byteArrayOutputStream.toByteArray().length * 8, byteArrayOutputStream.toByteArray());
            long compressedLength = bitArray.getSizeInBytes(); // Получаем размер в байтах

            System.out.println("Оригинальный текст: " + originalLength);
            System.out.println("Сжатый текст (в байтах): " + compressedLength);

            // Проверяем, что сжатый текст короче оригинального
            assertTrue(compressedLength < originalLength, "Сжатый текст должен быть короче.");
        }
    }
}
