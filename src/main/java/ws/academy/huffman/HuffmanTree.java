package ws.academy.huffman;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {
    public class Node implements Comparable<Node> {
        int frequency;
        char character;
        Node left, rigth;

        Node(int frequency, char character) {
            this.frequency = frequency;
            this.character = character;
        }

        Node(int frequency, Node left, Node rigth) {
            this.frequency = frequency;
            this.left = left;
            this.rigth = rigth;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.frequency, o.frequency);
        }
    }

    private Node root;
    private Map<Character, String> huffmanCode = new HashMap<>();

    public HuffmanTree(Map<Character, Integer> frequencyMap) {
        root = buildHuffmanTree(frequencyMap);
        buildHuffmanCode(root, "");
    }

    private Node buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.offer(new Node(entry.getValue(), entry.getKey()));
        }

        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            priorityQueue.offer(new Node(left.frequency + right.frequency, left, right));
        }

        return priorityQueue.poll();
    }

    private void buildHuffmanCode(Node node, String code) {
        if (node == null) return;

        if (node.left == null && node.rigth == null) {
            huffmanCode.put(node.character, code);
        }

        buildHuffmanCode(node.left, code + "0");
        buildHuffmanCode(node.rigth, code + "1");
    }

    public Map<Character, String> getHuffmanCode() {
        return huffmanCode;
    }

    public Node getRoot() {
        return root;
    }
}
