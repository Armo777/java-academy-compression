package ws.academy.huffman;

public class HuffmanDecoder {
    private final HuffmanTree tree;

    public HuffmanDecoder(HuffmanTree tree) {
        this.tree = tree;
    }

    public String decode(String encoded) {
        StringBuilder decoded = new StringBuilder();
        HuffmanTree node = tree;

        for (int i = 0; i < encoded.length(); i++) {
            node = encoded.charAt(i) == '0' ? node.left : node.right;
            if (node.content != null) {
                decoded.append(node.content);
                node = tree;
            }
        }
        return decoded.toString();
    }
}
