package LinkedList;
//https://leetcode-cn.com/problems/reverse-linked-list/
/**
 * @Classname 反转链表_206
 * @Date 2020/3/23 16:05
 * @Created by Veigar
 */
public class 反转链表_206_重点_简单 {
      public class ListNode {
          int val;
         ListNode next;
         ListNode(int x) { val = x; }

     }

    class Solution {
        public ListNode reverseList(ListNode head) {
            //最开始自己写的,超出内存限制...
//            ListNode newHead;
//            ListNode newRail;
//            ListNode temp = head;
//            ListNode prev = head;
//            while(temp.next!=null){
//                prev = temp;
//                temp = temp.next;
//            }
//            newHead = temp;
//            newRail = temp;
//            prev.next = null;
//            while(head.next!=null){
//                temp = head;
//                while(temp.next!=null){
//                    prev = temp;
//                    temp = temp.next;
//                }
//                prev.next = null;
//                newRail.next = temp;
//                newRail = temp;
//            }
//            return newHead;
            //递归
            //先假设如果reverseList函数写完了,那么实现的功能是把链表倒转
            //然后先以前两个结点来说的话,ListNode newHead = reverseList(head.next)
            //然后往后想,那么把5,4,3,2,1来想的话,如果传入4已经反转好了的话,那么只要处理5即可
//            ListNode newHead = reverseList(head.next);
//            head.next.next = head;  //关键点,此时反转之后,head还是5,head的next还是4,现在需要head.next4的next指向5结点
//            head.next = null;
//            return newHead;
            //写到上面,主体的功能已经实现,但是直接用肯定是死循环,需要在前面加边界条件
            //如果传head是空,那么就不需要反转
//            if(head==null){
//                return head;
//            }
//            ListNode newHead = reverseList(head.next);
//            head.next.next = head;
//            head.next = null;
//            return newHead;
//            if(head==null){
//                return head;
//            }
//            //如果head的next为空,那么就代表只有一个结点,也不用反转
//            if(head.next == null){
//                return head;
//            }
            //然后上下两个条件还可以合在一起
//            if(head==null||head.next==null){
//                return head;
//            }
//            ListNode newHead = reverseList(head.next);
//            head.next.next = head;
//            head.next = null;
//            return newHead;
            //递归完成
            //非递归写法
            if(head==null||head.next==null){
                return head;
            }
            ListNode newHead = null;
            while(head!=null) {
                ListNode temp = head.next;
                newHead = head;
                head.next = newHead;
                head = temp;
            }
            return newHead;
        }
    }
}
