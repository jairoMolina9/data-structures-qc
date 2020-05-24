package cs313final;

/* 
 * This file should not be modified
 */

public interface Map<K,V> {
	
	V put (K key, V val);
	
	V get (K key);
	
	V remove (K key);
	
	Iterable<V> values();
	
	Iterable<K> keySet();
	
	Iterable<Entry<K,V>> entrySet();
	
	int size();
	
	boolean isEmpty();
	
}
