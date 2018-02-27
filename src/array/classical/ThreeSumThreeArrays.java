package array.classical;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author yifengguo
 * Given three arrays, determine if a set can be made by picking one element 
 * from each array that sums to the given target number.
	
	Assumptions
	
	The three given arrays are not null and have length of at least 1
	Examples
	
	A = {1, 3, 5}, B = {8, 2}, C = {3}, target = 14, return true(pick 3 from A, pick 8 from B and pick 3 from C)
 */
/*
 * basic idea: hashtable and fix one element from a and apply two-sum logic on the other two arrays
 * time = O(n ^ 2)
 * space = O(n ^ 2)
 */
public class ThreeSumThreeArrays {
	public boolean exist(int[] a, int[] b, int[] c, int target) {
		if (a == null || b == null || c == null || a.length == 0 || b.length == 0 || c.length == 0) {
			return false;
		}

		for (int i = 0; i < a.length; i++) {
			int tmp = target - a[i];
			Map<Integer, Integer> map = new HashMap<>();
			for (int ib : b) {
				map.put(ib, 0);
			}
			for (int ic : c) {
				if (map.containsKey(tmp - ic)) {
					return true;
				}
			}
		}
		return false;
	}
}