package com.example.studytoursystem.utils;

import java.util.HashMap;
import java.util.Map;

public class HuffmanDecompression {
    public static String decompress(String compressed, HashMap<Character, String> codes) {
        StringBuilder originalText = new StringBuilder();
        int index = 0;
        while (index < compressed.length()) {
            char currentChar = '\0';
            for (Map.Entry<Character, String> entry : codes.entrySet()) {
                if (compressed.startsWith(entry.getValue(), index)) {
                    currentChar = entry.getKey();
                    index += entry.getValue().length();
                    break;
                }
            }
            originalText.append(currentChar);
        }

        return originalText.toString();
    }
}
