package dynamicprogramming.LAS;

import java.util.Arrays;
/**
 * 
 * @author yifengguo
  Given an array A[0]...A[n-1] of integers, find out the length of the longest ascending subsequence.

	Assumptions
	
	A is not null
	Examples
	Input: A = {5, 2, 6, 3, 4, 7, 5}
	Output: 4
	Because [2, 3, 4, 5] is the longest ascending subsequence.
 */
/*
 * time = O(n ^ 2)
 * space = O(n)
 */
public class LongestAscendingSubsequence {
	public int longest(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		// M[i] represents the longest ascending subsequence from 0 to current index
		int[] M = new int[array.length];
		Arrays.fill(M, 1); // initialize all position as 1 for minimum
		int globalMax = 1;
		for (int i = 1; i < array.length; i++) {
			for (int j = 0; j < i; j++) { // check all position before i
				if (array[j] < array[i]) {
					M[i] = Math.max(M[j] + 1, M[i]); // update M[i] if possible
				} else {
					continue;
				}
			}
			globalMax = Math.max(M[i], globalMax);
		}
		return globalMax;
	}
}
