package cs313final;

import java.util.Comparator;

/*
 * This file should not be modified
 */

public class DefaultComparator<T> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) throws ClassCastException {
		return ((Comparable<T>) o1).compareTo(o2);
	}

}
