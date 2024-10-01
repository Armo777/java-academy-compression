package com.example.HuffmanArchiverTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ws.academy.huffman.HuffmanArchiver;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HuffmanArchiverTest {
    private HuffmanArchiver archiver;

    @BeforeEach
    public void setUp() {
        archiver = new HuffmanArchiver();
    }

    @Test
    public void testCompress() throws IOException {
        FileReader inputFile = new FileReader("docs/romeo-and-juliet_Shakespeare.txt");
        //FileReader fileReader = new FileReader(inputFile);
        //StringWriter outputWriter = new StringWriter();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStreamWriter outputWriter = new OutputStreamWriter(byteArrayOutputStream);

        HuffmanArchiver archiver = new HuffmanArchiver();
        archiver.compress(inputFile, outputWriter);

        File file = new File("docs/romeo-and-juliet_Shakespeare.txt");
        long originalLength = file.length();

        outputWriter.close();

        long compressedLength = byteArrayOutputStream.toByteArray().length;

        System.out.println("Оригинальный текст: " + originalLength);
        System.out.println("Сжатый текст: " + compressedLength);

        assertTrue(compressedLength < originalLength, "Сжатый текст должен быть короче.");
    }
}
