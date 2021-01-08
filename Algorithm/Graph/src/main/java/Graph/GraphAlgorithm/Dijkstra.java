package Graph.GraphAlgorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import Graph.Edge;
import Graph.Node;

public class Dijkstra {
    
    public static HashMap<Node, Integer> dijkstra1(Node head){
        // 返回的结果Map,本身维护的是从head节点到该key的Node节点的最短距离
        HashMap<Node,Integer> distanceMap = new HashMap<>();
        // selectedSet不是判断是否已经加入distanceMap,而是代表该节点是否已经解锁
        // 比如最开始head节点是先加入distanceMap,传入head到head本身的距离0
        // 此时并不将head节点加入selectedMap,因为此时head还没有解锁,
        // 解锁的含义我理解是要遍历该节点所相邻的所有节点,因为此时起点到该节点的距离是当前时刻最短的
        // 而我们要做的就是判断从起点到该点的距离加上该点和相邻点的距离,和之前维护在distanceMap中起点通过其他路径到和该点相邻节点距离中较小的值
        // 所以此时相当于还没有判断(head到相邻的节点的距离加上head到head的距离)与(之前distanceMap中,head到该head相邻点的距离(之前distanceMap中没有数据呢,所以distance在getMinDistanceAndUnselectedNode函数中都是Integer.MAX_VALUE))
        // 所以不能在此时将head放入selectedSet
        HashSet<Node> selectedSet = new HashSet<>();
        distanceMap.put(head, 0);
        // 就相当于更新以这个被找出的最短路径节点为中转的相邻节点到起点的距离和原本相邻节点之前到起点距离的更小值之后,才相当于把这个节点解锁了
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedSet);
        while(minNode != null){
            for (Edge tempEdge : minNode.edges) {
                Node toTempNode = tempEdge.to;
                if (!selectedSet.contains(toTempNode)) {
                    distanceMap.put(toTempNode, distanceMap.get(minNode) + tempEdge.weight);
                }
                distanceMap.put(toTempNode,
                        Math.min(distanceMap.get(toTempNode), distanceMap.get(minNode) + tempEdge.weight));
            }
            selectedSet.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedSet);
        }
        return distanceMap;
    }

    // 找出当前distanceMap中起点到其他节点(并且该节点之前并未作为中转点解锁过)中的最短路径的那个终点节点
    public static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> touchedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for(Entry<Node,Integer> entry : distanceMap.entrySet()){
            Node tempNode = entry.getKey();
            int distance = entry.getValue();
            if(!touchedNodes.contains(tempNode) && distance < minDistance){
                minDistance = distance;
                minNode = tempNode;
            }
        }
        return minNode;
    }
}
