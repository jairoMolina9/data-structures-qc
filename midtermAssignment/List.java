// package cs313midterm;

public interface List<E> {

	
	void set(int i, E e) throws IndexOutOfBoundsException;

	void add(int i, E e) throws IndexOutOfBoundsException;

	E get(int i) throws IndexOutOfBoundsException;

	E remove(int i) throws IndexOutOfBoundsException;

	int size();

	boolean isEmpty();


	/**
	 * Inserts all of the elements in the specified array into this list, starting at the specified position.
	 * Shifts the element currently at that position (if any) and any subsequent elements to the right (increases their indices).
	 *
	 * @param index		index at which to insert the first element from the specified collection
	 * @param values	array containing elements to be added to this list
	 * @throws IndexOutOfBoundsException	if the index is out of range (index < 0 || index > size())
	 * @throws NullPointerException	if the specified array is null
	 */
	void addRange(int index, E[] values) throws IndexOutOfBoundsException, NullPointerException;


	/**
	 * Removes from this list all of the elements whose index is between fromIndex, inclusive, and toIndex, exclusive.
	 * Shifts any succeeding elements to the left (reduces their index).
	 * This call shortens the list by (toIndex - fromIndex) elements.
	 * (If toIndex==fromIndex, this operation has no effect.)
	 *
	 * @param fromIndex	index of first element to be removed
	 * @param toIndex	index after last element to be removed
	 * @return an array containing the removed values
	 * @throws IndexOutOfBoundsException	if fromIndex or toIndex is out of range (fromIndex < 0 || fromIndex >= size() || toIndex > size() || toIndex < fromIndex)
	 */
	Object[] removeRange(int fromIndex, int toIndex) throws IndexOutOfBoundsException;

}
