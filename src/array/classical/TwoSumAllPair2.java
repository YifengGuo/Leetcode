package array.classical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author yifengguo
  Find all pairs of elements in a given array that sum to the pair 
  the given target number. Return all the distinct pairs of values.

	Assumptions
	
	The given array is not null and has length of at least 2
	The order of the values in the pair does not matter
	Examples
	
	A = {2, 1, 3, 2, 4, 3, 4, 2}, target = 6, return [[2, 4], [3, 3]]

 
 */
/*
 * 2,1,3,2,4,3,4,2
 * 
 * 1 2 2 2 3 3 4 4
 * l             r       
 * 
 * l             r      5
 *   l           r  ====6
 *          l    r      7
 *          l r     ====6
 */
public class TwoSumAllPair2 {
	public static List<List<Integer>> allPairs(int[] array, int target) {
		List<List<Integer>> res = new ArrayList<>();
		if (array == null || array.length < 2) {
			return res;
		}
		Arrays.sort(array);
		int left = 0;
		int right = array.length - 1;
		while (left < right) {
			// dedup left side to the left pointer
			if (left > 0 && array[left] == array[left - 1]) {
				left++;
				continue;
			}
			// dedup right side to the right pointer
			if (right < array.length - 1 && array[right] == array[right + 1]) {
				right--;
				continue;
			}
			int tmp = array[left] + array[right];
			if (tmp == target) {
				List<Integer> sub = new ArrayList<>();
				sub.add(array[left]);
				sub.add(array[right]);
				res.add(sub);
				left++;
				right--;
			} else if (tmp < target) {
				left++;
			} else {
				right--;
			}
		}
		return res;
	}
}
