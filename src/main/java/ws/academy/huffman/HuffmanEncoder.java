package ws.academy.huffman;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class HuffmanEncoder {
    private final Map<Character, String> huffmanCode;

    public HuffmanEncoder(Map<Character, String> huffmanCode) {
        this.huffmanCode = huffmanCode;
    }

    public void encode(String text, Writer destination) throws IOException {
        for (char c : text.toCharArray()) {
            destination.write(huffmanCode.get(c));
        }
    }
}
