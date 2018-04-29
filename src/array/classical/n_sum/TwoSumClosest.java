package array.classical.n_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 
 * @author yifengguo
 * 
	Find the pair of elements in a given array that sum to a value that 
	is closest to the given target number. Return the values of the two numbers.
	
	Assumptions
	
	The given array is not null and has length of at least 2
	Examples
	
	A = {1, 4, 7, 13}, target = 7, closest pair is 1 + 7 = 8, return [1, 7].
 */
/*
 * basic idea: 2 sum solution by sort the array and apply two pointers
 */
public class TwoSumClosest {
	public List<Integer> closest(int[] array, int target) {
		List<Integer> res = new ArrayList<>();
		if (array == null) {
			return res;
		}
		res.add(0);
		res.add(0);
		Arrays.sort(array);
		int start = 0;
		int end = array.length - 1;
		int minDiff = Integer.MAX_VALUE;
		// do not need to dedup 
		// {2, 2, 3} 4 and the minDiff is 0 when 2, 2 forms 4
		while (start < end) {
			int sum = array[start] + array[end];
			if (sum == target) {
				res.set(0, array[start]);
				res.set(1, array[end]);
				return res;
			} else if (sum > target) {
				if (Math.abs(target - sum) < minDiff) {
					minDiff = Math.abs(target - sum);
					res.set(0, array[start]);
					res.set(1, array[end]);
				}
				end--;
			} else {
				if (Math.abs(target - sum) < minDiff) {
					minDiff = Math.abs(target - sum);
					res.set(0, array[start]);
					res.set(1, array[end]);
				}
				start++;
			}
		}
		return res;
	}
}
