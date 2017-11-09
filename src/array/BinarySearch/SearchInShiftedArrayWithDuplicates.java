package array.BinarySearch;
/**
 * 
 * @author guoyifeng
 *	Given a target integer T and an integer array A, A is sorted in ascending order first, 
 *	then shifted by an arbitrary number of positions.

	For Example, A = {3, 4, 5, 1, 2} (shifted left by 2 positions). 
	Find the index i such that A[i] == T or return -1 if there is no such index.
	
	Assumptions
	
	There could be duplicate elements in the array.
	Examples
	
	A = {3, 4, 5, 1, 2}, T = 4, return 1
	A = {3, 3, 3, 1, 3}, T = 1, return 3
	A = {3, 1, 3, 3, 3}, T = 1, return 1
	​Corner Cases
	
	What if A is null or A is of zero length? We should return -1 in this case.
 */
/*
 * my better solution
 * step 1: shrink search space by removing duplicates on left or right limit
 * step 2: run binary search the same way as search in shifted sorted array 1
 */
public class SearchInShiftedArrayWithDuplicates {
	public int search(int[] array, int target) {
		if (array == null || array.length == 0) {
			return -1;
		}
		int start = 0;
		int end = array.length - 1;
		while (start + 1 < end) {
		  int mid = start + (end - start) / 2;
			if (array[mid] == array[start]) { // removing duplicates to shrink search space 
				start++;
			} else if(array[mid] == array[end]) { // removing duplicates to shrink search space 
				end--;
			} else if (array[mid] > array[start]) { // determine array[mid] and array[start] relationship
			  if (array[mid] >= target && target >= array[start]) { // target within ascending range
			    end = mid;
			  } else { // all the other cases
			    start = mid;
			  }
			} else {
			  if (target <= array[end] && array[mid] <= target) { // target within ascending range
			    start = mid;
			  } else { // all the other cases
			    end = mid;
			  }
			}
		}
		if (array[end] == target) {
			return end;
		} else if (array[start] == target) {
			return start;
		}
		return -1;
	}
}
///*
// * basic idea: for we have duplicates in the shifted sorted array 
// * 			   step 1: 
// * 					we need to shrink the search space 
// * 			   		if (array[mid] == array[end] || array[mid] == array[start])
// * 			   		under these cases start and end elements must be duplicates 
// * 			   		and deleting them is safe for searching
// * 			  step 2: when target and mid with end or start are in ascending range
// * 					  then run binary search
// * 			  step 3: else cases:
// * 						
// * 
// */
//public class SearchInShiftedArrayWithDuplicates {
//	public int search(int[] array, int target) {
//		if (array == null || array.length == 0) {
//			return -1;
//		}
//		int start = 0;
//		int end = array.length - 1;
//		while (start + 1 < end) {
//			int mid = start + (end - start) / 2;
//			if (array[mid] == array[start]) { // shrink search space
//				start++;
//			} else if (array[mid] == array[end]) { // shrink search space
//				end--;
//			} else if (array[mid] >= target && target >= array[start]) { // case 1 ascending range
//				end = mid;
//			} else if (array[mid] <= target && target <= array[end]) { // case 2 ascending range
//				start = mid;
//			} else if (array[mid] < array[start]) { // argue case 1 like (3, -2, -1, 0, 2) and target is 3
//												    // now target is within this descending range
//				end = mid;
//			} else { // argue case 2 target is within this descending range
//					 // array[mid] > array[end]
//				start = mid;
//			}
//		}
//		if (array[end] == target) {
//			return end;
//		} else if (array[start] == target) {
//			return start;
//		}
//		return -1;
//	}
}
