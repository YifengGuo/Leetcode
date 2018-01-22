package dynamicprogramming._1d;
/**
 * 
 * @author yifengguo
  Given an unsorted array, find the length of the longest subarray in which the numbers are in ascending order.

	Assumptions
	
	The given array is not null
	Examples
	
	{7, 2, 3, 1, 5, 8, 9, 6}, longest ascending subarray is {1, 5, 8, 9}, length is 4.
	
	{1, 2, 3, 3, 4, 4, 5}, longest ascending subarray is {1, 2, 3}, length is 3.
 */
public class LongestAscendingSubArray {
	// method 1: 1 d array
	// time = O(n)
	// space = O(n)
	public int longest2(int[] array) {
		int globalMax = 1;
		if (array == null || array.length == 0) {
			return 0;
		}
		int[] dp = new int[array.length];
		dp[0] = 1;
		for (int i = 1; i < array.length; i++) {
			if (array[i] > array[i - 1]) {
				dp[i] = dp[i - 1] + 1;
			} else {
				dp[i] = 1;
			}
			globalMax = Math.max(globalMax, dp[i]);
		}
		return globalMax;
	}
	
	// method 2: one var to cache the sub-solu
	// time = O(n)
	// space = O(1)
	public int longest(int[] array) {
		int globalMax = 1;
		if (array == null || array.length == 0) {
			return 0;
		}
		int cur = 1;
		for (int i = 1; i < array.length; i++) {
			if (array[i] > array[i - 1]) {
				cur += 1;
			} else {
				cur = 1;
			}
			globalMax = Math.max(cur, globalMax);
		}
		return globalMax;
	}
}
