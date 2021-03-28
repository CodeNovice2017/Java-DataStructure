package List;

import List.TwoListNodeAdd.Node;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {

        System.out.println("Hello World!");

        testTwoListNodeAdd();

    }

    static void testTwoListNodeAdd(){
        // 1->2->3->4
        // 8->8->6->5->0
        // 1->0->9->9->0
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        n1.next.next.next = new Node(4);

        Node n2 = new Node(8);
        n2.next = new Node(8);
        n2.next.next = new Node(6);
        n2.next.next.next = new Node(5);
        n2.next.next.next.next = new Node(0);

        Node resultNode = TwoListNodeAdd.twoListNodeAddProcess(n1,n2);
        
        while(resultNode != null){
            System.out.println(resultNode.val + "->");
            resultNode = resultNode.next;
        }
    }
}
