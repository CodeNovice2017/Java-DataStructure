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
        // 此时并不将head节点加入selectedMap,因为此时head还没有进行松弛操作,
        // 松弛操作的含义我理解是要遍历该节点所相邻的所有节点,因为此时起点到该节点的距离是最短的(通过getMinDistanceAndUnselectedNode找出的节点就是起点到该节点最短路径的节点,不可能之后解锁的节点反而更近)
        // 而松弛操作要做的工作是计算从起点到该点的距离加上该点和相邻点的距离,和之前维护在distanceMap中起点通过其他路径到和该点相邻节点距离中较小的值,因为本身distanceMap.get(minNode)已经是起点到达minNode的最短路径了,即使之后有到minNode相邻接点的更短的路径,那这条路径也不可能经过minNode中转
        // 只有上面说的松弛操作的工作做完了,起点到minNode的最短距离也确定了,才将minNode加入selectedSet
        // 而对于此时的情景来说就是minNode就是head,按照上面说的流程,此时通过getMinDistanceAndUnselectedNode找出的就是还没有进行松弛操作的节点中到达head起点最短路径的节点(最开始就是距离为0的head)
        // 然后将通过minNode中转的所有相邻节点的distance加入distanceMap中,因为之后即使有起点到这些相邻节点更短的路径,也绝不会经过minNode了,因为起点到minNode已经是确认的最短路径了

        // 所以加入到selectedSet就代表着该节点的最短路径已经算出,通过该节点可以到达的相邻接点的路径距离已经更新在distanceMap中了,以后再也不用管我了

        // 也就是松弛操作之后解锁这个节点
        HashSet<Node> selectedSet = new HashSet<>();
        distanceMap.put(head, 0);
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
