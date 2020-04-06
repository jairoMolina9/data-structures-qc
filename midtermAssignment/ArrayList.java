// package cs313midterm;

 import java.util.Iterator;
 //this import was needed to compile
 import java.util.Comparator;
 //commented import below because it wouldn't compile
 //import cs313midtermSolutions.Sortable;

public class ArrayList<E> implements List<E>, Iterable<E>, Sortable<E> {

  private class ArrayIterator implements Iterator<E> {

    private int idx = 0;
		private int lastReturned = -1;

		@Override
		public boolean hasNext() {
			return this.idx < ArrayList.this.size();
		}

		@Override
		public E next() {
			this.lastReturned = this.idx;
			this.idx++;
			return ArrayList.this.get(this.lastReturned);
		}

		@Override
		public void remove() {
			if (this.lastReturned < 0) throw new IllegalStateException();
			ArrayList.this.remove(this.lastReturned);
			this.lastReturned = -1;
		}
	}


	private Object[] data;
	private int size;

	public ArrayList() {
		this.data = new Object[10];
		this.size = 0;
	}

	public ArrayList(int capacity) {
		this.data = new Object[capacity];
		this.size = 0;
	}

	@Override
	public void set(int i, E e) {
		if (i < 0 || i >= this.size()) throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for size " + this.size());
		this.data[i] = e;
	}

	@Override
	public void add(int i, E e) {
		if (i < 0 || i > this.size()) throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for size " + this.size());
		//check if we need a larger array
		if (this.size == this.data.length) {
			this.resize(this.data.length * 2);
		}

		this.size++;

		//shift all elements from i to last element one space to the right
		for (int j = this.size - 1; j > i; j--) {
			this.data[j] = this.data[j-1];
		}

		this.data[i] = e;
	}

	@Override
	public E get(int i) {
		if (i < 0 || i >= this.size()) throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for size " + this.size());
		return (E) this.data[i];
	}

	@Override
	public E remove(int i) {
		if (i < 0 || i >= this.size()) throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for size " + this.size());
		E val = (E) this.data[i];
		for (int j = i+1; j < this.size; j++) {
			this.data[j-1] = this.data[j];
		}
		this.size--;
		return val;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	public String toString() {
		if (this.size() == 0) return "[]";

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < this.size() - 1; i++) {
			sb.append(this.get(i) + ", ");
		}
		sb.append(this.get(this.size()-1) + "]");
		return sb.toString();
	}

	@Override
	public Iterator<E> iterator() {
		return new ArrayIterator();
	}

	private void resize(int newSize) {
		Object[] newData = new Object[newSize];
		for (int i = 0; i < this.size; i++) {
			newData[i] = this.data[i];
		}
		this.data = newData;
	}

  /**
 * Inserts all of the elements in the specified array into this list, starting at the specified position.
 * Shifts the element currently at that position (if any) and any subsequent elements to the right (increases their indices).
 *
 * @param index		index at which to insert the first element from the specified collection
 * @param values	array containing elements to be added to this list
 * @throws IndexOutOfBoundsException	if the index is out of range (index < 0 || index > size())
 * @throws NullPointerException	if the specified array is null
 * O(n)
 */
	@Override
	public void addRange(int index, E[] values) throws IndexOutOfBoundsException, NullPointerException {

		if(index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException("Index out of Bounds");
		} else if (values == null) {
			throw new NullPointerException("Unspecified array");
		} else {

      int newSize = values.length + this.size;

      //O(n)
			resize(newSize);

      this.size = newSize; //manually updates this.size

      //O(n)
      //var i:	index at which to start shifting right
			for(int i = this.size-values.length-1; i >= index; i--)
				this.data[i+values.length] = this.data[i];

      //O(n)
      int x = 0;
      for(int i = index; i < values.length+index; i++)
        this.data[i] = values[x++];

		}
	}

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
 * O(n)
 */
  @Override
  public Object[] removeRange(int fromIndex, int toIndex) throws IndexOutOfBoundsException {

    if(fromIndex < 0 || fromIndex >= this.size || toIndex > this.size || toIndex < fromIndex) {
      throw new IndexOutOfBoundsException("Indexes out of bound");
    } else {

      int newSize = this.size - (toIndex - fromIndex);

      Object[] newData = new Object[newSize];

      //O(n)
      for(int i = 0; i < newSize; i++) {

        if(i < fromIndex) { //copy until fromIndex
          newData[i] = this.data[i];
        } else { //shift left succeeding  elements
          newData[i] = this.data[toIndex++];
        }

      }

      return newData;
    }
  }

  /**
	 * Sorts the elements of this structure according to the order induced by the specified comparator.
	 *
	 * @param comp	the comparator to determine the ordering of the elements
   * O(nlogn)
	 */
  @Override
  public void sort(Comparator<E> comp) {
    sortingMerge(data, 0, data.length - 1, comp);
  }

  public void sortingMerge(Object data[], int start, int end, Comparator<E> comp) {

    if (start < end) {
      int center = (start+end)/2;

      sortingMerge(data, start, center, comp); //sort start
      sortingMerge(data , center+1, end, comp); //sort end
      merge(data, start, center, end, comp);//merge
    }
  }

  public void merge(Object data[], int start, int center, int end, Comparator<E> comp) {
    //initial indexes for sub-arrays
    int idx_start = 0;
    int idx_end = 0;
    int idx_merged = start;

    //sub arrays sizes and tmp arrays
    int size_1 = center - start + 1;
    int size_2 = end - center;
    Object sub_start[] = new Object [size_1];
    Object sub_end[] = new Object [size_2];

    //fill sub arrays
    for (int i = 0; i < size_1; ++i)
      sub_start[i] = data[start + i];

    for (int k = 0; k < size_2; ++k)
    sub_end[k] = data[center + 1 + k];

    while (idx_start < size_1 && idx_end < size_2) {
      //Comparator decides order
      if( comp.compare((E) sub_start[idx_start] , (E) sub_end[idx_end]) < 0 ) {
        data[idx_merged++] = sub_start[idx_start++];
      } else {
        data[idx_merged++] = sub_end[idx_end++];
      }
    }

    //insert start and end to data
    for(;idx_start < size_1; idx_start++, idx_merged++)
      data[idx_merged] = sub_start[idx_start];

    for(;idx_end < size_2; idx_end++, idx_merged++)
      data[idx_merged] = sub_end[idx_end];
    }


}
