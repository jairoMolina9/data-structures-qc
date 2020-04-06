import java.util.Comparator;
public class Main {
  public static void main (String [] args) {

    /* Problem 1
    ArrayList<Integer> arr1 = new ArrayList<>(4);

    arr1.add(0,10);
    arr1.add(1,11);
    arr1.add(2,12);
    arr1.add(3,13);
    arr1.add(4,14);
    arr1.add(5,15);

    Integer [] arr2 = {5,6,7};
    try {
      System.out.println("Original: " + arr1 + "\n");
      arr1.addRange(1,arr2);
      System.out.println("addRange: " + arr1);

    } catch(Exception e) {
      System.out.println(e.getMessage());
    }

    try {
      Object[] test = arr1.removeRange(0,4);
      System.out.print("removeRange: ");
      for(int i = 0; i < test.length; i++)
        System.out.print(test[i] + ", ");
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
    */

    /*Problem 2
    CircularArrayList<Integer> cArr = new CircularArrayList<>();

    for(int i = 1; i < 11; i++)
      cArr.addFirst(i);

    System.out.println(cArr);
    System.out.println("\nFront: " + cArr.first());
    System.out.println("Rear: " + cArr.last() + "\n\n");

    cArr.addFirst(11);
    System.out.println(cArr);
    System.out.println("\nFront: " + cArr.first());
    System.out.println("Rear: " + cArr.last() + "\n\n");

    cArr.rotate();
    System.out.println(cArr);
    System.out.println("\nFront: " + cArr.first());
    System.out.println("Rear: " + cArr.last() + "\n\n");
    */
    /*Problem 3 part A
    DoublyLinkedList<Integer> dList = new DoublyLinkedList<>();

    for(int i = 1; i <= 5; i++)
      dList.addFirst(i);

    System.out.println(dList);

    LinkedIterator<Integer> iter = dList.iterator();

    int opt = -1;

    try {
      do {

        System.out.println("1. hasNext()\n2.next()\n3.remove()");
        opt = new java.util.Scanner(System.in).nextInt();
        switch(opt) {
          case 1:    System.out.println(iter.hasNext());
                  break;
          case 2: System.out.println("iter = " + iter.next());
                  break;
          case 3: iter.remove();
                  System.out.println(dList);
          default:
          break;
        }

      } while(opt != 0);

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    */
    /*Problem 3 part B
    LinkedStack<Integer> stack = new LinkedStack<>();
    for(int i = 1; i <= 5; i++)
      stack.push(i);

    System.out.println(stack);
    int x = 1;
    System.out.println("Distance of (" + x + "): " + stack.search(x));
    */

    /* Problem 4 part A
    ArrayList<Integer> arr1 = new ArrayList<>(7);
    arr1.add(0,38);
    arr1.add(1,27);
    arr1.add(2,43);
    arr1.add(3,3);
    arr1.add(4,9);
    arr1.add(5,82);
    arr1.add(6,10);

    Comparator<Integer> c = new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        if(o1 < o2) return -1;
        if(o1 > o2) return 1;
        else return 0;
      }
    };

    System.out.println(arr1);
    arr1.sort(c);
    System.out.println(arr1);
    */
    /*Problem 4 part B
    DoublyLinkedList<Integer> dList = new DoublyLinkedList<>();

    dList.addFirst(5);
    dList.addLast(8);
    dList.addLast(1);
    dList.addLast(3);
    dList.addLast(9);
    dList.addLast(6);

    Comparator<Integer> c = new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        if(o1 < o2) return 1;
        if(o1 > o2) return -1;
        else return 0;
      }
    };

    System.out.println(dList);
    dList.sort(c);
    System.out.println(dList);
    */
  }
}
