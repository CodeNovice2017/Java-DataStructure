/**
 * @Classname abstractList
 * @Date 2020/3/22 16:59
 * @Created by Veigar
 */

//设计一个抽象类,用来放一些公共相同的代码,同时设计抽象类abstract,又可以不用再抽象类中实现接口List的全部方法
    //abstract类对外不可见,所以无法在外new一个abstractList
public abstract class abstractList<E> implements List<E>{
    protected int size;
    protected void outOfBounds(int index){
        throw new IndexOutOfBoundsException("Index:"+index+", Size"+size);
    }
    protected void rangeCheck(int index){
        if(index<0||index>=this.size){
            outOfBounds(index);
        }
    }
    protected void rangeCheckForAdd(int index){
        if(index<0||index>this.size){
            outOfBounds(index);
        }
    }
    public int size(){
        return this.size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public void add(E element){
        add(this.size,element);
    }
    public boolean contains(E element){
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }
}
