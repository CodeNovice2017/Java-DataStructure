package BasicDataStructure;

public class TwoLinkedListIntersection {
    
    public static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    public static ListNode getIntersectionNode(ListNode head1,ListNode head2){
        if(head1 == null || head2 == null){
            return null;
        }
        ListNode node1 = judgeWhetherLinkedListHasLoop(head1);
        ListNode node2 = judgeWhetherLinkedListHasLoop(head2);
        if(node1 == null && node2 == null){
            return noLoop(head1, head2);
        }
        if(node1 != null && node2 != null){
            return bothLoop(head1, head2,node1,node2);
        }
        return null;
    }

    // 判断单个链表是否有环
    public static ListNode judgeWhetherLinkedListHasLoop(ListNode head){
        if(head == null || head.next == null || head.next.next == null){
            return null;
        }
        ListNode slowPointer = head.next;
        ListNode fastPointer = head.next.next;
        while(fastPointer != slowPointer){
            if(fastPointer.next == null || fastPointer.next.next == null){
                return null;
            }
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }
        fastPointer = head;
        while(fastPointer != slowPointer){
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next;
        }
        return slowPointer;
    }

    // 两个链表都无环的情况(不使用Hash表找相交节点)
    public static ListNode noLoop(ListNode head1,ListNode head2){
        if(head1 == null || head2 == null){
            return null;
        }
        int n = 0;
        ListNode tempNode1 = head1;
        ListNode tempNode2 = head2;
        while(tempNode1.next != null){
            n++;
            tempNode1 = tempNode1.next;
        }
        while(tempNode2.next != null){
            n--;
            tempNode2 = tempNode2.next;
        }
        tempNode1 = head1;
        tempNode2 = head2;
        // 链表1更长
        if(n > 0){
            while(n > 0){
                tempNode1 = tempNode1.next;
            }
            while(tempNode1 != tempNode2){
                tempNode1 = tempNode1.next;
                tempNode2 = tempNode2.next;
            }
        }else{
            n = Math.abs(n);
            while (n > 0) {
                tempNode2 = tempNode2.next;
            }
            while (tempNode1 != tempNode2) {
                tempNode1 = tempNode1.next;
                tempNode2 = tempNode2.next;
            }
        }
        return tempNode1;
    }
    
    // 两个链表都有环的情况(不使用Hash表找相交节点)
    public static ListNode bothLoop(ListNode head1,ListNode head2,ListNode node1,ListNode node2){
        ListNode tempNode1 = head1;
        ListNode tempNode2 = head2;
        if(node1 == node2){
            int n = 0;

            while (tempNode1.next != node1) {
                n++;
                tempNode1 = tempNode1.next;
            }
            while (tempNode2.next != node2) {
                n--;
                tempNode2 = tempNode2.next;
            }
            tempNode1 = head1;
            tempNode2 = head2;
            // 链表1更长
            if (n > 0) {
                while (n > 0) {
                    tempNode1 = tempNode1.next;
                }
                while (tempNode1 != tempNode2) {
                    tempNode1 = tempNode1.next;
                    tempNode2 = tempNode2.next;
                }
            } else {
                n = Math.abs(n);
                while (n > 0) {
                    tempNode2 = tempNode2.next;
                }
                while (tempNode1 != tempNode2) {
                    tempNode1 = tempNode1.next;
                    tempNode2 = tempNode2.next;
                }
            }
            return tempNode1;
        }else{
            tempNode1 = node1.next;
            while(tempNode1 != node1){
                if(tempNode1 == node2){
                    return node2;
                }
                tempNode1 = tempNode1.next;
            }
            return null;
        }
    }
}
