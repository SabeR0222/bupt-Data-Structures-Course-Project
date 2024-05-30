package com.example.studytoursystem.utils;

import com.example.studytoursystem.model.graph.Edge;
import com.example.studytoursystem.model.graph.Graph;

import java.util.*;

public class Dijkstra {
    public static Map<String, Object> dijkstra(Graph graph, int srcId, int destId, int strategy) {
        int nodeCount = graph.getNodes().size();
        int[] distance = new int[nodeCount];
        int[] predecessor = new int[nodeCount];

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(predecessor, -1); // 使用-1表示无前驱

        distance[srcId] = 0;

        MyPriorityQueue<NodeDistance> pq = new MyPriorityQueue<>(Comparator.comparingInt(n -> n.distance));
        pq.offer(new NodeDistance(srcId, 0));

        boolean foundPath = false;
        while (!pq.isEmpty()) {
            NodeDistance currentNode = pq.poll();
            int nodeId = currentNode.id;

            if (nodeId == destId) {
                foundPath = true;
                break;
            }

            for (Edge edge : graph.getNodes().get(nodeId).getEdges()) {
                int newDistance = distance[nodeId] + edge.getWeightByStrategy(strategy);
                if (newDistance < distance[edge.getDestId()]) {
                    distance[edge.getDestId()] = newDistance;
                    predecessor[edge.getDestId()] = nodeId;
                    pq.offer(new NodeDistance(edge.getDestId(), newDistance));
                }
            }
        }
        // 构建结果Map
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("distance", foundPath ? distance[destId] : Integer.MAX_VALUE);
        resultMap.put("path", foundPath ? getPath(predecessor, srcId, destId) : null);

        return resultMap;
    }

    private static int[] getPath(int[] predecessor, int srcId, int destId) {
        if (predecessor[destId] == -1) {
            // 如果没有路径存在，可以抛出异常或返回一个特定值/空数组
            return new int[0]; // 或者根据需要处理这种情况
        }

        List<Integer> pathList = new ArrayList<>();
        int current = destId;
        while (current != -1) {
            pathList.add(current);
            current = predecessor[current];
        }
        Collections.reverse(pathList);

        // 将List转换为数组并返回
        int[] pathArray = new int[pathList.size()];
        for (int i = 0; i < pathList.size(); i++) {
            pathArray[i] = pathList.get(i);
        }
        return pathArray;
    }

    static class NodeDistance {
        int id;
        int distance;

        NodeDistance(int id, int distance) {
            this.id = id;
            this.distance = distance;
        }
    }
}
