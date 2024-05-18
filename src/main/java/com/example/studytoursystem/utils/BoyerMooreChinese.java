package com.example.studytoursystem.utils;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
public class BoyerMooreChinese {
    private final int R = 65536;   // Unicode字符集大小
    private int[] right;           // 坏字符规则数组
    private String pattern;        // 存储模式串

    public BoyerMooreChinese(String pattern) {
        this.pattern = pattern;

        // 计算坏字符规则数组
        right = new int[R];
        Arrays.fill(right, -1);
        for (int i = 0; i < pattern.length(); i++)
            right[pattern.charAt(i)] = i;
    }

    // 返回模式串在文本中第一次出现的位置，如果没有匹配成功，则返回文本长度
    public int search(String txt) {
        int m = pattern.length();
        int n = txt.length();
        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m - 1; j >= 0; j--) {
                if (pattern.charAt(j) != txt.charAt(i + j)) {
                    skip = Math.max(1, j - right[txt.charAt(i + j)]);
                    break;
                }
            }
            if (skip == 0) return i;    // 匹配成功
        }
        return n;                       // 未匹配成功
    }
}

