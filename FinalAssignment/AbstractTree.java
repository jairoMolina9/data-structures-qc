package cs313final;

/*
 * This file should not be modified
 */

public abstract class AbstractTree<E> implements Tree<E> {
	
	//O(1)
	public boolean isInternal(Position<E> p) {
		return this.numChildren(p) > 0;
	}
	
	//O(1)
	public boolean isExternal(Position<E> p) {
		return this.numChildren(p) == 0;
	}

	//O(1)
	public boolean isRoot(Position<E> p) {
		return p == this.root();
	}
	
	//O(1)
	public boolean isEmpty() {
		return this.size() == 0;		
	}
	
	//O(n)
	public int depth(Position<E> p) {
		if (this.isRoot(p)) return 0;
		else return 1 + this.depth(this.parent(p));
	}

	//O(n)
	public int height(Position<E> p) {
		int h = 0;
		for (Position<E> child : this.children(p)) {
			h = Math.max(h,  1 + this.height(child));
		}
		return h;
	}
	
}
