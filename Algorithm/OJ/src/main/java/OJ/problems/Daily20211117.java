package OJ.problems;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author codechase <codecx@163.com>
 * Created on 2021-11-17
 */
public class Daily20211117 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int v : nums){
            int c = map.getOrDefault(v,0) + 1;
            map.put(v,c);
        }
        // 建立一个只有K个元素的小根堆
        PriorityQueue<Map.Entry<Integer,Integer>> heap = new PriorityQueue<>((item1, item2) -> {
            if(item1.getValue() < item2.getValue()){
                return -1;
            }else if(item1.getValue() > item2.getValue()){
                return 1;
            }else{
                return 0;
            }
        });
        for(Map.Entry item : map.entrySet()){
            if(heap.size() < k){
                heap.add(item);
            }else if(heap.size() == k){
                heap.remove();
                heap.add(item);
            }
        }
        int[] result = new int[k];
        int index = 0;
        for(Map.Entry<Integer,Integer> item : heap){
            result[index++] = item.getKey();
        }
        return result;
    }
}
