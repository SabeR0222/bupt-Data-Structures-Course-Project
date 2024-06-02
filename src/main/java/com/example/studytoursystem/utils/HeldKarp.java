package com.example.studytoursystem.utils;

import java.util.Arrays;

public class HeldKarp {

    // 计算从源节点到所有其他节点的最短路径及路径信息
    public static int[] heldKarp(int[][] graph) {
        int n = graph.length;
        int[][] dp = new int[1 << n][n];
        int[][] parent = new int[1 << n][n]; // 用于记录前驱节点
        int INF = Integer.MAX_VALUE;
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        for (int[] row : parent) {
            Arrays.fill(row, -1); // 初始设置前驱为-1表示无前驱
        }

        // 初始化
        for (int i = 0; i < n; i++) {
            dp[(1 << i)][i] = graph[0][i];
            parent[(1 << i)][i] = 0; // 源点到自身的前驱是自己
        }

        // 动态规划构建状态表
        for (int mask = 2; mask < (1 << n); mask++) {
            for (int last = 0; last < n; last++) {
                if ((mask & (1 << last)) != 0) {
                    for (int k = 0; k < n; k++) {
                        if (k != last && (mask & (1 << k)) != 0) {
                            int cost = dp[mask ^ (1 << last)][k] + graph[k][last];
                            if (cost < dp[mask][last]) {
                                dp[mask][last] = cost;
                                parent[mask][last] = k; // 更新前驱节点
                            }
                        }
                    }
                }
            }
        }

        // 寻找最短路径的总成本
        int minTourCost = INF;
        int endCity = 0;
        for (int i = 0; i < n; i++) {
            if (dp[(1 << n) - 1][i] + graph[i][0] < minTourCost) {
                minTourCost = dp[(1 << n) - 1][i] + graph[i][0];
                endCity = i; // 记录最后一个城市的索引
            }
        }

        // 回溯得到最短路径
        int[] path = new int[n + 1]; // 需要额外一个位置存储源点（0）
        int index = n; // 用于记录路径数组中的当前位置
        int mask = (1 << n) - 1;
        while (endCity != -1) {
            path[index--] = endCity; // 存储当前城市
            int prevCity = parent[mask][endCity];
            if (prevCity == 0) { // 已经回到源点
                path[index] = 0; // 存储源点
                break;
            }
            mask ^= (1 << endCity); // 移除当前城市
            endCity = prevCity;
        }

        return path; // 返回最短路径的整数数组表
    }
}