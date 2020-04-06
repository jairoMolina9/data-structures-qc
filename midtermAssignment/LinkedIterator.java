// package cs313midterm;
import java.util.Iterator;

public class LinkedIterator<E> implements Iterator<E> {

  private Node<E> cursor;
  private Node<E> lastReturned;

  public LinkedIterator(Node<E> header) {
    cursor = header.getNext();
    lastReturned = null;
  }

  @Override
  public boolean hasNext() {
    return this.cursor.getNext() != null;
  }

  @Override
  public E next() {

    if(!hasNext())
       throw new java.util.NoSuchElementException("No next");

    this.lastReturned = this.cursor;
    this.cursor = this.cursor.getNext();

    return this.lastReturned.getData();
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("Can't remove");

    /*
    //This code removes work successfully
    //but does not update the size

    if(lastReturned.getPrev() == null) {
      cursor = lastReturned.getNext();
      lastReturned.setPrev(null);
    } else {
      Node<E> tmp = lastReturned.getNext();

      tmp.setPrev(lastReturned.getPrev());
      lastReturned.getPrev().setNext(tmp);

      cursor = tmp;
    }
    */

  }

}
