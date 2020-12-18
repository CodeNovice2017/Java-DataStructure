package BasicDataStructure;

import BasicDataStructure.CatAndDogQueue.CatDogQueue;

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

        zhiZiPrintMatrixTest();


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
}
