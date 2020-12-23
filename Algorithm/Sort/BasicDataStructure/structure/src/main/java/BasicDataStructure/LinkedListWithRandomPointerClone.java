package BasicDataStructure;

import java.util.HashMap;
import java.util.Map;

public class LinkedListWithRandomPointerClone {

    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }
    public static Node linkedListWithRandomPointerClone(Node head){
        if (head == null) {
            return null;
        }
        Map<Node,Node> map = new HashMap<>();
        Node tempNode = head;
        while(tempNode != null){
            map.put(tempNode, new Node(tempNode.value));
            tempNode = tempNode.next;
        }
        tempNode = head;
        while(tempNode != null){
            map.get(tempNode).next = map.get(tempNode.next);
            map.get(tempNode).rand = map.get(tempNode.rand);
            tempNode = tempNode.next;
        }
        return map.get(head);
    }
}
