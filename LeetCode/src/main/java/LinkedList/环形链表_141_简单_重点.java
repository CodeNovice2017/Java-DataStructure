package LinkedList;
//https://leetcode-cn.com/problems/linked-list-cycle/

/**
 * @Classname 环形链表_141_简单
 * @Date 2020/3/23 17:47
 * @Created by Veigar
 */
public class 环形链表_141_简单_重点 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    //快慢指针思想
    public class Solution {
        public boolean hasCycle(ListNode head) {
            if(head == null||head.next==null){
                return false;
            }
            //一开始设置不要相遇
            ListNode slow = head;
            ListNode fast = head.next;
            //这两个条件都不能省,思考,省了任何一个都有可能空指针异常,这也是为什么一个只走两步,走三步需要更多的循环判断条件
            while(fast!=null&&fast.next!=null){
                if(slow == fast){
                    return true;
                }
                slow = slow.next;
                fast = fast.next.next;
            }
            return false;
        }
    }
}
