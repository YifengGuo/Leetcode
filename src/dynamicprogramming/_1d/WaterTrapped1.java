package dynamicprogramming._1d;

/**
 * 
 * @author guoyifeng 
 * 		   Given a non-negative integer array representing the heights
 *         of a list of adjacent bars. Suppose each bar has a width of 1. Find
 *         the largest amount of water that can be trapped in the histogram.
 * 
 *         Assumptions
 * 
 *         The given array is not null Examples
 * 
 *         { 2, 1, 3, 2, 4 }, the amount of water can be trapped is 1 + 1 = 2
 *         (at index 1, 1 unit of water can be trapped and index 3, 1 unit of
 *         water can be trapped)
 */
public class WaterTrapped1 {
	/*
	 * method 1: dp
	 * 	
	 *  get the highest bar from left to right and from right to left
	 *  and the difference between current bar and the min(left_max, right_max)
	 *  is the amount of water the current bar can hold
	 *  
	 *  time = O(3n) = O(n)
	 *  space = O(n)
	 */
	public int maxTrapped(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int max = 0;
		// left_max represents from 0th to ith, the current highest bar length
		// right_max represents from (array.length - 1)th to ith, the current highest bar length
		int[] left_max = new int[array.length];
		int[] right_max = new int[array.length];
		int leftMax = Integer.MIN_VALUE;
		int rightMax = Integer.MIN_VALUE;
		// construct left_max
		for (int i = 0; i < array.length; i++) {
			if (leftMax < array[i]) {
				leftMax = array[i];
				left_max[i] = leftMax;
			} else {
				left_max[i] = leftMax;
			}
		}
		// construct right_max
		for (int i = array.length - 1; i >= 0; i--) {
			if (rightMax < array[i]) {
				rightMax = array[i];
				right_max[i] = rightMax;
			} else {
				right_max[i] = rightMax;
			}
		}
		// the difference between current bar and min(left_max[i], right_max[i]) is the 
		// amount of water current bar can hold
		for (int i = 0; i < array.length; i++) {
			max += Math.min(left_max[i], right_max[i]) - array[i];
		}
		return max;
	}
	
	/*
	 * method 2: greedy
	 */
	public int maxTrapped_greedy(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int max = 0;
		int left = 0;
		int right = array.length - 1;
		int left_max = 0;
		int right_max = 0;
		while (left <= right) {
			left_max = Math.max(array[left], left_max);
			right_max = Math.max(array[right], right_max);
			if (left_max < right_max) {
				max += left_max - array[left];
				left++;
			} else {
				max += right_max - array[right];
				right--;
			}
		}
		return max;
	}
}

