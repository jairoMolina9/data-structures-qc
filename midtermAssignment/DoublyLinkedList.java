// package cs313midterm;

import java.util.Comparator;
import java.util.Iterator;

public class DoublyLinkedList<E> implements Iterable<E>, Sortable<E> {

	private Node<E> header, trailer;
	private int size;

	public DoublyLinkedList() {
		this.size = 0;
		this.header = new Node<>(null, null, null);
		this.trailer = new Node<>(null, null, null);
		this.header.setNext(this.trailer);
		this.trailer.setPrev(this.header);
	}

	private void addBetween(E data, Node<E> prev, Node<E> next) {
		Node<E> newNode = new Node<>(data, prev, next);
		prev.setNext(newNode);
		next.setPrev(newNode);
		this.size++;
	}

	private E remove(Node<E> n) {
		Node<E> prev = n.getPrev();
		Node<E> next = n.getNext();
		prev.setNext(next);
		next.setPrev(prev);
		this.size--;
		return n.getData();
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public E first() {
		return this.header.getNext().getData();
	}

	public E last() {
		return this.trailer.getPrev().getData();
	}

	public void addFirst(E data) {
		this.addBetween(data, this.header, this.header.getNext());
	}

	public void addLast(E data) {
		this.addBetween(data, this.trailer.getPrev(), this.trailer);
	}

	public E removeFirst() {
		if (this.isEmpty()) return null;
		return this.remove(this.header.getNext());
	}

	public E removeLast() {
		if (this.isEmpty()) return null;
		return this.remove(this.trailer.getPrev());
	}


	public String toString() {
		if (this.isEmpty()) return "[]";

		StringBuilder sb = new StringBuilder();
		sb.append("[");

		Node<E> current = this.header.getNext();

		while(current.getNext() != this.trailer) {
			sb.append(String.format("%s, ", current.getData()));
			current = current.getNext();
		}
		sb.append(String.format("%s]", current.getData()));;

		return sb.toString();
	}
	/**
 * Sorts the elements of this structure according to the order induced by the specified comparator.
 *
 * @param comp	the comparator to determine the ordering of the elements
 * O(n^2) insertion sort
 */
	@Override
	public void sort(Comparator<E> comp) {
		Node<E> current = this.header.getNext();

		while(current != this.trailer) {

			Node<E> move_left = current.getPrev();
			Node<E> tmp_curr = current;

			E curr_data = (E) current.getData();

			while(move_left.getPrev() != null) {

				if(comp.compare( (E) move_left.getData(), curr_data) < 0) {

					E biggerNum = (E) move_left.getData();

					move_left.setData(curr_data);
					tmp_curr.setData(biggerNum);

					tmp_curr = move_left;
					move_left = move_left.getPrev();
				} else {
					break;
				}
			}
			current = current.getNext();
		}
	}


	//Method required by Iterable interface
	public LinkedIterator<E> iterator() {
		return new LinkedIterator<>(this.header);
	 }
}
