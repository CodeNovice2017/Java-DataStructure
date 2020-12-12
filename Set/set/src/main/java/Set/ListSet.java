package Set;

import List.LinkedList;
import List.List;

public class ListSet<E> implements Set<E> {


    private List<E> list = new LinkedList<>();

    @Override
    public void add(E element) {
        
        // 添加就不能直接list.add(element)了

        // 一种方法
        // if(list.contains(element)){
        //     return;
        // }
        // list.add(element);
        // 另一种方法

        // 然后就是如果使用LinkedList链表 之前都是支持元素是空的
        // 具体Set支持不支持空可以我们自己决定
        int index = list.indexOf(element);
        if(index != List.ELEMENT_NOT_FOUND){
            list.set(index, element);
        }

    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void remove(E element) {
        int index = list.indexOf(element);
        if (index != List.ELEMENT_NOT_FOUND) {
            list.remove(index);
        }
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        if(visitor == null){
            return;
        }
        int size = list.size();
        for(int i = 0; i < size;i++){
            visitor.visit(list.get(i));
            
        }


    }
    
    

}
