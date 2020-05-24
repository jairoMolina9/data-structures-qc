package cs313final;

import java.util.Comparator;

/*
 * This file should not be modified
 */

public abstract class AbstractPriorityQueue<K,V> implements PriorityQueue<K,V> {

	protected static class PQEntry<K,V> implements Entry<K,V> {

		private K key;
		private V value;
		
		public PQEntry(K k, V v) {
			this.key = k;
			this.value = v;
		}
		
		@Override
		public K getKey() {
			return this.key;
		}

		@Override
		public V getValue() {
			return this.value;
		}
		
		public void setKey(K k) {
			this.key = k;
		}
		
		public void setValue(V v) {
			this.value = v;
		}
	}
	
	private Comparator<K> comp;
	
	public AbstractPriorityQueue(Comparator<K> comp) {
		this.comp = comp;
	}
	
	public AbstractPriorityQueue() {
		this.comp = new DefaultComparator<>();
	}
	
	protected int compare(Entry<K,V> a, Entry<K,V> b) {
		return this.comp.compare(a.getKey(), b.getKey());
	}
	
	protected boolean checkKey(K key) throws IllegalArgumentException {
		try {
			return this.comp.compare(key, key) == 0;
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Invalid key");
		}
	}
	
	public boolean isEmpty() {
		return this.size() == 0;
	}
}
