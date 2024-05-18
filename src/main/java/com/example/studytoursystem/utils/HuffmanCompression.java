package com.example.studytoursystem.utils;

import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanCompression {

    public static HashMap<Character, String> generateHuffmanCodes(String text) {
        HashMap<Character, String> codes = new HashMap<>();
        if (text == null || text.length() == 0)
            return codes;

        HashMap<Character, Integer> frequencies = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (char c : frequencies.keySet()) {
            pq.add(new HuffmanNode(c, frequencies.get(c)));
        }

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();

            HuffmanNode merged = new HuffmanNode('\0', left.frequency + right.frequency);
            merged.left = left;
            merged.right = right;

            pq.add(merged);
        }

        if (pq.size() == 1) {
            generateCodes(pq.peek(), "", codes);
        }

        return codes;
    }

    private static void generateCodes(HuffmanNode root, String code, HashMap<Character, String> codes) {
        if (root == null)
            return;

        if (root.data != '\0') {
            codes.put(root.data, code);
        }

        generateCodes(root.left, code + "0", codes);
        generateCodes(root.right, code + "1", codes);
    }

    public static String compress(String text, HashMap<Character, String> codes) {
        StringBuilder compressed = new StringBuilder();
        for (char c : text.toCharArray()) {
            compressed.append(codes.get(c));
        }
        return compressed.toString();
    }
}