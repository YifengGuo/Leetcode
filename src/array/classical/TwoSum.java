package array.classical;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author yifengguo
  Determine if there exist two elements in a given array, the sum of which is the given target number.

	Assumptions
	
	The given array is not null and has length of at least 2
	​Examples
	
	A = {1, 2, 3, 4}, target = 5, return true (1 + 4 = 5)
	
	A = {2, 4, 2, 1}, target = 4, return true (2 + 2 = 4)
	
	A = {2, 4, 1}, target = 4, return false

 
 */
public class TwoSum {
	// method 1: hashMap
	public boolean existSum1(int[] array, int target) {
		if (array == null || array.length < 2) {
			return false;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for (int i : array) {
			if (map.containsKey(target - i)) {
				return true;
			} else {
				map.put(i, i);
			}
		}
		return false;
	}
	// method 2: sort and two pointers
	public boolean existSum2(int[] array, int target) {
		if (array == null || array.length == 0) {
			return false;
		}
		Arrays.sort(array);
		int left = 0;
		int right = array.length - 1;
		while (left < right) {
			if (array[left] + array[right] == target) {
				return true;
			} else if (array[left] + array[right] < target) {
				left++;
			} else {
				right--;
			}
		}
		return false;
	}
}
