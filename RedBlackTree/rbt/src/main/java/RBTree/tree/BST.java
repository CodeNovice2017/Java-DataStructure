package RBTree.tree;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public class BST<E> extends BinaryTree<E> {
	private Comparator<E> comparator;
	
	public BST() {
		this(null);
	}
	
	public BST(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	public void add(E element) {
		elementNotNullCheck(element);
		
		// 添加第一个节点
		if (root == null) {
			root = createNode(element, null);
			size++;

			afterAdd(root);
			return;
		}
		
		// 添加的不是第一个节点
		// 找到父节点
		Node<E> parent = root;
		Node<E> node = root;
		int cmp = 0;
		do {
			cmp = compare(element, node.element);
			parent = node;
			if (cmp > 0) {
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			} else { // 相等
				node.element = element;
				return;
			}
		} while (node != null);

		// 看看插入到父节点的哪个位置
		Node<E> newNode = createNode(element, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
		}
		size++;

		afterAdd(newNode);
	}

	// 提供一个方法(可以理解为接口的概念),供继承的子类去实现,因为我们既想实现AVL树的调整功能,
	// 又不能直接写在BST二叉搜索树中,而是想实现在AVLTree类中,因为调整只有AVL树才有,所以这样声明
	// 声明之后,将afterAdd()方法添加在add()方法对应的位置上,就实现了需求,
	// 即没有将特定的调整实现写在BST中,而是要写在AVLTree中去具体实现,而且又不影响BST,add()的原本功能,因为BST的afterAdd()没有任何逻辑
	// 添加node之后的调整,node就是新添加的一个节点
	// 这样也可以在之后红黑树中使用,红黑树有自己的调整方式
	protected void afterAdd(Node<E> node){}

	public void remove(E element) {
		remove(node(element));
	}
	protected void afterRemove(Node<E> node, Node<E> replacement){};

	public boolean contains(E element) {
		return node(element) != null;
	}
	
	private void remove(Node<E> node) {
		if (node == null)
			return;

		size--;
		// afterRemove(node)放在这里肯定是不对的,因为我们删除后调整的条件是,有一个节点真的被删除掉了
		// 但是现在这个位置afterRemove传入的node并不一定是真正被删除的节点,就是比如是度为2的节点并没有真正被删除
		// 真正被删除的是度为2的节点的前驱或者后继节点,度为2的节点只是element换为了前驱或者后继节点的element,具体可以看remove的实现笔记
		// afterRemove(node)

		if (node.hasTwoChildren()) { // 度为2的节点
			// 找到后继节点
			Node<E> s = successor(node);
			// 用后继节点的值覆盖度为2的节点的值
			node.element = s.element;
			// 删除后继节点
			node = s;
		}

		// 删除node节点（node的度必然是1或者0）
		Node<E> replacement = node.left != null ? node.left : node.right;

		if (replacement != null) { // node是度为1的节点
			// 更改parent
			replacement.parent = node.parent;
			// 更改parent的left、right的指向
			if (node.parent == null) { // node是度为1的节点并且是根节点
				root = replacement;
			} else if (node == node.parent.left) {
				node.parent.left = replacement;
			} else { // node == node.parent.right
				node.parent.right = replacement;
			}

			// 等到节点真的被删除后,并且其left,right,parent等都处理好了之后,就是完全删除维护逻辑完成后再进行失衡调整
			// 删除节点之后的处理
			afterRemove(node,replacement);
		} else if (node.parent == null) { // node是叶子节点并且是根节点
			root = null;

			// 删除节点之后的处理
			afterRemove(node,null);
		} else { // node是叶子节点，但不是根节点
			if (node == node.parent.left) {
				node.parent.left = null;
			} else { // node == node.parent.right
				node.parent.right = null;
			}

			// AVL树其实上面的两行afterRemove其实可以不用写,都统一在最后这里处理即可,但是如果想让这个BST二叉搜索树兼容后面的红黑树的话
			// 最好是这三个位置都写上,因为将来可能会传更多的参数
			// 删除节点之后的处理
			afterRemove(node,null);
		}
	}
	
	private Node<E> node(E element) {
		Node<E> node = root;
		while (node != null) {
			int cmp = compare(element, node.element);
			if (cmp == 0) return node;
			if (cmp > 0) {
				node = node.right;
			} else { // cmp < 0
				node = node.left;
			}
		}
		return null;
	}
	
	/**
	 * @return 返回值等于0，代表e1和e2相等；返回值大于0，代表e1大于e2；返回值小于于0，代表e1小于e2
	 */
	private int compare(E e1, E e2) {
		if (comparator != null) {
			return comparator.compare(e1, e2);
		}
		return ((Comparable<E>)e1).compareTo(e2);
	}
	
	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}
}
