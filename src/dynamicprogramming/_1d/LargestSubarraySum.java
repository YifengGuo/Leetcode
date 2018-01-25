package dynamicprogramming._1d;
/**
 * 
 * @author yifengguo
 * Given an unsorted integer array, find the subarray that has the greatest sum. Return the sum.

	Assumptions
	
	The given array is not null and has length of at least 1.
	Examples
	
	{2, -1, 4, -2, 1}, the largest subarray sum is 2 + (-1) + 4 = 5
	
	{-2, -1, -3}, the largest subarray sum is -1

 */
/*
 * basic idea: dp
 * time = O(n)
 * space = O(1)
 */
public class LargestSubarraySum {
	public int largestSum(int[] array) {
		if (array == null || array.length == 0) {
			return Integer.MIN_VALUE;
		}
		int globalMax = array[0];
		int preSum = array[0];
		for (int i = 1; i < array.length; i++) {
		  if (preSum + array[i] > array[i]) { // check if preSum can do positive contribution to current element
		    preSum += array[i];
		  } else {
		    preSum = array[i]; // if preSum is negative, discard it and reset it as array[i]
		  }
		  globalMax = Math.max(globalMax, preSum); // update globalMax
		}
		return globalMax;
	}
}

