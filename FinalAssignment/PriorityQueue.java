package cs313final;

/*
 * This file should not be modified
 */

public interface PriorityQueue<K,V> {

	Entry<K,V> insert(K key, V value);
	
	Entry<K,V> removeMin();
	
	Entry<K,V> min();
	
	int size();
	
	boolean isEmpty();
	
}
