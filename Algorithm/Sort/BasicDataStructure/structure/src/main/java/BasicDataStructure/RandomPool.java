package BasicDataStructure;

import java.util.HashMap;
import java.util.Map;

public class RandomPool {
    
    private Map<String, Integer> map1 = new HashMap<>();
    private Map<Integer, String> map2 = new HashMap<>();
    private int index = 0;

    // O(1)的插入key到结构中
    public void insert(String key){
        map1.put(key, index);
        map2.put(index, key);
        index++;
    }
    // O(1)等概率获取任何一个Key
    public String getRandom(){
        String key = map2.get((int)(Math.random() * index));
        return key;
    }
    // O(1)的删除指定的key
    public void delete(String key){

        if(index <= 0){
            throw new RuntimeException("RandomPool is Empty!");
        }
        // 记录需要删除的key的int index
        int removeIndex = map1.get(key);
        map1.remove(key);
        // map2.get(index-1)获取index-1位置的String key
        map1.put(map2.get(index-1),removeIndex);
        map2.put(removeIndex, map2.get(index-1));
        map2.remove(index-1);
        index--;
    }

}
