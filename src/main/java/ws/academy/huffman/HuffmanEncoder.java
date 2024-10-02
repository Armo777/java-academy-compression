package ws.academy.huffman;

import java.util.Map;
import java.util.TreeMap;

public class HuffmanEncoder {
    private final HuffmanTree tree;
    private final Map<Character, String> codes = new TreeMap<>();

    public HuffmanEncoder(HuffmanTree tree) {
        this.tree = tree;
        generateCodes(tree, "");
    }

    private void generateCodes(HuffmanTree node, String code) {
        if (node.content != null) {
            codes.put(node.content, code);
        } else {
            generateCodes(node.left, code + "0");
            generateCodes(node.right, code + "1");
        }
    }

    public String encode(String text) {
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            encoded.append(codes.get(text.charAt(i)));
        }
        return encoded.toString();
    }

    public Map<Character, String> getCodes() {
        return codes;
    }
}
