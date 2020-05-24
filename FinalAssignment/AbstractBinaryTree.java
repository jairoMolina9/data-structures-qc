package cs313final;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/*
 * This file should not be modified
 */

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

	private class ElementIterator implements Iterator<E> {

		private Iterator<Position<E>> positionIterator = AbstractBinaryTree.this.positions().iterator();
		
		@Override
		public boolean hasNext() {
			return this.positionIterator.hasNext();
		}

		@Override
		public E next() {
			return this.positionIterator.next().getElement();
		}
		
		@Override
		public void remove() {
			this.positionIterator.remove();
		}
		
	}
	
	public Position<E> sibling(Position<E> p) {
		Position<E> parent = this.parent(p);
		if (parent == null) return null;
		if (p == this.left(parent)) return this.right(parent);
		else return this.left(parent);
	}
	
	public int numChildren(Position<E> p) {
		int count = 0;
		if (this.left(p) != null) count++;
		if (this.right(p) != null) count++;
		return count;
	}
	
	public Iterable<Position<E>> children(Position<E> p) {
		ArrayList<Position<E>> children = new ArrayList<>(2);
		if (this.left(p) != null) children.add(this.left(p));
		if (this.right(p) != null) children.add(this.right(p));
		return children;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		return this.preorder();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<E> iter = this.iterator();
		while(iter.hasNext()) {
			sb.append(iter.next() + ", ");
		}
		return sb.toString();		
	}
	
	public Iterable<Position<E>> preorder() {
		ArrayList<Position<E>> list = new ArrayList<>();
		preorderSubtree(this.root(), list);
		return list;
	}
	
	private void preorderSubtree(Position<E> p, ArrayList<Position<E>> list) {
		list.add(p);
		for (Position<E> c : this.children(p)) {
			preorderSubtree(c, list);
		}		
	}
	
	public Iterable<Position<E>> inorder() {
		ArrayList<Position<E>> list = new ArrayList<>();
		inorderSubtree(this.root(), list);
		return list;
	}
	
	private void inorderSubtree(Position<E> p, ArrayList<Position<E>> list) {
		if (this.left(p) != null)
			inorderSubtree(this.left(p), list);
		list.add(p);
		if (this.right(p) != null)
			inorderSubtree(this.right(p), list);
	}
	
	public Iterable<Position<E>> breadthfirst() {
		ArrayList<Position<E>> list = new ArrayList<>();
		if (!this.isEmpty()) {
			LinkedList<Position<E>> q = new LinkedList<>();
			q.addLast(this.root());
			while (!q.isEmpty()) {
				Position<E> p = q.removeFirst();
				list.add(p);
				for (Position<E> c : this.children(p))
					q.addLast(c);
			}
		}
		return list;
	}
	
}
