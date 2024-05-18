package com.example.studytoursystem.utils;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoyerMooreChinese {
    private final int R = 65536;   // Unicode字符集大小
    private int[] right;           // 坏字符规则数组
    private int[] suffix;          // 好后缀规则数组
    private boolean[] prefix;      // 好后缀前缀匹配数组
    private String pattern;        // 存储模式串

    public BoyerMooreChinese(String pattern) {
        this.pattern = pattern;

        // 计算坏字符规则数组
        right = new int[R];
        Arrays.fill(right, -1);
        for (int i = 0; i < pattern.length(); i++)
            right[pattern.charAt(i)] = i;

        // 初始化好后缀规则数组
        suffix = new int[pattern.length()];
        prefix = new boolean[pattern.length()];
        Arrays.fill(suffix, -1);
        computeSuffix(pattern, suffix, prefix);
    }

    // 计算好后缀规则数组
    private void computeSuffix(String pattern, int[] suffix, boolean[] prefix) {
        int m = pattern.length();
        for (int i = 0; i < m - 1; i++) {
            int j = i;
            int k = 0;
            while (j >= 0 && pattern.charAt(j) == pattern.charAt(m - 1 - k)) {
                j--;
                k++;
                suffix[k] = j + 1;
            }
            if (j == -1) prefix[k] = true;
        }
    }

    // 返回模式串在文本中第一次出现的位置，如果没有匹配成功，则返回文本长度

    // 返回文本中所有模式串出现的位置
    public List<Integer> searchAll(String txt) {
        List<Integer> positions = new ArrayList<>();
        int m = pattern.length();
        int n = txt.length();
        int i = 0;
        while (i <= n - m) {
            int j = m - 1;
            while (j >= 0 && pattern.charAt(j) == txt.charAt(i + j)) {
                j--;
            }
            if (j < 0) {
                positions.add(i);
                i += (i + m < n) ? m - suffix[0] : 1;
            } else {
                i += Math.max(1, j - right[txt.charAt(i + j)]);
            }
        }
        return positions;
    }
}