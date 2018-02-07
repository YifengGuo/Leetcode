package array.classical;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * 
 * @author yifengguo
  Find all numbers that appear in both of the two unsorted arrays, return the common numbers in increasing order.

	Assumptions
	
	Both arrays are not null.
	There are no duplicate numbers in each of the two arrays respectively.
	Exmaples
	
	A = {1, 2, 3}, B = {3, 1, 4}, return [1, 3]
	A = {}, B = {3, 1, 4}, return []
 */
/*
 * use set
 * time = O(nlogn)
 * space = O(n)
 */
public class CommonNumbersOfTwoArrays1 {
	public List<Integer> common(List<Integer> a, List<Integer> b) {
		List<Integer> res = new ArrayList<>();
		if (a == null || b == null || a.size() == 0 || b.size() == 0) {
			return res;
		}
		Set<Integer> set = new HashSet<>();
		for (Integer i : a) {
			set.add(i);
		}
		for (Integer i : b) {
			if (set.contains(i)) {
				res.add(i);
			}
		}
		Collections.sort(res);
		return res;
	}
}
