package cs313final;

import java.util.Iterator;

/*
 * This file should not be modified
 */

public abstract class AbstractMap<K,V> implements Map<K,V> {

	protected static class MapEntry<K,V> implements Entry<K,V> {

		private K key;
		private V val;
		
		public MapEntry(K key, V val) {
			this.key = key;
			this.val = val;
		}
		
		@Override
		public K getKey() {
			return this.key;
		}

		@Override
		public V getValue() {
			return this.val;
		}
		
		public void setKey(K key) {
			this.key = key;
		}
		
		public void setValue(V val) {
			this.val = val;
		}
		
	}
	
	private class ValueIterator implements Iterator<V> {

		private Iterator<Entry<K,V>> entries = AbstractMap.this.entrySet().iterator();
		
		@Override
		public boolean hasNext() {
			return this.entries.hasNext();
		}
		
		@Override
		public V next() {
			return this.entries.next().getValue();
		}
	}
	
	private class KeyIterator implements Iterator<K> {

		private Iterator<Entry<K,V>> entries = AbstractMap.this.entrySet().iterator();
		
		@Override
		public boolean hasNext() {
			return this.entries.hasNext();
		}

		@Override
		public K next() {
			return this.entries.next().getKey();
		}
		
	}
	
	private class ValueIterable implements Iterable<V> {
		@Override
		public Iterator<V> iterator() {
			return new ValueIterator();
		}
	}
	
	private class KeyIterable implements Iterable<K> {

		@Override
		public Iterator<K> iterator() {
			return new KeyIterator();
		}
		
	}
	
	@Override
	public Iterable<V> values() {
		return new ValueIterable();
	}

	@Override
	public Iterable<K> keySet() {
		return new KeyIterable();
	}
	
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
}
