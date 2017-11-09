package array.BinarySearch;
/**
 * 
 * @author guoyifeng
 	Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

	(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
	
	Find the minimum element.
	
	You may assume no duplicate exists in the array.


 */
/*
 * basic idea: find the pivot where the array rotates
 */
public class FindMinimumInRotatedSortedArray {
	public int findMin(int[] nums) {
		if (nums == null || nums.length == 0) {
			return -1;
		} 
		int start = 0;
		int end = nums.length - 1;
		if (nums[start] < nums[end]) {
			return nums[start];
		}
		while (start < end - 1) {
			int mid = start + (end - start) / 2;
			if (nums[mid] > nums[start]) { // left side
				start = mid;
			} else {
				end = mid;
			}
		}
		return nums[start] < nums[end] ? nums[start] : nums[end];
	}
}

