package ws.academy.huffman;

import java.util.*;

public class HuffmanTree implements Comparable<HuffmanTree> {
    Character content;
    int weight;
    HuffmanTree left;
    HuffmanTree right;

    public HuffmanTree(Character content, int weight) {
        this.content = content;
        this.weight = weight;
    }

    public HuffmanTree(Character content, int weight, HuffmanTree left, HuffmanTree right) {
        this.content = content;
        this.weight = weight;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanTree o) {
        return o.weight - weight;
    }
}
