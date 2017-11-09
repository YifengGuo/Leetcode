package array.BinarySearch;
/**
 * 
 * @author guoyifeng
 	Follow up for "Find Minimum in Rotated Sorted Array":
	What if duplicates are allowed?
	
	Would this affect the run-time complexity? How and why?
	Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
	
	(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
	
	Find the minimum element.
	
	The array may contain duplicates.


 */
/*
 * step1: shrink search space
 * step2: find the pivot where original ascending array rotates
 * step3: post-process: compare nums[start] and nums[end] and return smaller one
 */
public class FindMinInRotatedSortedArrayWithDuplicates {
	public int findMin(int[] nums) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		int start = 0;
		int end = nums.length - 1;
		if (nums[start] < nums[end]) {
			return nums[start];
		}
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (nums[mid] == nums[start]) { // shrink the search space
				start++;
			} else if (nums[mid] == nums[end]) { // shrink the search space
				end--;
			} else if (nums[mid] > nums[start]) { // same idea as case without duplicates
												  // find the pivot where original array rotates
				start = mid;
			} else {
				end = mid;
			}
		}
		return nums[start] <= nums[end] ? nums[start] : nums[end];
	}
}
