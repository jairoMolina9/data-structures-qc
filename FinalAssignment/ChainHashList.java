package cs313final;
 
/*
 * Complete this file for part 2 of the assignment
 *
 * No additional classes should be imported
 */
 
public class ChainHashList<E> implements List<E> {
    
    ChainHashMap <Integer, E> map;
    int size;
   
    public ChainHashList(){
        
        map = new ChainHashMap<>();
        size = 0;
        
    }
   
    @Override
    public void set(int i, E e) {
        
        if( i < 0 || i >= map.size())
            throw new IndexOutOfBoundsException("Index out of range");
        
        map.put(i, e);
        
    }
 
    @Override
    public void add(int i, E e) {
        
        if( i < 0 || i > map.size())
            throw new IndexOutOfBoundsException("Index out of range");
        
        if (map.get(i) == null) { //no replacement
            //DO NOTHING
        }else { 
            for (int curr = map.size()-1; curr >= i; curr--)//shift right
                map.put(curr + 1, map.get(curr));
        }
        
        map.put(i, e);
        
    }
 
    @Override
    public E get(int i) {
        
        if( i < 0 || i >= map.size())
            throw new IndexOutOfBoundsException("Index out of range");
        
        return map.get(i);
        
    }
 
    @Override
    public E remove(int i) {
        
        if( i < 0 || i >= map.size())
            throw new IndexOutOfBoundsException("Index out of range");
        
        E removedEntry = map.get(i);
        
        if (map.size() <= 1) { //[0] or [0][1]
            map.remove(i);
        } else {
            for(int curr = i; curr < map.size(); curr++)//shift left
                map.put(curr, map.get(curr+1));
            
            map.remove(map.size()-1);
        }
        
        return removedEntry;
        
    }
 
    @Override
    public int size() {
        
        return map.size();
        
    }
 
    @Override
    public boolean isEmpty() {
        
        return map.isEmpty();
        
    }
}