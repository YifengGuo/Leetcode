package array.BinarySearch;
/**
 * 
 * @author guoyifeng
 *Given an array of integers sorted in ascending order, 
 *find the starting and ending position of a given target value.

	Your algorithm's runtime complexity must be in the order of O(log n).
	
	If the target is not found in the array, return [-1, -1].
	
	For example,
	Given [5, 7, 7, 8, 8, 10] and target value 8,
	return [3, 4].


 */
/*
 * binary search to find first occurrence and last occurrence
 * time = O(logn)
 */
public class SearchForARange {
	public int[] searchRange(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return new int[] { -1, -1 };
		}
		int start = 0;
		int end = nums.length - 1;
		int left = 0;
		int right = 0;
		// find first occurrence of target
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (nums[mid] >= target) {
				end = mid;
			} else {
				start = mid;
			}
		}
		// post-process
		if (nums[start] == target) {
			left = start;
		} else if (nums[end] == target) {
			left = end;
		} else {
			left = -1;
		}
		start = 0;
		end = nums.length - 1;
		// find last occurrence of target
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (nums[mid] <= target) {
				start = mid;
			} else {
				end = mid;
			}
		}
		// post-process
		if (nums[end] == target) {
			right = end;
		} else if (nums[start] == target) {
			right = start;
		} else {
			right = -1;
		}
		return new int[] { left, right };
	}
}
