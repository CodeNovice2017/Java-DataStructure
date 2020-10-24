package avl.tree;

import java.util.Comparator;

public class AVLTree<E> extends BST<E> {

  public AVLTree() {
    this(null);
  }

  public AVLTree(Comparator<E> comparator) {
    super(comparator);
  }



  private static class AVLNode<E> extends Node<E> {
    // 新添加的节点必然是叶子节点,它的高度暂时都是1,刚创建出来高度为1
    int height = 1;

    public AVLNode(E element, Node<E> parent) {
      super(element, parent);
    }

    // 获取节点的平衡因子
    public int balanceFactor(){
      // 这里直接left.height是会报错的,因为BinaryTree中Node类的left和right都是Node类型,而不是AVLNode类型
      // 所以虽然我们知道这个left肯定是AVLNode(因为add方法时构建AVL树都是创建的AVLNode),但依然需要强制转换,向下转型
      int leftHeight = left == null? 0 : ((AVLNode<E>)left).height;
      int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
      return leftHeight - rightHeight;
    }

    // 每个节点都维护一个自己的高度,一旦添加新的节点,因为新的节点肯定是叶子节点,我们需要更新的就是叶子节点一直向上走的父节点和祖先节点
    // 所以每一次add()节点之后,都会在afterAdd()中更新高度,高度即为左右子树高度最大的加1,因为每次afterAdd中都会对节点是否平衡判断
    // 所以不需要担心如果刚好是加在左子树上,而右子树高度比左子树高1,那么求max不就是相当于本来不应该添加高度,但是多添加了高度吗?
    // 上面这样的疑虑是错误的,因为每次afterAdd都会判断该节点是否平衡,平衡的话就不会更新高度了
    public void updateHeight() {
      int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
      int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
      height = 1 + Math.max(leftHeight, rightHeight);
    }

    public Node<E> tallerChild() {
      int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
      int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
      if (leftHeight > rightHeight)
        return left;
      if (leftHeight < rightHeight)
        return right;
      // 如果左右子树高度相等,那么我们设置一个规矩,返回和父节点同方向的子节点
      // 这句代码的意思就是如果我自己是父节点的左子节点那就返回我的左子节点,否则返回右
      return isLeftChild() ? left : right;
    }

    @Override
    public String toString() {
      String parentString = "null";
      if (parent != null) {
        parentString = parent.element.toString();
      }
      return element + "_p(" + parentString + ")_h(" + height + ")";
    }
  }



  // 用于失衡调整
  // BST的add()方法内添加了两处afterAdd()
  @Override
  protected void afterAdd(Node<E> node) {
    while ((node = node.parent) != null) {
      if (isBalanced(node)) {
        // 更新高度
        // 既然反正要在这里执行while循环,那么直接在while循环中直接把更新高度的事情做了,就可以不需要递归去更新高度了
        updateHeight(node);
      } else {
        // 恢复平衡
        rebalance(node);
        // 整棵树恢复平衡
        break;
      }
    }
  }

  @Override
  protected Node<E> createNode(E element, Node<E> parent) {
    return new AVLNode<E>(element,parent);
  }

  // 判断是否平衡
  private boolean isBalanced(Node<E> node) {
    return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
  }

  // 这样多封装一层的意义在于将强制转换封装起来,那么afterAdd的逻辑中就不会显得特别乱
  private void updateHeight(Node<E> node) {
    ((AVLNode<E>) node).updateHeight();
  }

  // 恢复平衡
  // 传入的节点实际就是高度最低的那个失衡节点,就是afterAdd一层一层向上判断失衡isBalanced的第一个失衡的节点,笔记中的g
  private void rebalance(Node<E> grand) {
    // 对应笔记的p
    Node<E> parent = ((AVLNode<E>) grand).tallerChild();
    // 对应笔记的n
    Node<E> node = ((AVLNode<E>) parent).tallerChild();
    if (parent.isLeftChild()) { // L
      if (node.isLeftChild()) { // LL
        rotate(grand, node, node.right, parent, parent.right, grand);
      } else { // LR
        rotate(grand, parent, node.left, node, node.right, grand);
      }
    } else { // R
      if (node.isLeftChild()) { // RL
        rotate(grand, grand, node.left, node, node.right, parent);
      } else { // RR
        rotate(grand, grand, parent.left, parent, node.left, node);
      }
    }
  }

  private void rotate(Node<E> r, // 子树的根节点
      Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f) {
    // 让d成为这棵子树的根节点
    d.parent = r.parent;
    if (r.isLeftChild()) {
      r.parent.left = d;
    } else if (r.isRightChild()) {
      r.parent.right = d;
    } else {
      root = d;
    }

    // b-c
    b.right = c;
    if (c != null) {
      c.parent = b;
    }
    updateHeight(b);

    // e-f
    f.left = e;
    if (e != null) {
      e.parent = f;
    }
    updateHeight(f);

    // b-d-f
    d.left = b;
    d.right = f;
    b.parent = d;
    f.parent = d;
    updateHeight(d);
  }

  private void rotateLeft(Node<E> grand) {
    Node<E> parent = grand.right;
    Node<E> child = parent.left;
    grand.right = child;
    parent.left = grand;
    afterRotate(grand, parent, child);
  }

  private void rotateRight(Node<E> grand) {
    Node<E> parent = grand.left;
    Node<E> child = parent.right;
    grand.left = child;
    parent.right = grand;
    afterRotate(grand, parent, child);
  }

  private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
    // 让parent称为子树的根节点
    parent.parent = grand.parent;
    if (grand.isLeftChild()) {
      grand.parent.left = parent;
    } else if (grand.isRightChild()) {
      grand.parent.right = parent;
    } else { // grand是root节点
      root = parent;
    }

    // 更新child的parent
    if (child != null) {
      child.parent = grand;
    }

    // 更新grand的parent
    grand.parent = parent;

    // 更新高度
    updateHeight(grand);
    updateHeight(parent);
  }

  /**
   * 恢复平衡
   * 
   * @param grand 高度最低的那个不平衡节点
   */
  @SuppressWarnings("unused")
  private void rebalance2(Node<E> grand) {
    Node<E> parent = ((AVLNode<E>) grand).tallerChild();
    Node<E> node = ((AVLNode<E>) parent).tallerChild();
    if (parent.isLeftChild()) { // L
      if (node.isLeftChild()) { // LL
        rotateRight(grand);
      } else { // LR
        rotateLeft(parent);
        rotateRight(grand);
      }
    } else { // R
      if (node.isLeftChild()) { // RL
        rotateRight(parent);
        rotateLeft(grand);
      } else { // RR
        rotateLeft(grand);
      }
    }
  }

  // 失衡调整其实和afterAdd是一样的,只不过afterAdd添加节点的失衡调整只需要更改一次即可
  // 因为就是只需要向上层找到第一个失衡的节点调整平衡即可,但是remove引起的失衡要一直向上调整,判断所有的祖父节点
  // 所以只比afterAdd少一个break
  @Override
  protected void afterRemove(Node<E> node) {
    while ((node = node.parent) != null) {
      if (isBalanced(node)) {
        // 更新高度
        updateHeight(node);
      } else {
        // 恢复平衡
        rebalance(node);
      }
    }
  }

}
