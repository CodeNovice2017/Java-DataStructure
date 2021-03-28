package List;

public class TwoListNodeAdd {

    public static class Node {
        Node next;
        int val;

        public Node(int val) {
            this.val = val;
        }
    }
    public static Node twoListNodeAddProcess(Node n1,Node n2){

        // 判空
        if(n1 == null){
            return n2;
        }
        if(n2 == null){
            return n1;
        }

        Node n1Pointer = n1;
        Node n2Pointer = n2;

        // 优化点1 可以不需要两个循环去遍历,只需要一个循环,走到短的链表为null时就停止

        // int n1ListLength = 1;
        // while(n1Pointer != null){
        //     n1Pointer = n1Pointer.next;
        //     n1ListLength++;
        // }
        // int n2ListLength = 1;
        // while(n2Pointer != null){
        //     n2Pointer = n2Pointer.next;
        //     n2ListLength++;            
        // }
        // Node newHead = n2ListLength > n1ListLength ? n2 : n1;

        while(n1Pointer != null && n2Pointer != null){
            n1Pointer = n1Pointer.next;
            n2Pointer = n2Pointer.next;
        }
        Node newHead = null;
        // n1Orn2Flag为true,表示n1更长,设置该标志位的作用是之后可以复用newHead引用
        boolean n1Orn2Flag = false;
        if(n1Pointer == null){
            n1Orn2Flag = false;
            newHead = n2;
        }else{
            n1Orn2Flag = true;
            newHead = n1;
        }

        n1Pointer = n1;
        n2Pointer = n2;

        while(n1Pointer != null && n2Pointer != null){
            newHead.val = n1Pointer.val + n2Pointer.val;
            n1Pointer = n1Pointer.next;
            n2Pointer = n2Pointer.next;
            newHead = newHead.next;
        }
        while(n1Pointer != null){
            newHead.val = n1Pointer.val;
            n1Pointer = n1Pointer.next;
            newHead = newHead.next;
        }
        while(n2Pointer != null){
            newHead.val = n2Pointer.val;
            n2Pointer = n2Pointer.next;
            newHead = newHead.next;
        }

        if(n1Orn2Flag){
            newHead = n1;
        }else{
            newHead = n2;
        }

        // 优化点2 反转链表部分因为要进行两次,可以提取出来作为一个方法复用
        // 反转链表
        newHead = reverseList(newHead);

        // flag标志位表示是否产生进位,最开始肯定无需考虑之前是否产生进位
        boolean flag = false;
        Node pre = null;
        Node tempPointer = newHead;
        while(tempPointer != null){
            if(!flag){
                if(tempPointer.val >= 10){
                    flag = true;
                    tempPointer.val = tempPointer.val % 10;
                }
            }else{
                if(tempPointer.val + 1 >= 10){
                    flag = true;
                    tempPointer.val = (tempPointer.val + 1) % 10;
                }else{
                    flag = false;
                }
            }
            pre = tempPointer;
            tempPointer = tempPointer.next;
        }
        // 最终节点的值如果产生了进位,还需要new一个新的Node(1)连在头部
        if(flag){
            pre.next = new Node(1);
            pre = pre.next;
            newHead = reverseList(newHead);
        }
        return newHead;
    }

    private static Node reverseList(Node head){
        Node pre = null;
        while (head != null) {
            Node temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }
}
