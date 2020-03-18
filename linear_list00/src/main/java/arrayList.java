import com.sun.media.sound.SoftTuning;

import java.util.Arrays;

/**
 * @Classname arrayList
 * @Date 2020/3/15 9:16
 * @Created by Veigar
 */
//封装一个动态数组
public class arrayList<T> {
    //不用设置容量域,因为capacity = elements.length
    private int size;
    private T[] elements;
    private static final int DEFAULT_CAPACITY = 2;
    private static final int ELEMENT_NOTFOUND = -1;

    public arrayList(int capacity){
        capacity = capacity<DEFAULT_CAPACITY?DEFAULT_CAPACITY:capacity; //只要传入的capacity小于DEFAULT_CAPACITY就用默认值
        elements = (T[])new Object[capacity];  //这里直接改T[]会报错,Type parameter 'T' cannot be instantiated directly 这时就用到了java中所有的类最终都继承java.lang.Object
    }
    public  arrayList(){
//        elements = new int[DEFAULT_CAPACITY]; 这样写完全可以再改进,利用this调用构造函数
        this(DEFAULT_CAPACITY);
    }
    public int size(){
        return this.size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public T get(int index){
        rangeCheck(index);
        return elements[index];
    }
    public T set(int index,T element){
        rangeCheck(index);
        T old = elements[index];
        elements[index] = element;
        return old;
    }
    //写成泛型之后要修改,要重写可能填入对象的equals,默认equals方法是比较两个对象是否完全一样,就是内存地址是否一致
    public int indexOf(T element){
        if(element == null){
            for (int i = 0; i < size; i++) {
                if(elements[i] == null){ //返回第一个null在数组中的位置
                    return i;
                }
            }
        }else{
            for(int i = 0;i<this.size;i++) {
                if (element.equals(elements[i])) { //改写为element在前,防止elements数组中被传入null元素,引起空指针
                    return i;
                }
            }
        }
        return ELEMENT_NOTFOUND;
    }
    public boolean contains(T element){
        return indexOf(element) != ELEMENT_NOTFOUND;
    }
    //写成泛型之后要修改内存释放,具体原因见笔记内存释放
    public void clear(){
        for (int i = 0; i <this.size ; i++) {
            elements[i] = null;
        }
        size = 0;
    }
    public void add(T element){
        add(this.size,element);
    }
    //泛型改写之后要修改内存释放
    public T remove(int index){
        rangeCheck(index);
        T old = elements[index];
        for (int i = index; i < size; i++) {
            elements[i] = elements[i+1];
        }
        elements[--size] = null;
        return old;
    }
    public void add(int index,T element){
        rangeCheckForAdd(index);
        //动态扩容(两个add需要扩容)
        ensureCapacity(this.size+1);
        for (int i = size-1; i >= index ; i--) {
            elements[i+1] = elements[i];
        }
        this.elements[index] = element;
        this.size++;
    }
    //动态扩容操作
    private void ensureCapacity(int capacity){
        if(this.elements.length > capacity){
            return;
        }
        int newCapacity = this.elements.length + (this.elements.length >> 1); //java官方也有这个系数的选择,但是注意写法我们一般不写成*1.6这种
        T[] newElements = (T[]) new Object[newCapacity];
        for(int i=0;i<size;i++){
            newElements[i] = elements[i]; //将elements指向新创建得newElements
        }
        elements = newElements;
        System.out.println("扩容至:"+ newCapacity);
    }
    private void outOfBounds(int index){
        throw new IndexOutOfBoundsException("Index:"+index+", Size"+size);
    }
    private void rangeCheck(int index){
        if(index<0||index>=this.size){
            outOfBounds(index);
        }
    }
    private void rangeCheckForAdd(int index){
        if(index<0||index>this.size){
            outOfBounds(index);
        }
    }

    @Override
    public String toString() {
        return "arrayList{" +
                "size=" + size +
                ", elements=" + Arrays.toString(elements) +
                '}';
    }
}
