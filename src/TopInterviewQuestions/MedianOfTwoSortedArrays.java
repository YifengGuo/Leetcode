package TopInterviewQuestions;
/**
 * 
 * @author yifengguo
 	There are two sorted arrays nums1 and nums2 of size m and n respectively.

	Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
	
	Example 1:
	nums1 = [1, 3]
	nums2 = [2]
	
	The median is 2.0
	Example 2:
	nums1 = [1, 2]
	nums2 = [3, 4]
	
	The median is (2 + 3)/2 = 2.5
 */
/*
 * time = O(log(m + n)) where m and n are the length of nums1 and nums2
 * space = O(log(m + n)) for each layer of recursion only has O(1) space
 * 
 * idea: 1. binary reduction (nums1_k/2th and nums2_k/2th comparison and delete half k elements after each comparison, k = k - k /2)
 *       2. base cases:
 *       	determine k and nums1.length and nums2.length relationship, if k out of array bound, try to find it in another array
 *          if k == 1, which means cannot further divided, it means we are finding the smallest element, just return smaller one of two arrays.
 *       3. if k /2 is larger than its array, force to find it in another array
 */
public class MedianOfTwoSortedArrays {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		// assume neither of two arrays is null or empty
		int len = nums1.length + nums2.length;
		// even case
		if (len % 2 == 0) {
			return (findKthSmallest(nums1, 0, nums2, 0, len / 2) + findKthSmallest(nums1, 0, nums2, 0, len / 2 + 1))
					/ 2.0;
		}
		// odd case
		return findKthSmallest(nums1, 0, nums2, 0, len / 2 + 1);
	}

	private int findKthSmallest(int[] nums1, int nums1Start, int[] nums2, int nums2Start, int k) {
		// base case 1
		// nothing left in nums1
		if (nums1Start >= nums1.length) {
			return nums2[nums2Start + k - 1];
		}
		// base case 2
		// nothing left in nums2
		if (nums2Start >= nums2.length) {
			return nums1[nums1Start + k - 1];
		}
		// base case 3
		// k cannot be further divided
		if (k == 1) {
			return Math.min(nums1[nums1Start], nums2[nums2Start]);
		}
		// general process
		// determine relationship between k / 2 and arrays length
		int nums1HalfK = nums1Start + k / 2 - 1 < nums1.length ? nums1[nums1Start + k / 2 - 1] : Integer.MAX_VALUE;
		int nums2HalfK = nums2Start + k / 2 - 1 < nums2.length ? nums2[nums2Start + k / 2 - 1] : Integer.MAX_VALUE;
		if (nums1HalfK > nums2HalfK) {
			return findKthSmallest(nums1, nums1Start, nums2, nums2Start + k / 2, k - k / 2);
		} else {
			return findKthSmallest(nums1, nums1Start + k / 2, nums2, nums2Start, k - k / 2);
		}
	}
}
