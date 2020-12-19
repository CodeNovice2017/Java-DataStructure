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

        determineALinkedListIsPalindromeStructureTest();

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
}
