// package cs313midterm;

import java.util.NoSuchElementException;

public class CircularArrayList<E> implements CircularList<E> {

	private Object[] data;
	private int front, rear, size;

	public CircularArrayList() {
		this(10);
	}

	public CircularArrayList(int capacity) {
		data = new Object[capacity];
		front = -1;
		rear = -1;
		size = 0;
	}

	/**
 * Creates a new array with the given capacity and copies all of the elements from this list into that array.
 * Used when the capacity of this list's array is no longer sufficient for the number of elements.
 *
 * @param newSize	new capacity of the underlying array
 *
 * O(n)
 */
	private void resize(int newCapacity) {

		Object[] newData = new Object[newCapacity];

		int new_idx = 0; //newData index
		int old_idx = front; //oldData index

		boolean end = false; //rear reached

		while(!end) {
			end = ((old_idx % data.length) == rear);
			newData[new_idx] = data[old_idx % data.length];

			old_idx++;
			new_idx++;
		}

		front = 0;
		rear = size - 1;
		data = newData;
	}

	/**
 * Retrieves, but does not remove, the first element of this list, or returns null if this list is empty.
 *
 * @return the first element of this list, or null if this list is empty
 * O(1)
 */
	@Override
	public E first() {
		if (this.isEmpty()) return null;

		return (E) data[front];
	}

	/**
 * Retrieves, but does not remove, the last element of this list, or returns null if this list is empty.
 *
 * @return the last element of this list, or null if this list is empty
 * O(1)
 */
	@Override
	public E last() {
		if(this.isEmpty()) return null;

		return (E) data[rear];
	}

	/**
	 * Inserts the specified element at the beginning of this list.
	 *
	 * @param e	the element to add
	 * O(1)
	 */
	@Override
	public void addFirst(E e) {
		if (size == data.length)
			resize(data.length * 2);

		if (front == -1) {//check if empty
			front = 0;
			rear = 0;
		} else if (front == 0) {
			front = data.length - 1;
		} else {
			front = (front - 1) % data.length;
		}

		data[front] = e ;

		size++;
	}

	/**
	 * Inserts the specified element at the end of this list.
	 *
	 * @param e	the element to add
	 *
	 * O(1)
	 */
	@Override
	public void addLast(E e) {
		if (size == data.length)
			resize(data.length * 2);

		//sets front first index if empty
		if(front == -1)
			front = 0;

		rear = (rear + 1) % data.length;
		data[rear] = e;

		size++;
	}

	/**
 * Removes and returns the first element from this list.
 *
 * @return	the first element from this list
 * @throws NoSuchElementException	if this list is empty
 * O(1)
 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if (this.isEmpty()) throw new NoSuchElementException("List is Empty");

		E element = (E) data[front];

		if(front == rear) {
			front = rear = -1;
		} else {
			front = (front+1) % data.length;
		}

		size--;

		return element;
	}

	/**
 * Returns the number of elements in this list.
 *
 * @return	the number of elements in this list
 * O(1)
 */
	@Override
	public int size() {
		return size;
	}

	/**
 * Returns true if this collection contains no elements.
 *
 * @return	true if this collection contains no elements
 * O(1)
 */
	@Override
	public boolean isEmpty() {
		 return (size == 0);
	}

	/**
	 * Shifts the first element in this list to the end of this list, or does nothing if this list is empty

	 O(1)
	 */
	@Override
	public void rotate() {
		if(isEmpty()) return;

		this.addLast((E) data[front]);
		this.removeFirst();
	}

	//debugging
	public String toString() {
		int num = front;

		String temp = "Front ";

        for(int i = 0; i < size; i++) {
            temp += "[" + num + "] = " + data[num] +" --> ";

            num = (num + 1) % data.length;
        }

				temp += "Rear";

				return temp;
	}

}
