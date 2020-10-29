package RBTree.tree;

import java.util.Comparator;

import RBTree.tree.BST;

public class RBTree<E> extends BBST<E> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    private static class RBNode<E> extends Node<E> {
        public RBNode(E element, Node<E> parent) {
            super(element, parent);
            // TODO Auto-generated constructor stub
        }

        // 颜色可以用int类型,0就红色,1就黑色,其实也可以使用boolean类型,真是黑色,假是红色
        boolean color = RED;

        @Override
        public String toString() {
            String str = "";
            if (color == RED) {
                str = "R_";
            }
            return str + element.toString();
        }
    }

    // 添加之后的处理
    // 这是最能看懂逻辑的代码了,如果按照讲师的代码其实是将一些各种情况中都要进行的操作进行了统一的操作,
    // 统一放到前面或者后面,虽然代码简洁了,但是逻辑上更难看懂
    @Override
    protected void afterAdd(Node<E> node) {
        // afterAdd()中传入的是新添加的节点
        // 先拿到父节点
        Node<E> parent = node.parent;

        // 新添加的节点是根节点或者上溢到达了根节点
        // 这行代码绝对不能剪到后面去,因为colorOf()方法的逻辑
        if (parent == null) {
            // 根节点直接染色即可
            black(node);
            return;
        }

        // 无需做任何处理的4种情况
        // 如果父节点是黑色,直接返回
        if (isBlack(parent)) {
            return;
        }

        // uncle节点是RED的4种情况
        // 上溢的情况:只需要染色
        // 然后当做是新添加节点向上合并再次判断12中情况之一,然后递归调用afterAdd()
        // 有可能有疑惑:上溢的最坏情况,导致不断上溢的根节点,最后要拿出一个节点,成为一个新的根节点(而且此刻肯定不会再发生上溢了,B树长高了一层)
        // 祖父节点
        Node<E> grand = parent.parent;
        // 叔父节点
        Node<E> uncle = parent.sibling();
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            // 祖父节点当做新添加的节点
            // 逻辑就是先染为红色,然后调用afterAdd
            // 这里就利用了下面color(node.parent,RED)所说的逻辑,就是直接方法本身就有返回这个染完色的节点,方法不是void的意义就在这里
            afterAdd(red(grand));
            return;
        }

        // uncle节点不是RED的四种情况
        // 旋转+染色,旋转代码和AVLTree一模一样
        // 为了理解清晰,最好不要使用找规律那一种,还是使用rotateLeft,rotateRight这种
        // LL/RR情况
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                // LL情况下,parent染成黑色,grand染为红色
                black(parent);
                red(grand);
                // 祖父节点进行右旋
                rotateRight(grand);
            } else { // LR
                     // 自己染成黑色
                     // grand染成红色
                black(node);
                red(grand);
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else { // R
            if (node.isLeftChild()) { // RL
                // 自己染成黑色
                // grand染成红色
                black(node);
                red(grand);
                rotateRight(parent);
                rotateLeft(grand);
            } else { // RR
                black(parent);
                red(grand);
                // 祖父节点进行左旋
                rotateLeft(grand);
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        // TODO Auto-generated method stub
        super.afterRemove(node);
    }

    // 包括染色,原本红色染成黑色,原本黑色染成红色
    // 既然牵扯到颜色相关的操作,并且原本afterAdd()和afterRemove()传入的是Node类型,
    // Node类型不认识color,只能将其强转为RBNode,然后拿到颜色属性,那么干脆封装一下
    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) {
            return node;
        }
        ((RBNode<E>) node).color = color;
        return node;
    }
    // 通过上面这样的封装,比如遇到如下场景,相对某节点的parent进行染色,那么就可以使用
    // color(node.parent,RED)
    // 同时染完色的同时,想要拿到这个染了色的节点做事情,
    // 通过这样的封装,我们就可以直接在染色color()之后,直接获得这个被染了色的Node
    // 直接通过Node<E> parent = color(node.parent,RED);
    // 这样就实现了染色的同时又把它赋值给某个变量,定义变量的同时染了色

    // 再多一层封装
    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    // 空节点在红黑树中意味着是黑色,所以如果位空节点就返回黑色,否则返回这个节点的颜色
    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        // TODO Auto-generated method stub
        return new RBNode<E>(element,parent);
    }
}
