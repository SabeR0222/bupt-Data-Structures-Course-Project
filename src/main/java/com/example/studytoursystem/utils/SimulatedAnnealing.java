package com.example.studytoursystem.utils;

import java.util.Arrays;
import java.util.Random;

public class SimulatedAnnealing {

    private static final double INITIAL_TEMPERATURE = 1000; // 初始温度
    private static final double COOLING_RATE = 0.99; // 冷却率
    private static final Random random = new Random();

    public static int[] simulatedAnnealing(int[][] distanceMatrix) {
        int cityCount = distanceMatrix.length;
        int[] currentSolution = generateInitialSolution(cityCount);
        int currentCost = calculateTotalDistance(distanceMatrix, currentSolution);
        int bestSolution[] = Arrays.copyOf(currentSolution, cityCount);
        int bestCost = currentCost;
        double temperature = INITIAL_TEMPERATURE;

        while (temperature > 1e-6) { // 温度足够低时停止
            int[] neighbour = generateNeighbourWithFixedStart(currentSolution);
            int neighbourCost = calculateTotalDistance(distanceMatrix, neighbour);

            // 计算接受邻居的概率
            double acceptanceProbability = acceptanceProbability(currentCost, neighbourCost, temperature);

            if (acceptanceProbability > Math.random()) {
                currentSolution = neighbour;
                currentCost = neighbourCost;
            }

            // 更新最佳解
            if (currentCost < bestCost) {
                bestCost = currentCost;
                bestSolution = Arrays.copyOf(currentSolution, cityCount);
            }

            // 降低温度
            temperature *= COOLING_RATE;
        }

        return bestSolution;
    }

    private static int[] generateInitialSolution(int cityCount) {
        int[] solution = new int[cityCount];
        for (int i = 0; i < cityCount; i++) {
            solution[i] = i;
        }
        shuffleArray(solution, 1, cityCount); // 从第二个元素开始随机化
        return solution;
    }

    private static void shuffleArray(int[] array, int start, int end) {
        for (int i = start; i < end; i++) {
            int index = start + random.nextInt(end - start);
            // Simple swap
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    private static int calculateTotalDistance(int[][] distanceMatrix, int[] solution) {
        int totalDistance = distanceMatrix[solution[solution.length - 1]][solution[0]]; // 从最后回到起始点
        for (int i = 0; i < solution.length - 1; i++) {
            totalDistance += distanceMatrix[solution[i]][solution[i + 1]];
        }
        return totalDistance;
    }

    private static int[] generateNeighbourWithFixedStart(int[] currentSolution) {
        int[] neighbour = Arrays.copyOf(currentSolution, currentSolution.length);
        int i = random.nextInt(neighbour.length - 1) + 1; // 避免交换起始点
        int j = random.nextInt(neighbour.length - 1) + 1;
        // Swap cities (excluding the first one)
        int temp = neighbour[i];
        neighbour[i] = neighbour[j];
        neighbour[j] = temp;
        return neighbour;
    }

    private static double acceptanceProbability(int currentCost, int newCost, double temperature) {
        if (newCost < currentCost) {
            return 1.0;
        }
        return Math.exp((currentCost - newCost) / temperature);
    }
}