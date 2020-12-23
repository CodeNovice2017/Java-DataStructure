package BasicDataStructure;

public class LinkedListPartition {
    
    public static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    public static ListNode partition(ListNode head,int pivot){

        ListNode smallHead = null;
        ListNode equalHead = null;
        ListNode bigHead = null;

        ListNode smallTail = null;
        ListNode equalTail = null;
        ListNode bigTail = null;

        while(head != null){
            if(head.value < pivot){
                if(smallHead == null){
                    smallHead = head;
                    smallTail = smallHead;
                }else{
                    smallTail.next = head;
                    smallTail = head;
                }
            }else if(head.value > pivot){
                if (bigHead == null) {
                    bigHead = head;
                    bigTail = bigHead;
                } else {
                    bigTail.next = head;
                    bigTail = head;
                }
            }else{
                if (equalHead == null) {
                    equalHead = head;
                    equalTail = equalHead;
                } else {
                    equalTail.next = head;
                    equalTail = head;
                }
            }
            head = head.next;
        }
        if (bigTail != null) {
            bigTail.next = null;
        }
        if(smallHead == null){
            if(equalHead == null){
                return bigHead;
            }else{
                equalTail.next = bigHead;
                return equalHead;
            }
        }else{
            if(equalHead == null){
                smallTail.next = bigHead;
                return smallHead;
            }else{
                smallTail.next = equalHead;
                equalTail.next = bigHead;
                return smallHead;
            }
        }
    }
}
