package array.classical.n_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 
 * @author yifengguo
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? 
 * Find all unique quadruplets in the array which gives the sum of target.

	Note:
	
	The solution set must not contain duplicate quadruplets.
	
	Example:
	
	Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
	
	A solution set is:
	[
	  [-1,  0, 0, 1],
	  [-2, -1, 1, 2],
	  [-2,  0, 0, 2]
	]
 */
public class FourSumAll {
	public List<List<Integer>> fourSum(int[] array, int target) {
		List<List<Integer>> res = new ArrayList<>();
		if (array == null || array.length <= 3) {
			return res;
		}
		Arrays.sort(array);

		for (int i = 0; i < array.length; i++) {
			if (i > 0 && array[i] == array[i - 1]) { // dedup
				continue;
			}
			int threeSum = target - array[i];
			for (int j = i + 1; j < array.length; j++) {
				if (j > i + 1 && array[j] == array[j - 1]) { // dedup
					continue;
				}
				int twoSum = threeSum - array[j];
				int left = j + 1;
				int right = array.length - 1;
				while (left < right) {
					if (left > j + 1 && array[left] == array[left - 1]) { // dedup
						left++;
						continue;
					}
					if (right < array.length - 1 && array[right] == array[right + 1]) { // dedup
						right--;
						continue;
					}
					if (array[left] + array[right] == twoSum) {
						List<Integer> list = new ArrayList<>();
						list.add(array[i]);
						list.add(array[j]);
						list.add(array[left]);
						list.add(array[right]);
						res.add(list);
						left++;
						right--;
					} else if (array[left] + array[right] > twoSum) {
						right--;
					} else {
						left++;
					}
				}
			}
		}
		return res;
	}
}
