package cs313final;

import java.util.Comparator;

/*
 * Complete this file for part 3 of the assignment
 * Note that this class shares the same name as the class from lecture, 
 * but is to be implemented using a LinkedBinaryTree heap
 * 
 * No additional classes should be imported
 */

public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
    
    private LinkedBinaryTree<Entry<K,V>> heap;
    private Comparator <K> comp;
    int size;
    
    public HeapPriorityQueue() {
        
        heap = new LinkedBinaryTree<>();
        comp = new DefaultComparator<>();
        size = 0;
        
    }
    
    @Override
    public Entry<K, V> insert(K key, V value) {
        
        this.checkKey(key);
        Entry<K,V> newEntry = new PQEntry<>(key, value);
        
        if(size == 0) {
            heap.addRoot(newEntry);
        } else {
        String binary = Integer.toBinaryString(size+1);//binary format of next position
        Position<Entry<K,V>> p = heap.root();
        
        int i = 1;
        //binary format starting from [i=1] if '0' go left if '1' go right
        for(; i < binary.length()-1; i++) {
            if(binary.charAt(i) == '0')
                p = heap.left(p);
            else
                p = heap.right(p);
        }
        
        if(binary.charAt(i) == '0')//adds depending in last digit
            p = heap.addLeft(p, newEntry);
        else
            p = heap.addRight(p, newEntry);
        
        if(size > 1)
            heapifyUp(p);
        }
        
        size++;
        
        return newEntry;
        
    }
    
    public void heapifyUp(Position<Entry<K,V>> newPos) {
        
        Position<Entry<K,V>> posParent;
        
        // p != root
        while(comp.compare(newPos.getElement().getKey(), heap.root().getElement().getKey()) != 0) { 
            posParent = heap.parent(newPos);
            K newPosKey = newPos.getElement().getKey();
            K posParentKey = posParent.getElement().getKey();
            
            //newPosKey bigger stop
            if(comp.compare(newPosKey, posParentKey) >= 0) break;
            
            //swap and keep going up
            swap(posParent, newPos);
            newPos = posParent;
        }
    }
    
    public void swap(Position<Entry<K,V>> p1, Position<Entry<K,V>> p2) {
        
        Entry<K,V> tmp = new PQEntry<>(p1.getElement().getKey(),p1.getElement().getValue());
        heap.set(p1,p2.getElement());
        heap.set(p2,tmp);
        
    }
    

    @Override
    public Entry<K, V> removeMin() {
        
        if(size == 0)
            return null;
        
        Entry<K,V> minEntry = new PQEntry<>(heap.root().getElement().getKey(),heap.root().getElement().getValue());
        Position<Entry<K,V>> lastEntry = heap.root();
        String binary = Integer.toBinaryString(size);
        
        int i = 1;
        //binary format starting from [i=1] if '0' go left if '1' go right
        for(; i <= binary.length() - 1; i++) {
            if(binary.charAt(i) == '0')
                lastEntry = heap.left(lastEntry);
            else
                lastEntry = heap.right(lastEntry);
        }
        
        swap(heap.root(), lastEntry);
        heap.remove(lastEntry);
        heapifyDown(heap.root());
        size--;
        
        return minEntry;
        
    }
    
    public void heapifyDown(Position<Entry<K,V>> p) {
        
        int tmp_size = size;
        Position<Entry<K,V>> smaller = null;
        
        while(tmp_size > 1) {
            
            if(heap.left(p) != null && heap.right(p) != null) {
                //compare left and right
                if(comp.compare(heap.left(p).getElement().getKey(),heap.right(p).getElement().getKey()) < 0)
                    smaller = heap.left(p);
                else
                    smaller = heap.right(p);
                    
            } else if (heap.left(p) != null && heap.right(p) == null) {//no right
                smaller = heap.left(p);
            } else if (heap.left(p) == null && heap.right(p) != null) {//no left
                smaller = heap.right(p);
            } else {
                break;
            }
            
            swap(smaller,p);
            p = smaller;
            tmp_size--;
        }
    }

    @Override
    public Entry<K, V> min() {
        
        if(size == 0)
            return null;
        
        return heap.root().getElement();
        
    }

    @Override
    public int size() {
        
        return heap.size();
        
    }
    public static void main (String [] args) {
    HeapPriorityQueue<Integer,String> heapPQ = new HeapPriorityQueue<>();
    heapPQ.insert(1, "one");
    heapPQ.insert(3, "three");
    heapPQ.insert(7, "seven");
    heapPQ.insert(8, "eight");
    heapPQ.insert(5, "five");
    heapPQ.insert(9, "nine");

    System.out.println("Size: " + heapPQ.size());

    Entry<Integer, String> min = heapPQ.removeMin();

    System.out.println("\nMIN: " + min.getKey() + " => " + min.getValue());


    Entry<Integer, String> min2 = heapPQ.removeMin();

    System.out.println("\nMIN: " + min2.getKey() + " => " + min2.getValue());

       System.out.println();
    for(int i = 0; i < 5; i++) {
        Entry<Integer, String> min11 = heapPQ.removeMin();
        if(min11 != null)
            System.out.println("MIN: " + min11.getKey() + " => " + min11.getValue());
        else
            System.out.println("null");
    }
}
}
