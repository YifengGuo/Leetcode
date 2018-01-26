package dynamicprogramming._1d;
/**
 * 
 * @author yifengguo
  Given an array containing only 0s and 1s, find the length of the longest subarray of consecutive 1s.

	Assumptions
	
	The given array is not null
	Examples
	
	{0, 1, 0, 1, 1, 1, 0}, the longest consecutive 1s is 3.
 */
/*
 * dp:
 * 	0 1 0 1 1 1 0
 *  0 1 0 1 2 3 0
 *  
 *  idea: check current element value and calculate preSum
 *  
 *  time = O(n)
 *  space = O(1)
 */
public class LongestConsecutive1s {
	public int longest(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int globalMax = 0;
		int preSum = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == 1) { // case 1: current value is 1
				if (i == 0 || array[i - 1] == 0) {
					preSum = 1;
				} else {
					preSum++;
				}
			}
			globalMax = Math.max(preSum, globalMax);
		}
		return globalMax;
	}
	
	// solution 2:
	public int longest2(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int globalMax = array[0];
		int cur = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] == 0) {
				cur = 0;
			} else {
				if (array[i - 1] == 1) {
					cur += 1;
				} else {
					cur = 1;
				}
			}
			globalMax = Math.max(globalMax, cur);
		}
		return globalMax;
	}
}
