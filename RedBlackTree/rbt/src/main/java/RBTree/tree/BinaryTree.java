package RBTree.tree;

import java.util.LinkedList;
import java.util.Queue;

import RBTree.printer.BinaryTreeInfo;

@SuppressWarnings("unchecked")
public class BinaryTree<E> implements BinaryTreeInfo {
	protected int size;
	protected Node<E> root;
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		root = null;
		size = 0;
	}
	
	public void preorder(Visitor<E> visitor) {
		if (visitor == null) return;
		preorder(root, visitor);
	}
	
	private void preorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.stop) return;
		
		visitor.stop = visitor.visit(node.element);
		preorder(node.left, visitor);
		preorder(node.right, visitor);
	}
	
	public void inorder(Visitor<E> visitor) {
		if (visitor == null) return;
		inorder(root, visitor);
	}
	
	private void inorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.stop) return;
		
		inorder(node.left, visitor);
		if (visitor.stop) return;
		visitor.stop = visitor.visit(node.element);
		inorder(node.right, visitor);
	}
	
	public void postorder(Visitor<E> visitor) {
		if (visitor == null) return;
		postorder(root, visitor);
	}
	
	private void postorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.stop) return;
		
		postorder(node.left, visitor);
		postorder(node.right, visitor);
		if (visitor.stop) return;
		visitor.stop = visitor.visit(node.element);
	}
	
	public void levelOrder(Visitor<E> visitor) {
		if (root == null || visitor == null) return;
		
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			if (visitor.visit(node.element)) return;
			
			if (node.left != null) {
				queue.offer(node.left);
			}
			
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
	}
	
	public boolean isComplete() {
		if (root == null) return false;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		
		boolean leaf = false;
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			if (leaf && !node.isLeaf()) return false;

			if (node.left != null) {
				queue.offer(node.left);
			} else if (node.right != null) {
				return false;
			}
			
			if (node.right != null) {
				queue.offer(node.right);
			} else { // 后面遍历的节点都必须是叶子节点
				leaf = true;
			}
		}
		
		return true;
	}
	
	public int height() {
		if (root == null) return 0;
		
		// 树的高度
		int height = 0;
		// 存储着每一层的元素数量
		int levelSize = 1;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			levelSize--;
			
			if (node.left != null) {
				queue.offer(node.left);
			}
			
			if (node.right != null) {
				queue.offer(node.right);
			}

			if (levelSize == 0) { // 意味着即将要访问下一层
				levelSize = queue.size();
				height++;
			}
		}
		
		return height;
	}
	
	public int height2() {
		return height(root);
	}
	
	private int height(Node<E> node) {
		if (node == null) return 0;
		return 1 + Math.max(height(node.left), height(node.right));
	}

	// 和BST中添加的调整方法afterAdd一样的原理,实现一个创建节点的方法,因为如果不实现这个方法的话,
	// 虽然我们在AVLTree中声明了一个AVLNode继承Node,但是BST的add方法中原本是直接new Node<E>(element,parent)的,
	// 那样的话就用不上AVLNode了,也就无法在添加的时候设置height
	// 修改`root = new Node<>(element, null);`为`root = createNode(element,null);`
	// 以及`Node<E> newNode = createNode(element, parent);`
	// 这样就实现了只使用BinaryTree的add()方法,实现了创建BST树时,使用默认的Node,而使用AVLTree时就使用AVLNode
	// 这样也可以在之后红黑树中使用,红黑树也有自己的节点
	protected Node<E> createNode(E element,Node<E> parent){
		return new Node<E>(element,parent); 
	}

	protected Node<E> predecessor(Node<E> node) {
		if (node == null) return null;
		
		// 前驱节点在左子树当中（left.right.right.right....）
		Node<E> p = node.left;
		if (p != null) {
			while (p.right != null) {
				p = p.right;
			}
			return p;
		}
		
		// 从父节点、祖父节点中寻找前驱节点
		while (node.parent != null && node == node.parent.left) {
			node = node.parent;
		}

		// node.parent == null
		// node == node.parent.right
		return node.parent;
	}
	
	protected Node<E> successor(Node<E> node) {
		if (node == null) return null;
		
		// 前驱节点在左子树当中（right.left.left.left....）
		Node<E> p = node.right;
		if (p != null) {
			while (p.left != null) {
				p = p.left;
			}
			return p;
		}
		
		// 从父节点、祖父节点中寻找前驱节点
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}

		return node.parent;
	}

	public static abstract class Visitor<E> {
		boolean stop;
		/**
		 * @return 如果返回true，就代表停止遍历
		 */
		abstract boolean visit(E element);
	}
	
	protected static class Node<E> {
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;
		// height应该是AVL树才有的概念,不应该声明在二叉树类中,
		// 因为如果只是需要二叉树的Node,那么height就会造成额外的空间浪费
		// 所以我们的做法是在AVL中声明一个AVLNode继承该Node
		// int height;
		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}
		
		public boolean isLeaf() {
			return left == null && right == null;
		}
		
		public boolean hasTwoChildren() {
			return left != null && right != null;
		}
		
		public boolean isLeftChild() {
			return parent != null && this == parent.left;
		}

		public boolean isRightChild() {
			return parent != null && this == parent.right;
		}

		// 返回兄弟节点
		// 如果我自己是父节点的左子节点,那么我的兄弟节点就是父节点的右边
		public Node<E> sibling() {
			if (isLeftChild()) {
				return parent.right;
			}

			if (isRightChild()) {
				return parent.left;
			}
			// 我既不是左子节点又不是右子节点,那么说明我自己是根节点,那么我没有兄弟节点
			return null;
		}
		// uncle节点就不用单独写个方法了,因为直接用parent.sibling()即可获取到叔父节点

	}

	@Override
	public Object root() {
		return root;
	}

	@Override
	public Object left(Object node) {
		return ((Node<E>)node).left;
	}

	@Override
	public Object right(Object node) {
		return ((Node<E>)node).right;
	}

	@Override
	public Object string(Object node) {
		return node;
	}
}
