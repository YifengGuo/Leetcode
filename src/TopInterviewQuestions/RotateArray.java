package TopInterviewQuestions;

/**
 * 
 * @author yifengguo 
 *         Rotate an array of n elements to the right by k steps.
 * 
 *         For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is
 *         rotated to [5,6,7,1,2,3,4].
 * 
 *         Note: Try to come up as many solutions as you can, there are at least
 *         3 different ways to solve this problem.
 */
/*
 * basic idea:	I love Yahoo type problem
 *              need to check k and nums.length relationship
 *    time = O(n)
 *    space = O(1)
 */
public class RotateArray {
	public void rotate(int[] nums, int k) {
		// for do not know k and nums.length relationship
		// k might be quite huge, so a safe way to avoid
		// overflow risk is to k % nums.length
		k = k % nums.length;
		reverse(nums, 0, nums.length - 1);
		reverse(nums, 0, k - 1);
		reverse(nums, k, nums.length - 1);
	}

	private void reverse(int[] nums, int start, int end) {
		while (start < end) {
			swap(nums, start, end);
			start++;
			end--;
		}
	}

	private void swap(int[] nums, int a, int b) {
		int tmp = nums[a];
		nums[a] = nums[b];
		nums[b] = tmp;
	}
}
