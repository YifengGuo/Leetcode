package ProbabilityAndSampling;
/**
 * 
 * @author guoyifeng
 * Given an array of integers (without any duplicates), 
 * shuffle the array such that all permutations are equally likely to be generated.

	Assumptions
	
	The given array is not null
 */
public class PerfectShuffle {
	public void shuffle(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}
		for (int i = nums.length; i >= 1; i--) {
			int index = (int)(Math.random() * i); // Math.random() return [0, 1)
			                                      // so i starts from array.length
												  // return [0, n)
			swap(nums, index, i - 1);
		}
	}
	
	private void swap(int[] nums, int a, int b) {
		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}
}
