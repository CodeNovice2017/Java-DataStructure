package org.codenovice;

import java.util.Stack;

import org.codenovice.printer.BinaryTreeInfo;

//一种写法,代表E这个元素是必须遵守Comparable接口的
//这是java的语法,如果是类要实现接口就要implements,元素要求实现Compareble好像是要用extends
//而在要实现Compareble的Person类是正常使用implements的,public class Person implements Comparable<Person>
// 警告:泛型类型的Comparable应该参数化
//@SuppressWarnings("unchecked")
public class BinarySearchTree<E> implements BinaryTreeInfo{
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
        Node<E> parNode;
        public Node(E element,Node<E> parNode){
            this.element = element;
            this.parNode = parNode;
        }
    }

    public int size(){
        return 0;
    }
    public boolean isEmpty(){
        return false;
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
                //值比较相等时的处理,用新的元素覆盖原来节点的元素
                //因为如果传入的对象仅仅可能是因为值比较器相同,但是这个对象不光有age,还可能有name属性,所以要采用覆盖
                node.element = element;
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

    //----------遍历代码------------
    //前序遍历
    //递归方式
    public void preorderTraversal(){
        preorderTraversal(rNode);
    }
    private void preorderTraversal(Node<E> node){
        if(node == null){
            return;
        }
        System.out.println(node.element);
        preorderTraversal(node.leftNode);
        preorderTraversal(node.rightNode);
    }
    //非递归方式
    public void preorderTraversalNonRecursive(){
        preorderTraversalNonRecursive(rNode);
    }
    private void preorderTraversalNonRecursive(Node<E> node){
        Stack<Node> stack = new Stack<>();
        
        Node<E> tempNode = rNode;
        stack.push(tempNode);
        while(!stack.empty()){
            Node<E> temp = stack.pop();
            if (temp != null) {
                System.out.println(temp.element);
            } else {
                break;
            }
            if(temp.rightNode != null){
                stack.push(temp.rightNode);
            }
            if(temp.leftNode != null){
                stack.push(temp.leftNode);
            }
        }
    }
    //中序遍历
    //递归方式
    public void inorderTraversal(){

    }
    public void inorderTraversalNonRecursive(){
        inorderTraversalNonRecursive(rNode);
    }
    private void inorderTraversalNonRecursive(Node<E> node){
        Stack<Node> stack = new Stack<>();
        
        Node<E> tempNode = node;
        Node<E> historyNode = null;
        if(tempNode.rightNode!=null){
            stack.push(tempNode. rightNode);
        }
        stack.push(tempNode);
        if(tempNode.leftNode!=null){
            stack.push(tempNode.leftNode);
        }
        while(!stack.empty()){
            tempNode = stack.pop();
            if(tempNode.leftNode == null||tempNode.leftNode == historyNode){
                System.out.println(tempNode.element);
                historyNode = tempNode;
                if (tempNode.rightNode != null) {
                    stack.push(tempNode.rightNode);
                }
            }else{
                if (tempNode.rightNode != null) {
                    stack.push(tempNode.rightNode);
                }
                stack.push(tempNode);
                if (tempNode.leftNode != null) {
                    stack.push(tempNode.leftNode);
                }
            }
        }
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
        return ((Comparable<E>)e1).compareTo(e2);
    }

    //使用讲师自己写的一个printer打印器来输出检验

    @Override
    public Object root() {
        return rNode;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).leftNode;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).rightNode;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>) node).element;
    }
}
