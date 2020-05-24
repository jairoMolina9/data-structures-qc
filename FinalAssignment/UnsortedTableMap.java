package cs313final;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * This file should not be modified
 */

public class UnsortedTableMap<K,V> extends AbstractMap<K,V> {
	
	private class EntryIterable implements Iterable<Entry<K,V>> {
		@Override
		public Iterator<Entry<K, V>> iterator() {
			return new EntryIterator();
		}
	}
	
	private class EntryIterator implements Iterator<Entry<K,V>> {

		private int idx = 0;
		
		@Override
		public boolean hasNext() {
			return this.idx < UnsortedTableMap.this.table.size();
		}

		@Override
		public Entry<K, V> next() {
			if (this.idx == UnsortedTableMap.this.table.size()) throw new NoSuchElementException();
			return UnsortedTableMap.this.table.get(this.idx++);
		}
		
	}

	private ArrayList<MapEntry<K,V>> table = new ArrayList<>();

	//O(n)
	private int findIndex(K key) {
		for (int i = 0; i < this.table.size(); i++) {
			if (this.table.get(i).getKey().equals(key)) {
				return i;
			}
		}
		return -1;
	}
	
	//O(n)
	@Override
	public V put(K key, V val) {
		int i = this.findIndex(key);
		if (i > -1) {
			//update the matching entry
			V oldVal = this.table.get(i).getValue();
			this.table.get(i).setValue(val);
			return oldVal;
			
		} else {
			
			//create a new entry
			this.table.add(this.table.size(), new MapEntry<>(key, val)); //O(1)
			return null;
			
		}
	}

	//O(n)
	@Override
	public V get(K key) {
		int idx = this.findIndex(key);
		if (idx < 0) return null;
		return this.table.get(idx).getValue();
	}

	//O(n)
	@Override
	public V remove(K key) {
		int idx = this.findIndex(key);
		if (idx < 0) return null;
		V val = this.table.get(idx).getValue();
		
		this.table.set(idx, this.table.get(this.table.size() - 1));
		this.table.remove(this.table.size() - 1);
		
		return val;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return new EntryIterable();
	}

	@Override
	public int size() {
		return this.table.size();
	}
	
	public static void main(String[] args) {
		
		Map<String, Integer> m = new UnsortedTableMap<>();
		
		//add new entries
		m.put("TP", 30);
		m.put("Toothbrush", 2);
		m.put("Beans", 20);
		
		//update
		m.put("TP", 42);
		
		//remove
		m.remove("Toothbrush");
		
		for (Entry<String, Integer> entry : m.entrySet() ) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
	}
	
}
