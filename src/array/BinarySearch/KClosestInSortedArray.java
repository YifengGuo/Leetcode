package array.BinarySearch;
/**
 * 
 * @author yifengguo
 	Given a target integer T, a non-negative integer K and an integer array A sorted in ascending order, find the K closest numbers to T in A.

Assumptions

A is not null
K is guranteed to be >= 0 and K is guranteed to be <= A.length
Return

A size K integer array containing the K closest numbers(not indices) in A, sorted in ascending order by the difference between the number and T. 
Examples

A = {1, 2, 3}, T = 2, K = 3, return {2, 1, 3} or {2, 3, 1}
A = {1, 4, 6, 8}, T = 3, K = 3, return {4, 1, 6}
 
 */
/*
 * time = O(Math.max(k, logn))
 */
public class KClosestInSortedArray {
	public int[] kClosest(int[] array, int target, int k) {
		if (array == null || array.length == 0) {
			return array;
		}
		int start = 0;
		int end = array.length - 1;
		int[] res = new int[k];
		while (end - start > 1) {
			int mid = start + (end - start) / 2;
			if (array[mid] >= target) {
				end = mid;
			} else {
				start = mid;
			}
		}
		int i = 0;
		while (i < k) { // iterate k closest
			if (start < 0 || end > array.length - 1) { // start or end may be out of bound
				if (start < 0) {
					res[i++] = array[end++];
					continue;
				} else if (end > array.length - 1) {
					res[i++] = array[start--];
					continue;
				} else {
					break;
				}
			} else if (array[end] == target) {
				res[i++] = array[end++];
				continue;
			} else if (array[start] == target) {
				res[i++] = array[start--];
				continue;
			} else { // determine which one is closer to target
				if (Math.abs(array[start] - target) < Math.abs(array[end] - target)) {
					res[i++] = array[start--];
				} else {
					res[i++] = array[end++];
				}
				continue;
			}
		}
		return res;
	}
}
