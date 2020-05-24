package cs313final;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * This file should not be modified
 */

public class ChainHashMap<K,V> extends AbstractMap<K,V> {

	private UnsortedTableMap<K,V>[] table;
	private int size, a, b, p;
	
	public ChainHashMap(int capacity) {
		//assign values for MAD compression function
		Random rand = new Random();
		this.p = 109345121;
		this.a = rand.nextInt(this.p - 1) + 1;
		this.b = rand.nextInt(this.p);
		
		//initialize table
		this.createTable(capacity);
	}
	
	public ChainHashMap() {
		this(10);
	}
	
	
	private void createTable(int capacity) {
		this.table = (UnsortedTableMap<K,V>[]) new UnsortedTableMap[capacity];
		for (int i = 0; i < this.table.length; i++)
			this.table[i] = new UnsortedTableMap<>();
		this.size = 0;
	}
	
	private void maybeResize() {
		//only resize if load factor is > 0.5
		if ( this.size <= this.table.length / 2) return;
		
		//store the current entries
		List<Entry<K,V>> temp = new ArrayList<>();
		for (Entry<K,V> entry : this.entrySet())
			temp.add(temp.size(), entry);
		
		//create new table
		this.createTable(this.table.length * 2);
		
		//reinsert the old entries (since the capacity has changed, indices will be different)
		for (Entry<K,V> entry : temp ) 
			this.put(entry.getKey(), entry.getValue());
		
	}
	
	private int hash(K key) {
		//MAD compression
		return Math.abs((key.hashCode() * a + b) % p ) % this.table.length;
	}
	
	@Override
	public V put(K key, V val) {
		UnsortedTableMap<K,V> bucket = this.table[this.hash(key)];
		int oldSize = bucket.size();
		V oldValue = bucket.put(key, val);
		this.size += (bucket.size() - oldSize); //size is only increased if the bucket size changed
		
		this.maybeResize();
		
		return oldValue;
	}
	
	@Override
	public V get(K key) {
		return this.table[this.hash(key)].get(key);
	}
	
	@Override
	public V remove(K key) {
		UnsortedTableMap<K,V> bucket = this.table[this.hash(key)];
		int oldSize = bucket.size(); 
		V oldValue = bucket.remove(key);
		this.size -= (oldSize - bucket.size());  //size is only decreased if the bucket size changed
		return oldValue;
	}
	
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		List<Entry<K,V>> iterable = new ArrayList<>(this.size);
		
		for (int i = 0; i < this.table.length; i++) {
			UnsortedTableMap<K,V> bucket = this.table[i];
			for ( Entry<K,V> entry : bucket.entrySet() )
				iterable.add(iterable.size(), entry);
		}
		
		return iterable;
	}
	
	@Override
	public int size() {
		return this.size;
	}
	
}
