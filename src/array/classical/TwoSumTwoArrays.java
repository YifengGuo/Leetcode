package array.classical;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author yifengguo
 * Given two arrays A and B, determine whether or not there exists a pair of elements, 
 * one drawn from each array, that sums to the given target number.
	
	Assumptions
	
	The two given arrays are not null and have length of at least 1
	Examples
	
	A = {3, 1, 5}, B = {2, 8}, target = 7, return true(pick 5 from A and pick 2 from B)
	
	A = {1, 3, 5}, B = {2, 8}, target = 6, return false
 */
/*
 * basic idea: HashMap
 * time = O(n)
 * space = O(n)
 */
public class TwoSumTwoArrays {
	public boolean existSum(int[] a, int[] b, int target) {
		if (a == null || b == null || a.length == 0 || b.length == 0) {
			return false;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for (int i : a) {
			map.put(i, 0);
		}
		for (int i : b) {
			if (map.containsKey(target - i)) {
				return true;
			}
		}
		return false;
	}
}
