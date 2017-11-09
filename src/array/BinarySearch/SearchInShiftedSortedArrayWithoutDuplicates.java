package array.BinarySearch;
/**
 * 
 * @author guoyifeng
 	Given a target integer T and an integer array A, A is sorted in 
 	ascending order first, then shifted by an arbitrary number of positions.

	For Example, A = {3, 4, 5, 1, 2} (shifted left by 2 positions). Find the index i 
	such that A[i] == T or return -1 if there is no such index.

	Assumptions
	
	There are no duplicate elements in the array.
	Examples
	
	A = {3, 4, 5, 1, 2}, T = 4, return 1
	A = {1, 2, 3, 4, 5}, T = 4, return 3
	A = {3, 5, 6, 1, 2}, T = 4, return -1
	Corner Cases
	
	What if A is null or A is of zero length? We should return -1 in this case.

 */
/*
 * solution 1: classical binary search template
 * basic idea: 
 * 		consider 3 cases for [1, 2, 3, 4, 5]
 * 				case 1: 1 2 3 4 5     [mid] = 3
 * 				case 2: 5 1 2 3 4     [mid] = 2
 * 				case 3: 3 4 5 1 2     [mid] = 5
 * 		step1: determine the relationship between array[mid] and array[start] (or array[end])
 * 		step2: determine if target is within ascending sub-range
 * 					if true  -> classical binary search
 * 					if not   -> else handle
 * 		step3: post-process
 */
public class SearchInShiftedSortedArrayWithoutDuplicates {
	public int search(int[] array, int target) {
		if (array == null || array.length == 0) {
			return -1;
		}
		int start = 0;
		int end = array.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			// first determine array[mid] relationship with array[start](or array[end])
			if (array[mid] > array[start]) {
				// target is within ascending range, then run classical binary search
				if (array[mid] >= target && target >= array[start]) {
					end = mid;
				} else { // other cases are in else
					start = mid;
				}
			} else {
				// target is within ascending range, then run classical binary search
				if (array[mid] <= target && target <= array[end]) {
					start = mid;
				} else {
					end = mid;
				}
			}
		}
		if (array[start] == target) {
			return start;
		} else if (array[end] == target) {
			return end;
		}
		return -1;
	}
}
