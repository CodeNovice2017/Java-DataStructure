/**
 * @Classname LinkedList
 * @Date 2020/3/22 15:10
 * @Created by Veigar
 */
//带虚拟头结点的LinkedList
    //要在LinkedList基础上修改 add remove indexOf toString
    //也并不是非要加虚拟头节点
public class linkedListAddFirstNullNode<E> extends abstractList<E>{
    //由于结点类型是只用于链表内部的,应该声明为内部类,而且内部类建议写为静态的
    private Node<E> first;
    public linkedListAddFirstNullNode(){
        first = new Node<E>(null,null);
    }
    private static class Node<E>{
        //并且由于这是只用在LinkList内部的,所以就不必声明私有,公共等
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }
    
    public void clear() {
        size = 0;
        first = null;
    }

    public E get(int index) {
        return nodeIndex(index).element;
    }

    public E set(int index, E element) {
        E temp = nodeIndex(index).element;
        nodeIndex(index).element = element;
        return temp;
    }

    public void add(int index, E element) {
        rangeCheckForAdd(index);
        Node<E> prev = index == 0? first:nodeIndex(index - 1);
        Node<E> newNode = new Node<E>(element, prev.next);
        prev.next = newNode;
        size++;

    }

    public E remove(int index) {
        rangeCheck(index);
        Node<E> prev = index ==0?first:nodeIndex(index - 1);
        Node<E>node = prev.next;
        prev.next = node.next;
        size--;
        return node.element;
    }

    public int indexOf(E element) {
        Node<E> node = first.next;
        if(element == null){
            for (int i = 0; i < size; i++) {
                if(node.element == null){ //返回第一个null在数组中的位置
                    return i;
                }
                node = node.next;
            }
        }else{
            for(int i = 0;i<this.size;i++) {
                if (element.equals(node.element)) { //改写为element在前,防止elements数组中被传入null元素,引起空指针
                    return i;
                }
            }
        }
        return ELEMENT_NOT_FOUND;
    }
    //写一个方法,专门用于返回给定索引的结点
    private Node<E> nodeIndex(int index){
        rangeCheck(index);
        Node<E> node = first.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        Node<E> node = first.next;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(node.element);

            node = node.next;
        }
        string.append("]");

//		Node<E> node1 = first;
//		while (node1 != null) {
//
//
//			node1 = node1.next;
//		}
        return string.toString();
    }
}
