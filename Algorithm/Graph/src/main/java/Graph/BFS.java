package Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// 图的宽度有限遍历
public class BFS { 
    public static void bfs(Node node){
        if(node == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        // 在平时写宽度优先遍历算法的时候,一定要记住有一个set,set是为了不让节点重复遍历
        HashSet<Node> set = new HashSet<>();
        queue.offer(node);
        set.add(node);
        while(!queue.isEmpty()){
            Node tempNode = queue.poll();
            System.out.println(tempNode.value);
            for(Node temp : tempNode.nexts){
                if(!set.contains(temp)){
                    set.add(temp);
                    queue.offer(temp);                    
                }
            }
        }
    }
}
