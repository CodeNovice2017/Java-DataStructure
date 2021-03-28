package BasicDataStructure;

import BasicDataStructure.CatAndDogQueue.CatDogQueue;
import BasicDataStructure.DetermineALinkedListIsPalindromeStructure.ListNode;

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

        // designStackByQueueTest();

        // designQueueByStackTest();

        // catAndDogQueueTest();

        // randomPoolTest();

        // circlePrintMatrixTest();

        // zhiZiPrintMatrixTest();

        // sortedRowAndColMatrixFindValueTest();

        // determineALinkedListIsPalindromeStructureTest();

        // linkedListPartitionTest();

        // linkedListWithRandomPointerCloneTest();

        // twoLinkedListIntersectionTest();

        testReverseList();

    }

    static void designStackByQueueTest(){

        DesignStackByQueue designStackByQueue = new DesignStackByQueue();
        designStackByQueue.push(11);
        designStackByQueue.push(12);
        int result = designStackByQueue.pop();
        System.out.println(result);
        result = designStackByQueue.peek();
        System.out.println(result);
        result = designStackByQueue.pop();
        System.out.println(result);
    }
    
    static void designQueueByStackTest() {

        DesignQueueByStack designQueueByStack = new DesignQueueByStack();
        designQueueByStack.offer(11);
        designQueueByStack.offer(12);
        int result = designQueueByStack.poll();
        System.out.println(result);
        result = designQueueByStack.poll();
        System.out.println(result);
        // result = designQueueByStack.poll();
    }

    static void catAndDogQueueTest() {

        CatAndDogQueue catAndDogQueue = new CatAndDogQueue();

        CatAndDogQueue.Dog dog1 = catAndDogQueue.new Dog();
        CatAndDogQueue.Dog dog2 = catAndDogQueue.new Dog();
        CatAndDogQueue.Dog dog3 = catAndDogQueue.new Dog();

        CatAndDogQueue.Cat cat1 = catAndDogQueue.new Cat();
        CatAndDogQueue.Cat cat2 = catAndDogQueue.new Cat();
        CatAndDogQueue.Cat cat3 = catAndDogQueue.new Cat();

        CatDogQueue catDogQueue = new CatDogQueue();

        catDogQueue.add(dog1);
        catDogQueue.add(cat1);
        catDogQueue.add(dog2);
        catDogQueue.add(cat2);
        catDogQueue.add(dog3);
        catDogQueue.add(cat3);

        System.out.println(catDogQueue.isCatEmpty());

        for (int i = 0; i < 3; i++) {
            catDogQueue.pollDog();
        }
        System.out.println(catDogQueue.isDogEmpty());
    }

    static void randomPoolTest(){
        RandomPool randomPool = new RandomPool();
        char temp = 'a';
        while(temp <= 'z'){
            randomPool.insert(String.valueOf(temp));
            temp++;
        }
        for (int i = 0; i < 10; i++) {
            String tempString = randomPool.getRandom();
            System.out.println(tempString);
        }
        randomPool.delete("c");
    }

    static void circlePrintMatrixTest(){
        int[][] matrix = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        CirclePrintMatrix.spiralOrder(matrix);
    }
    
    static void zhiZiPrintMatrixTest() {
        int[][] matrix = new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        ZhiZiPrintMatrix.zhiZiPrintMatrixFunction(matrix);
    }

    static void sortedRowAndColMatrixFindValueTest() {
        int[][] matrix = new int[][] { { 0,1,2,5 }, { 2,3,4,7 }, { 4,4,4,8 }, { 5,7,7,9 } };
        boolean result = SortedRowAndColMatrixFindValue.hasValue(matrix, 0);
        System.out.println(result);
    }
    static void determineALinkedListIsPalindromeStructureTest(){

        // 奇数个节点测试
        // ListNode node1 = new ListNode(1);
        // ListNode node2 = new ListNode(2);
        // ListNode node3 = new ListNode(3);
        // ListNode node4 = new ListNode(2);
        // ListNode node5 = new ListNode(1);
        // node1.next = node2;
        // node2.next = node3;
        // node3.next = node4;
        // node4.next = node5;
        // node5.next = null;

        // 偶数个节点测试
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(2);
        ListNode node6 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = null;
        boolean result = DetermineALinkedListIsPalindromeStructure.determineALinkedListIsPalindromeStructureFunction(node1);
        System.out.println(result);
    }
    static void linkedListPartitionTest(){

        LinkedListPartition.ListNode node1 = new LinkedListPartition.ListNode(7);
        LinkedListPartition.ListNode node2 = new LinkedListPartition.ListNode(3);
        LinkedListPartition.ListNode node3 = new LinkedListPartition.ListNode(4);
        LinkedListPartition.ListNode node4 = new LinkedListPartition.ListNode(6);
        LinkedListPartition.ListNode node5 = new LinkedListPartition.ListNode(0);
        LinkedListPartition.ListNode node6 = new LinkedListPartition.ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = null;

        LinkedListPartition.ListNode head = LinkedListPartition.partition(node1, 4);

        System.out.println(head);
    }
    static void linkedListWithRandomPointerCloneTest(){

        LinkedListWithRandomPointerClone.Node node1 = new LinkedListWithRandomPointerClone.Node(1);
        LinkedListWithRandomPointerClone.Node node2 = new LinkedListWithRandomPointerClone.Node(2);
        LinkedListWithRandomPointerClone.Node node3 = new LinkedListWithRandomPointerClone.Node(3);
        LinkedListWithRandomPointerClone.Node node4 = new LinkedListWithRandomPointerClone.Node(4);
        LinkedListWithRandomPointerClone.Node node5 = new LinkedListWithRandomPointerClone.Node(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

        node1.rand = node5;
        node2.rand = node4;

        LinkedListWithRandomPointerClone.Node newHead = LinkedListWithRandomPointerClone.linkedListWithRandomPointerClone(node1);
        System.out.println(newHead);
    }

    static void twoLinkedListIntersectionTest(){

        TwoLinkedListIntersection.ListNode node1 = new TwoLinkedListIntersection.ListNode(1);
        TwoLinkedListIntersection.ListNode node2 = new TwoLinkedListIntersection.ListNode(2);
        TwoLinkedListIntersection.ListNode node3 = new TwoLinkedListIntersection.ListNode(3);
        TwoLinkedListIntersection.ListNode node4 = new TwoLinkedListIntersection.ListNode(4);
        TwoLinkedListIntersection.ListNode node5 = new TwoLinkedListIntersection.ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node1;

        TwoLinkedListIntersection.ListNode result = TwoLinkedListIntersection.judgeWhetherLinkedListHasLoop(node1);
        System.out.println(result);
    }

    static void testReverseList(){

        ReverseLinkedList.ListNode node1 = new ReverseLinkedList.ListNode(1);
        ReverseLinkedList.ListNode node2 = new ReverseLinkedList.ListNode(2);
        ReverseLinkedList.ListNode node3 = new ReverseLinkedList.ListNode(3);
        ReverseLinkedList.ListNode node4 = new ReverseLinkedList.ListNode(4);
        ReverseLinkedList.ListNode node5 = new ReverseLinkedList.ListNode(5);
        
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

        ReverseLinkedList.reverseList(node1);
    }
}
