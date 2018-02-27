package array.classical;

import java.util.Arrays;
/**
 * 
 * @author yifengguo
 * Determine the number of pairs of elements in a given array that sum to a value smaller than the given target number.

	Assumptions
	
	The given array is not null and has length of at least 2
	Examples
	
	A = {1, 2, 2, 4, 7}, target = 7, number of pairs is 6({1,2}, {1, 2}, {1, 4}, {2, 2}, {2, 4}, {2, 4})
 */
/*
 * basic idea: sort and two pointers
 */
public class TwoSumSmaller {
	public int smallerPairs(int[] array, int target) {
		int count = 0;
		if (array == null || array.length <= 1) {
			return 0;
		}
		Arrays.sort(array);
		int left = 0;
		int right = array.length - 1;
		// [2,3,4,5,6,7] 8
		// when we 2 + 7 > 8, we need to move right pointer to make sum less
		// and when we see 2 + 4 < 8, we need to find all the pairs with 2   ===> 2 + 3, 2 + 4， 2 + 5. the number of pairs == right - left
		// then we move left forward to a new start
		while (left < right) {
			int sum = array[left] + array[right];
			if (sum < target) {
				count += right - left;
				left++;
			} else if (sum >= target) {
				right--;
			}
		}
		return count;
	}
}
