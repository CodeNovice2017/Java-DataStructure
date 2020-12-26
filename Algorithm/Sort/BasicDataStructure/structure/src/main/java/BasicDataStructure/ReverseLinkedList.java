package BasicDataStructure;

public class ReverseLinkedList {

    public static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }
    
    public static ListNode reverseRecursive(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        // 先把我head后面的链表的反转好了,同时保留了原链表尾节点做反转链表的头结点
        // currentHead始终是保存反转链表头结点的
        ListNode currentHead = reverseRecursive(head.next);

        // 如果我下面的链表已经反转好了,那么我只要让我下面一个节点的next指向我即可
        head.next.next = head;

        // 同时为了避免产生环,我自己的next肯定要指向空了

        head.next = null;

        // 最后递归返回的永远都是currentHead
        return currentHead;
    }
    public static ListNode reverseNonRecursive(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }
        ListNode preNodePointer = head;
        ListNode currentNodePointer = head.next;
        ListNode tempNode = null;
        head.next = null;
        while(currentNodePointer != null){
            tempNode = currentNodePointer.next;
            currentNodePointer.next = preNodePointer;
            preNodePointer = currentNodePointer;
            currentNodePointer = tempNode;
        }
        return preNodePointer;
    }
}
