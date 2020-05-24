package cs313final;

/*
 * This file should not be modified
 */

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

	protected static class Node<E> implements Position<E> {
		
		private E element;
		private Node<E> parent, left, right;
		
		public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
			this.element = e;
			this.parent = p;
			this.left = l;
			this.right = r;
		}

		public E getElement() {
			return element;
		}

		public void setElement(E element) {
			this.element = element;
		}

		public Node<E> getParent() {
			return parent;
		}

		public void setParent(Node<E> parent) {
			this.parent = parent;
		}

		public Node<E> getLeft() {
			return left;
		}

		public void setLeft(Node<E> left) {
			this.left = left;
		}

		public Node<E> getRight() {
			return right;
		}

		public void setRight(Node<E> right) {
			this.right = right;
		}
		
	}
	
	protected Node<E> root;
	private int size;
	
	public LinkedBinaryTree() {
		this.root = null;
		this.size = 0;
	}
	
	protected Node<E> createNode(E e, Node<E> p, Node<E> l, Node<E> r) {
		return new Node<>(e, p, l, r);
	}
	
	protected Node<E> validate(Position<E> p) {
		if (!(p instanceof Node)) throw new IllegalArgumentException("Position is not valid type");
		Node<E> node = (Node<E>) p;
		if (node.getParent() == node) throw new IllegalArgumentException("Position is no longer in the tree");
		return node;
	}
	
	@Override
	public Position<E> left(Position<E> p) {
		Node<E> node = this.validate(p);
		return node.getLeft();
	}

	@Override
	public Position<E> right(Position<E> p) {
		Node<E> node = this.validate(p);
		return node.getRight();
	}

	@Override
	public Position<E> root() {
		return this.root; 
	}

	@Override
	public Position<E> parent(Position<E> p) {
		Node<E> node = this.validate(p);
		return node.getParent();
	}

	@Override
	public int size() {
		return size;
	}
	
	public Position<E> addRoot(E e) throws IllegalStateException {
		if (!this.isEmpty()) throw new IllegalStateException("Tree is not empty");
		this.root = this.createNode(e, null, null, null);
		this.size = 1;
		return this.root;
	}

	public Position<E> addLeft(Position<E> p, E e) {
		Node<E> node = this.validate(p);
		if (node.getLeft() != null) throw new IllegalStateException("Position already has a left child");
		Node<E> child = this.createNode(e, node, null, null);
		node.setLeft(child);
		this.size++;
		return child;
	}
	
	public Position<E> addRight(Position<E> p, E e) {
		Node<E> node = this.validate(p);
		if (node.getRight() != null) throw new IllegalStateException("Position already has a right child");
		Node<E> child = this.createNode(e, node, null, null);
		node.setRight(child);
		this.size++;
		return child;
	}
	
	public E set(Position<E> p, E e) {
		Node<E> node = this.validate(p);
		E old = node.getElement();
		node.setElement(e);
		return old;
	}
	
	public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) {
		Node<E> node = this.validate(p);
		if (this.isInternal(p)) throw new IllegalStateException("Position must be a leaf");
		
		if (t1 != null) {
			this.size += t1.size();
			
			t1.root.setParent(node);
			node.setLeft(t1.root);
			
			t1.root = null;
			t1.size = 0;
		}
		if (t2 != null) {
			this.size += t2.size();
			
			t2.root.setParent(node);
			node.setRight(t2.root);
			
			t2.root = null;
			t2.size = 0;
		}
	}
	
	public E remove(Position<E> p) {
		Node<E> node = this.validate(p);
		if (this.numChildren(p) == 2) throw new IllegalStateException("Position has two children");
		
		Node<E> child;
		if (node.getLeft() != null)
			child = node.getLeft();
		else 
			child = node.getRight();
		
		if (child != null)
			child.setParent(node.getParent());
		
		if (node == this.root)
			this.root = child;
		else {
			Node<E> parent = node.getParent();
			if (node == parent.getLeft())
				parent.setLeft(child);
			else
				parent.setRight(child);
		}
		
		this.size--;
		
		E old = node.getElement();
		node.setElement(null);
		node.setLeft(null);
		node.setRight(null);
		node.setParent(node);
		return old;
	}
	
	//example usage
	public static void main(String[] args) {
		
		//initialize empty tree
		LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
		
		Position<String> root = tree.addRoot("I'm the data at the root node");
		
		Position<String> p = tree.addLeft(root, "I'm the data at the left child of the root");
		
		System.out.println(tree.isExternal(p));
		
		LinkedBinaryTree<String> t1 = new LinkedBinaryTree<>();
		Position<String> r1 = t1.addRoot("t1 root");
		t1.addRight(r1, "t1 right child");
		
		tree.attach(p, t1, null);
		
		p = tree.left(p); //now at "t1 root"
		p = tree.right(p); //now at "t1 right child"
		
		System.out.println(p.getElement());
		
		System.out.println(tree);
	}
	
}
