package TopInterviewQuestions;

/**
 * 
 * @author guoyifeng 
 * 		   A peak element is an element that is greater than its
 *         neighbors.
 * 
 *         Given an input array where num[i] ≠ num[i+1], find a peak element and
 *         return its index.
 * 
 *         The array may contain multiple peaks, in that case return the index
 *         to any one of the peaks is fine.
 * 
 *         You may imagine that num[-1] = num[n] = -∞.
 * 
 *         For example, in array [1, 2, 3, 1], 3 is a peak element and your
 *         function should return the index number 2.
 * 
 * 
 */
/*
 * basic idea: binary search
 * time = O(logn) where n is the length of nums
 * space = O(1)
 * maintain the property which [left, right], nums[left] > nums[left - 1] && nums[right] > nums[right + 1]
 * use binary search idea and reduce this range into two numbers
 * post-process will determine which one to return
 */
public class FindPeakElement {
	public int findPeakElement(int[] nums) {
		if (nums == null || nums.length <= 1) {
			return 0;
		}
		int left = 0;
		int right = nums.length - 1;
		while (right - left > 1) {
			int mid = left + (right - left) / 2;
			if (nums[mid] < nums[mid + 1]) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return (left == nums.length - 1 || nums[left] > nums[left + 1]) ? left : right;
	}
}
