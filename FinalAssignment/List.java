package cs313final;

/*
 * This file should not be modified
 */

public interface List<E> {

	void set(int i, E e);
	
	void add(int i, E e);
	
	E get(int i);
	
	E remove(int i);
	
	int size();
	
	boolean isEmpty();
	
}
