package BinaryTree;

import java.util.Stack;

public class BinaryTreeTraversal {
    
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void preOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    public static void inOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    public static void posOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value + " ");
    }

    public static void preOrderUnRecur(Node head) {
        System.out.print("pre-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.value + " ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    public static void preOrderUnRecur2(Node head){
        System.out.print("pre-order: ");
        if(head != null){
            Stack<Node> stack = new Stack<>();
            while(head != null || !stack.isEmpty()){
                if(head != null){
                    System.out.print(head.value + " ");
                    stack.push(head);
                    head = head.left;
                }else{
                    head = stack.pop();
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    public static void inOrderUnRecur(Node head) {
        System.out.print("in-order: ");
        if(head != null){
            Stack<Node> stack = new Stack<>();
            while (head != null || !stack.isEmpty()) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    public static void posOrderUnRecur1(Node head) {
        if(head != null){
            Stack<Node> stack = new Stack<>();
            Stack<Node> resultStack = new Stack<>();
            while(!stack.isEmpty() || head != null){
                if(head != null){
                    resultStack.push(head);
                    head = head.right;
                }else{
                    head = stack.pop();
                    head = head.left;
                }
            }
            while(!resultStack.isEmpty()){
                System.out.println(resultStack.pop().value + " ");
            }
        }
        System.out.println();
    }
}
