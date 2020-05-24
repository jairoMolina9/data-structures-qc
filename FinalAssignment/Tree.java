package cs313final;

import java.util.Iterator;

/*
 * This file should not be modified
 */

public interface Tree<E> extends Iterable<E> {
	
	Position<E> root();
	Position<E> parent(Position<E> p);
	Iterable<Position<E>> children(Position<E> p);
	
	int numChildren(Position<E> p);
	
	boolean isInternal(Position<E> p);
	boolean isExternal(Position<E> p);
	boolean isRoot(Position<E> p);
	
	int size();
	boolean isEmpty();
	Iterator<E> iterator();
	Iterable<Position<E>> positions();
	
	int depth(Position<E> p);
	int height(Position<E> p);
	
}
