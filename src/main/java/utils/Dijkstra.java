package utils;

import com.example.studytoursystem.model.EdgeNode;
import com.example.studytoursystem.model.Graph;

import java.util.*;

public class Dijkstra {
    private final Graph graph;
    private final int srcId;
    private final int destId;
    private final int strategy;
    private final PriorityQueue<EdgeNode> pq; // 优先队列用于存放待访问的节点
    private final Map<Integer, Integer> distTo; // 存储源节点到各节点的最短距离
    private final Map<Integer, EdgeNode> edgeFrom; // 记录到达每个节点的最短路径上的前一个节点
    public Dijkstra(Graph graph, int srcId, int destId, int strategy) {
        this.graph = graph;
        this.srcId = srcId;
        this.destId = destId;
        this.strategy = strategy;
        this.pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.getWeightByStrategy(strategy)));
        this.distTo = new HashMap<>();
        this.edgeFrom = new HashMap<>();
    }

    public int[] dijkstra() {
        // 初始化
        for (int id : graph.getAdjList().keySet()) {
            distTo.put(id, Integer.MAX_VALUE);
        }
        distTo.put(srcId, 0);

        // 将源节点加入优先队列
        pq.offer(new EdgeNode(srcId, 0, 0f, true));

        while (!pq.isEmpty()) {
            EdgeNode currNode = pq.poll();
            if (currNode.getDestId() == destId) break; // 已找到目标节点，结束循环

            for (EdgeNode neighbor : graph.getAdjList().getOrDefault(currNode.getDestId(), Collections.emptyList())) {
                int alt = distTo.get(currNode.getDestId()) + neighbor.getWeightByStrategy(strategy);
                if (alt < distTo.get(neighbor.getDestId())) {
                    // 更新最短路径信息
                    distTo.put(neighbor.getDestId(), alt);
                    edgeFrom.put(neighbor.getDestId(), currNode);
                    pq.offer(new EdgeNode(neighbor.getDestId(), alt, neighbor.getCrowding(), neighbor.isRideable()));
                }
            }
        }

        // 构建并返回最短路径
        return buildPath();
    }

    private int[] buildPath() {
        if (!edgeFrom.containsKey(destId)) return null; // 没有路径到达目标节点
        Stack<Integer> path = new Stack<>();
        int currentNode = destId;
        while (currentNode != srcId) {
            path.push(currentNode);
            currentNode = edgeFrom.get(currentNode).getDestId();
        }
        path.push(srcId); // 添加源节点
        int[] result = new int[path.size()];
        int index = 0;
        while (!path.isEmpty()) {
            result[index++] = path.pop();
        }
        return result;
    }
}