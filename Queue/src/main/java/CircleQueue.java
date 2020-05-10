public class CircleQueue<E>{ //只用一维数组实现的循环队列

    private int front;
    private int size;
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    public CircleQueue(){
        elements = (E[])new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    //入队添加到尾部
    public void enQueue(E element) {
        ensureCapacity(size+1);;
        elements[(front + size)%elements.length] = element;
        size++;
    }
    public E deQueue() {
        E frontElement = elements[front];
        elements[front] = null;
        front = (front+1)%elements.length;
        size--;
        return frontElement;
    }

    //最简单的实现,只要保证front一直指向队头即可
    public E front() {
        return elements[front];
    }

    //动态扩容
    private void ensureCapacity(int capacity) {
        if (this.elements.length >= capacity) {
            return;
        }
        int newCapacity = this.elements.length + (this.elements.length >> 1); // java官方也有这个系数的选择,但是注意写法我们一般不写成*1.6这种
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(i + front)%elements.length]; // 将elements指向新创建得newElements
        }
        elements = newElements;
        front = 0;
        System.out.println("扩容至:" + newCapacity);
    }
    
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("capacity=").append(elements.length).append("size=").append(size).append("front=").append(front).append(", [");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0) {
                string.append(", ");
            }
            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }
}