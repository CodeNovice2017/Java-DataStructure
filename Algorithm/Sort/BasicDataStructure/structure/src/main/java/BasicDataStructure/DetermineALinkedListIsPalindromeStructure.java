package BasicDataStructure;

public class DetermineALinkedListIsPalindromeStructure {
    public static class ListNode{
        int value;
        ListNode next;
        public ListNode(int value){
            this.value = value;
        }
    }
    public static boolean determineALinkedListIsPalindromeStructureFunction(ListNode head){
        // 无元素
        if(head == null){
            return false;
        }
        // 仅有一个元素
        if(head.next == null){
            return true;
        }
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        ListNode preSlowPointer = slowPointer;
        // 按这种边界条件的判断方式,链表为偶数时slowPointer指向划分一半后,链表右侧部分第一个节点
        // 链表为奇数时,slowPointer刚好指向中间节点
        while(fastPointer != null){
            if(fastPointer.next != null){
                preSlowPointer = slowPointer;
                slowPointer = slowPointer.next;
                fastPointer = fastPointer.next.next;
            }else{
                break;
            }
        }

        boolean result = true;
        ListNode reverseListHeadNode = reverseLinkedList(slowPointer);
        ListNode reverseListHeadNodeTemp = reverseListHeadNode;
        while(reverseListHeadNode != null){
            if(head.value == reverseListHeadNode.value){
                head = head.next;
                reverseListHeadNode = reverseListHeadNode.next;
            }else{
                result = false;
            }
        }
        // 将逆序的后半区再调回来
        reverseListHeadNode = reverseLinkedList(reverseListHeadNodeTemp);
        preSlowPointer.next = reverseListHeadNode;
        return result;
    }

    // 对后半区的链表逆序
    private static ListNode reverseLinkedList(ListNode pointer){
        ListNode preNode = pointer;
        ListNode tempNode = pointer;
        ListNode nextNode = pointer.next;
        while(nextNode!=null){
            if(preNode == pointer){
                preNode.next = null;
            }
            tempNode = nextNode;
            nextNode = nextNode.next;
            tempNode.next = preNode;
            preNode = tempNode;
        }
        return tempNode;
    }
}
