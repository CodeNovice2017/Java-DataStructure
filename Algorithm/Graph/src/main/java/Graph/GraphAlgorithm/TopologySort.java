package Graph.GraphAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Graph.Graph;
import Graph.Node;

public class TopologySort {
    // directed graph and no loop
    // 必须是有向图并且图中没有环路
    public static List<Node> sortedTopology(Graph graph) {
        if(graph == null){
            return null;
        }
        HashMap<Node,Integer> inMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        // 遍历该图所有的点,所有的点都加入inMap中,并且把每个点在原本图中的入度登记在inMap中
        for(Node temp : graph.nodes.values()){
            inMap.put(temp, temp.in);
            if(temp.in == 0){
                queue.offer(temp);
            }
        }
        List<Node> resultList = new ArrayList<>();
        while(!queue.isEmpty()){
            Node tempNode = queue.poll();
            resultList.add(tempNode);
            for (Node temp : tempNode.nexts) {
                inMap.put(temp,inMap.get(temp) - 1);
                if(inMap.get(temp) == 0){
                    queue.offer(temp);
                }        
            }
        }
        return resultList;
    }
}
