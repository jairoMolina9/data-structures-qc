// package cs313midterm;

import java.util.Comparator;

public interface Sortable<E> {

	/**
	 * Sorts the elements of this structure according to the order induced by the specified comparator.
	 *
	 * @param comp	the comparator to determine the ordering of the elements
	 */
	void sort(Comparator<E> comp);

}
