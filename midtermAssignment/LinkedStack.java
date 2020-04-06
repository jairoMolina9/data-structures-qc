// package cs313midterm;

import java.util.Iterator;

public class LinkedStack<E> implements Stack<E> {

	private DoublyLinkedList<E> data = new DoublyLinkedList<>();

	@Override
	public void push(E e) {
		this.data.addFirst(e);
	}

	@Override
	public E pop() {
		return this.data.removeFirst();
	}

	@Override
	public E top() {
		return this.data.first();
	}

	@Override
	public int size() {
		return this.data.size();
	}

	@Override
	public boolean isEmpty() {
		return this.data.isEmpty();
	}

	/**
	 * Returns the 1-based position where an object is on this stack.
	 * If the object o occurs as an item in this stack, this method returns the distance from the top of the stack of the occurrence nearest the top of the stack;
	 * the topmost item on the stack is considered to be at distance 1.
	 *
	 * @param o	the desired object
	 * @return the 1-based position from the top of the stack where the object is located; the return value -1 indicates that the object is not on the stack
	 */
	 @Override
	public int search(Object o) {

		int dist = -1;
		LinkedIterator<E> iter = this.data.iterator();

		int x = 1;
		while(iter.hasNext()) {
			if(iter.next() == o) {
				dist = x;
				break;
			} else {
				x++;
			}

		}

		return dist;

	}

	public String toString() {
		return this.data.toString();
	}

}
