// package cs313midterm;

public interface Stack<E> {

	void push(E e);

	E pop();

	E top();

	int size();

	boolean isEmpty();


	/**
	 * Returns the 1-based position where an object is on this stack.
	 * If the object o occurs as an item in this stack, this method returns the distance from the top of the stack of the occurrence nearest the top of the stack;
	 * the topmost item on the stack is considered to be at distance 1.
	 *
	 * @param o	the desired object
	 * @return the 1-based position from the top of the stack where the object is located; the return value -1 indicates that the object is not on the stack
	 */
	int search(Object o);

}
