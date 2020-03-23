package LinkedList;
//https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
/**
 * @Classname 删除链表中的节点_237
 * @Date 2020/3/23 15:48
 * @Created by Veigar
 */
public class 删除链表中的节点_237_简单 {

     public class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
     }
    //最重要思想,不需要头节点,删这个结点下一个结点即可
    class Solution {
        public void deleteNode(ListNode node) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }
}
