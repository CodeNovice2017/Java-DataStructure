package BinaryTree;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianHolderStructureDesign {

    public static class MedianHolderStructure{
        public PriorityQueue<Integer> bigHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                // 默认小根堆的话,应该是t1 > t2的时候是返回大于0的值
                if (t1 > t2) {
                    // 这样就可以改变比较的规则变成了大根堆,就是返回值是-1,那么我就认为t1此时应该是"小于"t2,应该放在t2的前面,这样就变成了实际大的值放在了前面
                    return -1;
                } else if (t1 < t2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        public PriorityQueue<Integer> smallHeap = new PriorityQueue<>();

        public MedianHolderStructure(){
            
        }

        public void add(int element){
            if(bigHeap.isEmpty()){
                bigHeap.add(element);
                return;
            }
            if(element <= bigHeap.peek()){
                bigHeap.add(element);
            }else{
                smallHeap.add(element);
            }
            int valueTemp = bigHeap.size() > smallHeap.size() ? bigHeap.size() - smallHeap.size() : smallHeap.size() - bigHeap.size();
            if(valueTemp > 1){
                if(bigHeap.size() > smallHeap.size()){
                    smallHeap.add(bigHeap.poll());
                }else{
                    bigHeap.add(smallHeap.poll());
                }
            }
        }
        public int getMedian(){
            if(bigHeap.isEmpty()){
                return 0;
            }else if(bigHeap.size() == 1 && smallHeap.isEmpty()){
                return bigHeap.peek();
            }else{
                return (bigHeap.peek() + smallHeap.peek())/2;
            }
        }
    }
}
