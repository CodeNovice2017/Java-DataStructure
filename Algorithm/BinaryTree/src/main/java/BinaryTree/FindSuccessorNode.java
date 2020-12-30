package BinaryTree;

public class FindSuccessorNode {
    
    public class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node findSuccessorNode(Node node){
        if(node == null){
            return null;
        }
        // 如果node节点有右子树,那么后继节点就是其右子树中最左的节点
        if(node.right != null){
            node = node.right;
            while(node.left != null){
                node = node.left;
            }
            return node;
        } else {
            Node parent = node.parent;
            while (parent != null && parent.left != node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }
}
