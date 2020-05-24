package cs313final;

import java.util.ArrayList;
import java.util.Comparator;

/*
 * Complete this file for part 2 of the assignment
 * 
 * No additional classes should be imported
 */

public class SortedTableMap<K,V> extends AbstractMap<K,V> implements SortedMap<K,V> {
    
    private ArrayList <Entry <K,V>> table;
    private Comparator <K> comp;
    
    public SortedTableMap() {
        
        table = new ArrayList <> ();
        comp = new DefaultComparator <> ();
    
    }
    
    public int findIndex(K key) {
        
        if(table.isEmpty())
            return -1;
        
        int low = 0;
        int high = table.size() - 1;
        int mid = (low + high) / 2;
        K midKey = table.get(mid).getKey();
        
        while (low <= high && (comp.compare(midKey, key) != 0)) {
            
            if (comp.compare(midKey, key) > 0) { //ASC
                high = mid - 1;
            } else {
                low = mid + 1;
            }
            
            mid = (low + high) / 2;
            midKey = table.get(mid).getKey();
            
        }
        
        if(comp.compare(midKey, key) != 0)
            mid = -1;
        
        return mid;
        
    }
    
    @Override
    public V put(K key, V val) {
        
        MapEntry<K,V> newEntry = new MapEntry<>(key, val);
        V oldVal = null;
        
        if (table.isEmpty()) {
            table.add(newEntry);
        } else {
            for (int curr = 0; curr <= table.size() - 1; curr++) {
                K currKey = table.get(curr).getKey();
                
                if (comp.compare(currKey, key) == 0) { //SET
                    oldVal = table.get(curr).getValue();
                    table.set(curr, newEntry);
                    break;
                }
                
                if (comp.compare(currKey, key) > 0) { //ASC
                    table.add(curr, newEntry);
                    break;
                }
                
                if(curr == table.size() -1 ) { //END
                    table.add(newEntry);
                    break;
                }
            }
        }
        
        return oldVal;
 
 }

    @Override
    public V get(K key) {
        
        if(key == null)
            throw new NullPointerException("NULL KEY");
        
        V val = null;
        int index = this.findIndex(key);
        
        if(index != -1)//true if key exists
            val = table.get(index).getValue();
        
        return val;
        
    }

    @Override
    public V remove(K key) {
         
        if(key == null)
            throw new NullPointerException("NULL KEY");
        
        V val = null;
        int index = this.findIndex(key);
        
        if(index != -1) {//true if key exists
            val = table.get(index).getValue();
            table.remove(index);
        }
        
        return val;
        
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return this.table;
    }

    @Override
    public int size() {
        
        if(table.isEmpty())
            return 0;
        
        return table.size();
    }

    @Override
    public Entry<K, V> firstEntry() {
        
        if(table.isEmpty())
            return null;
        
        return table.get(0);
        
    }

    @Override
    public Entry<K, V> lastEntry() {
        
        if(table.isEmpty())
            return null;
        
        return table.get(table.size() - 1);
    }

    @Override
    public Entry<K, V> floorEntry(K key) {
        
        if(table.isEmpty())
            return null;
        
        Entry <K,V> floor = null;
        
        if(this.findIndex(key) != -1) { //if key exists
            floor = table.get(this.findIndex(key));
        } else {
            int low = 0;
            int high = table.size() - 1;
            int mid = (low + high) / 2;
            
            while(low <= high) {
                K midKey = table.get(mid).getKey();
                
                if(comp.compare(midKey, key) < 0)
                    low = mid + 1;
                else
                    high = mid - 1;
                
                mid = (low + high) / 2;
            }
            
            if(comp.compare(table.get(mid).getKey(), key) < 0) //key <
                floor = table.get(mid);
        }
        
        return floor;
        
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) {
        
        if(table.isEmpty())
            return null;
        
        Entry <K,V> ceil = null;
        
        if(this.findIndex(key) != -1) { //if key exists
            ceil = table.get(this.findIndex(key));
        } else {
            int low = 0;
            int high = table.size() - 1;
            int mid = (low + high) / 2;
            
            while(low <= high) {
                K midKey = table.get(mid).getKey();
                
                if(comp.compare(midKey, key) < 0)
                    low = mid + 1;
                else
                    high = mid - 1;
                
                mid = (low + high) / 2;
            }
            
            if((low != table.size()) && (comp.compare(table.get(low).getKey(), key) > 0)) //key >
                ceil = table.get(low);
        }
        
        return ceil;
        
    }

    @Override
    public Entry<K, V> lowerEntry(K key) {
        
        if(table.isEmpty())
            return null;
        
        Entry <K,V> lower = null;
        int low = 0;
        int high = table.size() - 1;
        int mid = (low + high) / 2;
        
        while(low <= high) {
            K midKey = table.get(mid).getKey();
            
            if(comp.compare(midKey, key) < 0)
                low = mid + 1;
            else
                high = mid - 1;
            
            mid = (low + high) / 2;
        }
        
        if(comp.compare(table.get(mid).getKey(), key) < 0) //key <
            lower = table.get(mid);
        
        return lower;
        
    }

    @Override
    public Entry<K, V> higherEntry(K key) {
        
        if(table.isEmpty())
            return null;
        
        Entry <K,V> higher = null;
        int low = 0;
        int high = table.size() - 1;
        int mid = (low + high) / 2;
        
        while(low <= high) {
            K midKey = table.get(mid).getKey();
            
            if(comp.compare(midKey, key) <= 0) // = , for last key
                low = mid + 1;
            else
                high = mid - 1;
            
            mid = (low + high) / 2;
        }
        
        if((low != table.size()) && (comp.compare(table.get(low).getKey(), key) > 0)) //key >
            higher = table.get(low);
        
        return higher;
        
    }

    @Override
    public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) {
        
        if(table.isEmpty())
            return null;
        
        if(comp.compare(fromKey, toKey) > 0)
            throw new IllegalArgumentException("Must be fromKey > toKey");
        
        if(comp.compare(fromKey, toKey) == 0) {
            System.out.println("Returning empty subset");
            return new ArrayList<>();
        }
        
        Entry<K,V> fromEntry = this.ceilingEntry(fromKey);//inclusive
        Entry<K,V> toEntry = this.lowerEntry(toKey);//exclusive, unless toKey > lastKey
       
        if(fromEntry == null || toEntry == null) { //e.g [1,2,3] submap [-1,1)
            System.out.println("Returning empty subset");
            return new ArrayList<>();
        }
        
        ArrayList<Entry<K,V>> submap = new ArrayList<>(this.size());
        fromKey = fromEntry.getKey(); 
        toKey = toEntry.getKey();
        int index = this.findIndex(fromKey);
        
        if(comp.compare(fromKey, toKey) == 0) { // only 1 added [x)
            submap.add(table.get(index));
        } else {
            while(true) {
                if(comp.compare(table.get(index).getKey(), toKey) == 0) {
                    submap.add(table.get(index));
                    break;
                }
                
                submap.add(table.get(index++));
            }
        }
        
        return submap;
        
    }
}
