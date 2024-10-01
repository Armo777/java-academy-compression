package ws.academy.huffman;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

public class HuffmanDecoder {
    private final HuffmanTree.Node root;

    public HuffmanDecoder(HuffmanTree.Node root) {
        this.root = root;
    }

    public void decode(InputStream source, Writer destination) throws IOException {
        BitInputStream bitIn = new BitInputStream(source);
        HuffmanTree.Node currentNode = root;

        int bit;
        while ((bit = bitIn.readBit()) != -1) {
            currentNode = (bit == 0) ? currentNode.left : currentNode.right;

            if (currentNode.left == null && currentNode.right == null) {
                destination.write(currentNode.character);
                currentNode = root;
            }
        }
        bitIn.close();
    }
}
