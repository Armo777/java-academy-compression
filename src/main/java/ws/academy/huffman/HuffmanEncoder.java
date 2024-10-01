package ws.academy.huffman;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;

public class HuffmanEncoder {
    private final Map<Character, String> huffmanCode;

    public HuffmanEncoder(Map<Character, String> huffmanCode) {
        this.huffmanCode = huffmanCode;
    }

    public void encode(String text, OutputStream destination) throws IOException {
        BitOutputStream bitOut = new BitOutputStream(destination);
        for (char c : text.toCharArray()) {
            String code = huffmanCode.get(c);
            for (char bit : code.toCharArray()) {
                bitOut.writeBit(bit == '1' ? 1 : 0);
            }
        }
        bitOut.close();
    }
}
