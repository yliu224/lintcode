package mock_state.leetc_e;

import java.util.*;

public class PeekingIterator implements Iterator<Integer> {
    private List<Integer> list;
    private int index;
	public PeekingIterator(Iterator<Integer> iterator) {
	    this.list = new ArrayList<>();
	    while(iterator.hasNext()){
            this.list.add(iterator.next());
        }
        this.index = 0;
	}
	
    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        return this.list.get(index);
	}
	
	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    return this.list.get(index++);
	}
	
	@Override
	public boolean hasNext() {
	    if(index>=this.list.size()){
            return false;
        }
        return true;
	}    
}
