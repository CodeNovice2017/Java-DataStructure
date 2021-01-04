package Graph.GraphAlgorithm;

import java.util.HashSet;
import java.util.Stack;

import Graph.Node;

// 图的深度优先遍历
// 就是对任何一个节点来说,下面任何一条路可达的所有节点走完才会回到父节点,去走父节点的其他路
public class DFS {
    public static void dfs(Node node){
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            // 从栈中弹出一个节点
            Node tempNode = stack.pop();
            // 遍历该出栈节点的后序节点
            // 也是通过遍历后代遍历过还是没遍历过完成的
            for(Node temp : tempNode.nexts){
                // 如果出栈节点的下一个节点有任何一个没访问过的
                if(!set.contains(temp)){
                    // 再把出栈节点重新入栈(因为这个出栈节点还有可能没有遍历完)
                    stack.push(tempNode);
                    // 再把出栈节点指向的后代节点入栈
                    stack.push(temp);
                    // 注册并遍历这个后代节点
                    set.add(temp);
                    System.out.println(temp.value);
                    // 中断这次for循环,因为已经把出栈节点重新入栈了,不怕之后找不到,下次出栈的是这次出栈节点的后代节点
                    break;
                }
            }
        }
    }
}
