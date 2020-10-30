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

    // 删除后的处理
    // 这里传入在node绝对是真正被删除的节点,
    // 因为即使是删除度为2的节点,也已经在BST的remove中完成了前驱或者后继节点的值的覆盖
    // @Override
    // protected void afterRemove(Node<E> node) {
    // // 如果删除的节点是红色
    // // 或者 用以取代删除节点的子节点是红色
    // if (isRed(node)) {
    // black(node);
    // return;
    // }

    // Node<E> parent = node.parent;
    // // 删除的是根节点
    // if (parent == null)
    // return;

    // // 删除的是黑色叶子节点【下溢】
    // // 判断被删除的node是左还是右
    // boolean left = parent.left == null || node.isLeftChild();
    // Node<E> sibling = left ? parent.right : parent.left;
    // if (left) { // 被删除的节点在左边，兄弟节点在右边
    // if (isRed(sibling)) { // 兄弟节点是红色
    // black(sibling);
    // red(parent);
    // rotateLeft(parent);
    // // 更换兄弟
    // sibling = parent.right;
    // }

    // // 兄弟节点必然是黑色
    // if (isBlack(sibling.left) && isBlack(sibling.right)) {
    // // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
    // boolean parentBlack = isBlack(parent);
    // black(parent);
    // red(sibling);
    // if (parentBlack) {
    // afterRemove(parent);
    // }
    // } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
    // // 兄弟节点的左边是黑色，兄弟要先旋转
    // if (isBlack(sibling.right)) {
    // rotateRight(sibling);
    // sibling = parent.right;
    // }

    // color(sibling, colorOf(parent));
    // black(sibling.right);
    // black(parent);
    // rotateLeft(parent);
    // }
    // } else { // 被删除的节点在右边，兄弟节点在左边
    // if (isRed(sibling)) { // 兄弟节点是红色
    // black(sibling);
    // red(parent);
    // rotateRight(parent);
    // // 更换兄弟
    // sibling = parent.left;
    // }

    // // 兄弟节点必然是黑色
    // if (isBlack(sibling.left) && isBlack(sibling.right)) {
    // // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
    // boolean parentBlack = isBlack(parent);
    // black(parent);
    // red(sibling);
    // if (parentBlack) {
    // afterRemove(parent);
    // }
    // } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
    // // 兄弟节点的左边是黑色，兄弟要先旋转
    // if (isBlack(sibling.left)) {
    // rotateLeft(sibling);
    // sibling = parent.left;
    // }

    // color(sibling, colorOf(parent));
    // black(sibling.left);
    // black(parent);
    // rotateRight(parent);
    // }
    // }
    // }

    // 删除之后的处理
    // 这里传入在node绝对是真正被删除的节点,
    // 因为即使是删除度为2的节点,也已经在BST的remove中完成了前驱或者后继节点的值的覆盖
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        // 如果删除的节点是红色
        if (isRed(node))
            return;

        // 用以取代node的子节点是红色
        if (isRed(replacement)) {
            black(replacement);
            return;
        }

        Node<E> parent = node.parent;
        // 删除的是根节点
        if (parent == null)
            return;

        // 接下来要看兄弟节点是黑是红

        // 一般我们会这么写Node<E> sibling = node.sibling();
        // 但是这其实是一个陷阱,因为afterRemove传入的node是被删除的节点,而且能来到这里的node绝对是叶子节点,并且是黑色的
        // 那么回到BST中的remove,如果删除的是叶子节点的情况的代码中,我们是将node.parent指向node这根线给断掉了
        // 因为我们是在删除之后调用afterRemove的,此时sibling()方法中调用的isLeftChild()中的parent.left已经其实被清空了
        // 所以此时再去对目前这种情况使用sibling(),isLeftChild()/isRightChild()都是失效的,因为在这里已经无法通过isLeftChild()/isRightChild()判断node是左是右了
        // 就是可以通过node.parent拿到parent,但是parent的left,right已经不再指向node了
        // 这样的话,我们可以换一个角度来看node是左是右,就是如果来到这里,remove中的node.parent.left/right = null肯定发生了
        // 那么比如node.parent.left = null,那就代表我node以前是左,那么我的兄弟就是右
        // 所以boolean left = parent.left == null来判断,返回true代表node原来是左,那么Node<E> sibling就是parent.right

        // 下面这行代码是因为,只用上面这一句还不严谨,因为有可能afterRemove(parent, null);是来自于自己这边的递归调用的
        // 就比如76-80-88的删除情况,删除88之后,80下沉合并,那么80原本的位置就空出来,此时是将80当做被删除的节点进行处理
        // 此时是将80作为parent传入afterRemove(parent,
        // null);但是此时80的parent指向80的这根left,right线可并没有断掉,
        // 也就是自己递归的情况下,boolean left = parent.left == null来判断是不靠谱的了,此时就可以使用node.isLeftChild();来判断是不是左子节点了
        
        // 删除的是黑色叶子节点【下溢】
        // 即用于取代node的子节点是黑色
        // 判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;

        // 这里不要急着判断sibling是红是黑,PPT例子中讲解都是88在右边,兄弟在左边,
        // 但实际上方向也可以是删除的节点在左边,兄弟节点在右边,这会影响兄弟那边进行左旋还是右旋,
        // 而且是绝对是对称的,就是如果兄弟在左做的是先左旋再右旋,那么反过来如果兄弟在右的话就是先右旋再左旋
        if (left) { // 被删除的节点在左边，兄弟节点在右边
            // 兄弟节点是红色,那就先转成sibling是黑色的情况,
            // 类似于remove中先处理度为2的情况,因为度为2的情况可以转成度为1或0的情况,然后统一处理的设计
            // 如果是红色,就将sibling染为黑色,parent染为红色,然后左旋
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent, null);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else { // 被删除的节点在右边，兄弟节点在左边(PPT讲义中的情况)
            // 如果是红色,就将sibling染为黑色,parent染为红色,然后右旋
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
                // 这里千万别return,还没开始正式处理呢,只是先将sibling为红色的情况转化为了sibling为黑色的情况
            }

            // 兄弟节点必然是黑色(即使是红色,也在上面已经转换完了)
            // 有人问:兄弟节点是否还可以有黑色子节点,既然走到了这里肯定是不可能的,因为如果还有黑色子节点,那么就还有更下面一层,那么删除肯定会发生在最后一层的
            // 兄弟节点的左和右子节点都是黑色,即使是left/right比如是空的(PPT的情况,就只有兄弟76),那么也是黑色的,那么就代表没有红色子节点
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                // 同时这里也不需要调整什么指向,因为本来指向就是正确的,只是调整颜色而已
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                // 如果父节点是黑色,需要递归处理父节点又下溢的情况
                // 并且此时其实相当于删除叶子节点,把它当成删除一个叶子节点之后的调整
                if (parentBlack) {
                    afterRemove(parent, null);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                // 就是PPT,向兄弟借元素的三种情况,兄弟左子节点红,右红,左右红
                // 实际上特殊的就是右红,如果右红,要先对兄弟做左旋,然后右旋
                // 另外两种情况都是直接右旋即可
                // 而这个右红的情况最特殊的就是它的左是黑的,因为左右红的情况和左红的情况的左都是红色的,所以通过isBlack(sibling.left)
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    // 旋转之后,兄弟节点变了,要给sibling重新赋值
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
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
        return new RBNode<E>(element, parent);
    }
}
