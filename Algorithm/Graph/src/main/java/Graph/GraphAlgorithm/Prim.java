package Graph.GraphAlgorithm;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import Graph.Edge;
import Graph.Graph;
import Graph.Node;

public class Prim {
    
    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }

    }

    public static Set<Edge> primByMySelf(Graph graph){
        if(graph == null){
            return null;
        }
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        Set<Node> currentNodeSet = new HashSet<>();
        Set<Edge> resultSet = new HashSet<>();
        // 任意获取图中某个节点为第一个解锁的节点:获取节点1
        Node nodePointer = graph.nodes.get(1);
        currentNodeSet.add(nodePointer);
        for (Edge edge : nodePointer.edges) {
            if(edge.from == nodePointer){
                priorityQueue.offer(edge);
            }
        }
        while(currentNodeSet.size() != graph.nodes.size()){
            Edge minWeightEdge = priorityQueue.poll();
            nodePointer = minWeightEdge.to;
            if(!currentNodeSet.contains(nodePointer)){
                currentNodeSet.add(nodePointer);
                resultSet.add(minWeightEdge);
                for (Edge edge : nodePointer.edges) {
                    if (edge.from == nodePointer) {
                        priorityQueue.offer(edge);
                    }
                }
            }
        }
        return resultSet;
    }

    // 测试左神的代码有问题
    public static Set<Edge> primMST(Graph graph) {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        HashSet<Node> set = new HashSet<>();
        Set<Edge> result = new HashSet<>();

        // 这个最外层的for循环是考虑到 这张图不一定是一张连通图,有可能是一个森林,由几块不连通的图组成
        // 那么加上这个for循环就能保证 生成森林中所有图的最小生成树
        for (Node node : graph.nodes.values()) {
            // 如果set中没有node节点,则表示该节点还未加入最小生成树
            if (!set.contains(node)) {
                set.add(node);
                // 把解锁的该节点的所有边加入优先级队列中
                for (Edge edge : node.edges) {
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()) {
                    // 从优先级队列弹出最小权值的边
                    Edge edge = priorityQueue.poll();
                    Node toNode = edge.to;
                    // 如果toNode不再set中,重新解锁toNode解锁的边,加入优先级队列中
                    if (!set.contains(toNode)) {
                        set.add(toNode);
                        result.add(edge);
                        for (Edge nextEdge : node.edges) {
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
        }
        return result;
    }

}
