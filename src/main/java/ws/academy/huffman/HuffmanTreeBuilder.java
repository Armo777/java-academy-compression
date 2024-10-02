package ws.academy.huffman;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTreeBuilder {
    public HuffmanTree buildTree(ArrayList<HuffmanTree> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            HuffmanTree left = nodes.remove(nodes.size() - 1);
            HuffmanTree right = nodes.remove(nodes.size() - 1);

            HuffmanTree parent = new HuffmanTree(null, left.weight + right.weight, left, right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}
