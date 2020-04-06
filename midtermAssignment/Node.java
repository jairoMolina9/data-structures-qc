// package cs313midterm;

public class Node<E> {

	private E data;
	private Node<E> next, prev;

	public Node(E data) {
		this.data = data;
		this.next = null;
		this.prev = null;
	}

	public Node(E data, Node<E> prev, Node<E> next) {
		this.data = data;
		this.prev = prev;
		this.next = next;
	}

	public E getData() {
		return this.data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public Node<E> getNext() {
		return this.next;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}

	public Node<E> getPrev() {
		return this.prev;
	}

	public void setPrev(Node<E> prev) {
		this.prev = prev;
	}

}
