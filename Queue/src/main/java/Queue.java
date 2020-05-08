import list.List;
import list.linkedList;

/**
 * package_name: PACKAGE_NAME
 * project_name: Queue
 * creat_user: Veigar
 * creat_date: 2020/5/6
 * creat_time: 16:17
 **/
public class Queue<E> {
    private List<E> list = new linkedList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
    //入队添加到尾部
    public void enQueue(E element) {
        list.add(element);
    }
    public E deQueue() {
        return list.remove(0);
    }

    public E front() {
        return list.get(0);
    }
}
