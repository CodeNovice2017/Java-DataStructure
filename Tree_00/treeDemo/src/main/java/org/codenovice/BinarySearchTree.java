package org.codenovice;

//一种写法,代表E这个元素是必须遵守Comparable接口的
//这是java的语法,如果是类要实现接口就要implements,元素要求实现Compareble好像是要用extends
//而在要实现Compareble的Person类是正常使用implements的,public class Person implements Comparable<Person>
// 警告:泛型类型的Comparable应该参数化
@SuppressWarnings("unchecked")
public class BinarySearchTree<E>{
    //根节点 至少要知道根节点
    private Node<E> rNode;

    private int size;
    //Comparator比较器思路
    private Comparator<E> comparator;

    public BinarySearchTree(Comparator comparator){
        this.comparator = comparator;
    }

    public BinarySearchTree(){
        this(null);
    }

    //内部静态类
    private static class Node<E>{
        E element;
        Node<E> leftNode;
        Node<E> rightNode;
        @SuppressWarnings("unused");
        Node<E> parNode;
        public Node(E element,Node<E> parNode){
            this.element = element;
            this.parNode = parNode;
        }
    }

    public int size(){

    }
    public boolean isEmpty(){

    }
    public void clear(){
        return;
    }
    public void add(E element){
        //element不能为空
        elementNotNullCheck(element);
        //添加第一个节点刚好是根节点
        if(rNode == null){
            rNode = new Node<>(element,null);
            size++;
            return;
        }
        //添加的不是第一个节点
        //找到父节点
        //-------我的思路-------
        // Node<E> node = rNode;
        // int cmp = compare(element, node.element);
        // while(cmp!=0){
        //     if(cmp>0){
        //         if(node.rightNode == null){
        //             Node<E> tempNode = new Node<E>(element, node);
        //             node.rightNode = tempNode;
        //             return;
        //         }
        //         else{
        //             node = node.rightNode;
        //             cmp = compare(element,node.element);
        //         }
        //     }
        //     else{
        //         if (node.leftNode == null) {
        //             Node<E> tempNode = new Node<E>(element, node);
        //             node.leftNode = tempNode;
        //             return;
        //         }
        //         else{
        //             node = node.leftNode;
        //             cmp = compare(element, node.element);
        //         }
        //     }
        // }
        // if(cmp == 0){
        //     throw new IllegalArgumentException("element have already exist!");
        // }

        //--------讲师思路---------
        Node<E> node = rNode;
        Node<E> parentNode = rNode;
        int cmp = 0;
        while(node!=null){
            cmp = compare(element, node.element);
            parentNode = node;
            if(cmp < 0){
                node = node.leftNode;
            }
            else if(cmp == 0){
                return;
            }else{
                node = node.rightNode;
            }
        }
        Node<E> tempNode = new Node<>(element, parentNode);
        if(cmp > 0){
            parentNode.rightNode = tempNode;
        }else{
            parentNode.leftNode = tempNode;
        }
        size++;
    }
    public void remove(E element){

    }
    public boolean contains(E element){
        return false;
    }

    //----------封装额外方法区-----------
    //非空检查封装
    private void elementNotNullCheck(E element){
        if(element == null){
            throw new IllegalArgumentException("element must not be null!");
        }
    }
    //泛型下封装的比较函数
    //返回值等于0,代表e1=e2,返回值大于0,代表e1大于e2,返回值大于0,代表e1小于e2,

    //Comparable实现方法
    // private int compare(E e1,E e2){
    //     return e1.comparaTo(e2);
    // }
    private int compare(E e1, E e2) {
        if(comparator != null){
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>)e1).comparaTo(e2);
    }
}
