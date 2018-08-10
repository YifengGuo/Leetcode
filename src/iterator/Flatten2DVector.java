package iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author guoyifeng
 * Implement an iterator to flatten a 2d vector.

	Example:
	
	Input: 2d vector =
	[
	  [1,2],
	  [3],
	  [4,5,6]
	]
	Output: [1,2,3,4,5,6]
	Explanation: By calling next repeatedly until hasNext returns false, 
	             the order of elements returned by next should be: [1,2,3,4,5,6].
	Follow up:
	As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 */
public class Flatten2DVector implements Iterator<Integer> {
    private List<Iterator<Integer>> list;
    private int cur;
    public Flatten2DVector(List<List<Integer>> vec2d) {
        list = new ArrayList<>();
        cur = 0; // to indicate which list iterator is being used
        for (List<Integer> row : vec2d) {
            Iterator<Integer> it = row.iterator();
            list.add(it);
        }
    }

    @Override
    public Integer next() {
        return (Integer)list.get(cur).next();
    }

    @Override
    public boolean hasNext() {
    	// base case
        if (list.size() == 0 || cur == list.size()) {
            return false;
        }
        if (cur < list.size() && list.get(cur).hasNext()) {
            return true;
        } else {
            cur++; // move to next list
            return hasNext();
        }
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */