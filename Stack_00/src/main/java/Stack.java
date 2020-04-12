/**
 * package_name: PACKAGE_NAME
 * project_name: Stack_00
 * creat_user: Veigar
 * creat_date: 2020/4/12
 * creat_time: 15:05
 **/
import list.List;
import list.arrayList;

public class Stack<E>{
    private List<E> list = new arrayList<E>();

    public int size(){
        return list.size();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public void push(E element){
        list.add(element);
    }
    public E pop(){
        return list.remove(list.size()-1);
    }
    public E top(){
        return list.get(list.size() - 1);
    }
}
