package TopInterviewQuestions;

/**
 * 
 * @author guoyifeng 
 * 		   Suppose an array sorted in ascending order is rotated at
 *         some pivot unknown to you beforehand.
 * 
 *         (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 *         You are given a target value to search. If found in the array return
 *         its index, otherwise return -1.
 * 
 *         You may assume no duplicate exists in the array.
 * 
 * 
 */
/*
 * basic idea: Binary search
 * 	rotated sorted array can be separated into two parts by the pivot
 * 	left field and right field
 * 		mid can be located at left or right field
 * 			when mid is on left field, target could belong to [lo, mid] or [mid, ?] we only
 * 			need to consider target within [lo, mid] and run simple binary search, other cases
 * 			are handled by else
 * 			when mid is on right field, target could belong to [?, mid] or [mid, hi] we only
 * 			need to consider target within [mid, hi] and run simple binary search, other cases
 * 			are handled by else
 *	And find target index by post process
 *	time = O(logn)
 *	space = O(1)
 */
public class SearchInRotatedSortedArray {
	public int search(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		int lo = 0;
		int hi = nums.length - 1;
		while (lo + 1 < hi) {
			int mid = lo + (hi - lo) / 2;
			if (nums[mid] > nums[hi]) { // located at left field
				// call binary search
				if (target >= nums[lo] && target <= nums[mid]) {
					hi = mid;
				} else {
					lo = mid;
				}
			} else { // located at right field
				if (target <= nums[hi] && target >= nums[mid]) {
					lo = mid;
				} else {
					hi = mid;
				}
			}
		}
		if (nums[lo] == target) {
			return lo;
		} else if (nums[hi] == target) {
			return hi;
		}
		return -1;
	}
}
