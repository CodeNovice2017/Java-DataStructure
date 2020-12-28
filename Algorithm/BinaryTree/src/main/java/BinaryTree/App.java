package BinaryTree;

import java.util.Comparator;
import java.util.PriorityQueue;

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

        // PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        // PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>(){
        //     @Override
        //     public int compare(Integer t1,Integer t2){
        //         // 默认小根堆的话,应该是t1 > t2的时候是返回大于0的值
        //         if(t1 > t2){
        //             // 这样就可以改变比较的规则变成了大根堆,就是返回值是-1,那么我就认为t1此时应该是"小于"t2,应该放在t2的前面,这样就变成了实际大的值放在了前面
        //             return -1;
        //         }else if(t1 < t2){
        //             return 1;
        //         }else{
        //             return 0;
        //         }
        //     }
        // });
        // priorityQueue.add(1);
        // priorityQueue.add(2);
        // priorityQueue.add(3);
        // priorityQueue.add(0);

        // priorityQueue.stream().forEach(System.out::println);

        // medianHolderStructureDesignTest();

        leastCostGoldChainTest();
    }

    static void medianHolderStructureDesignTest(){

        MedianHolderStructureDesign.MedianHolderStructure medianHolderStructure = new MedianHolderStructureDesign.MedianHolderStructure();

        medianHolderStructure.add(1);
        medianHolderStructure.add(3);
        medianHolderStructure.add(4);
        medianHolderStructure.add(1);

        System.out.println(medianHolderStructure.getMedian());

    }

    static void leastCostGoldChainTest(){
        int[] arr = {10,20,30};
        System.out.println(LeastCostGoldChain.getLeastCost(arr));
    }

}

