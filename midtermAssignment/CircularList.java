// package cs313midterm;

import java.util.NoSuchElementException;

public interface CircularList<E> {


	/**
	 * Retrieves, but does not remove, the first element of this list, or returns null if this list is empty.
	 *
	 * @return the first element of this list, or null if this list is empty
	 */
	E first();


	/**
	 * Retrieves, but does not remove, the last element of this list, or returns null if this list is empty.
	 *
	 * @return the last element of this list, or null if this list is empty
	 */
	E last();


	/**
	 * Inserts the specified element at the beginning of this list.
	 *
	 * @param e	the element to add
	 */
	void addFirst(E e);


	/**
	 * Inserts the specified element at the end of this list.
	 *
	 * @param e	the element to add
	 */
	void addLast(E e);


	/**
	 * Removes and returns the first element from this list.
	 *
	 * @return	the first element from this list
	 * @throws NoSuchElementException	if this list is empty
	 */
	E removeFirst() throws NoSuchElementException;


	/**
	 * Returns the number of elements in this list.
	 *
	 * @return	the number of elements in this list
	 */
	int size();


	/**
	 * Returns true if this collection contains no elements.
	 *
	 * @return	true if this collection contains no elements
	 */
	boolean isEmpty();


	/**
	 * Shifts the first element in this list to the end of this list, or does nothing if this list is empty
	 */
	void rotate();

}
