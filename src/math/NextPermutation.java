package math;

/**
 * 
 * @author guoyifeng 
 *         Implement next permutation, which rearranges numbers into
 *         the lexicographically next greater permutation of numbers.
 * 
 *         If such arrangement is not possible, it must rearrange it as the
 *         lowest possible order (ie, sorted in ascending order).
 * 
 *         The replacement must be in-place and use only constant extra memory.
 * 
 *         Here are some examples. Inputs are in the left-hand column and its
 *         corresponding outputs are in the right-hand column.
 * 
 *         1,2,3 → 1,3,2 
 *         3,2,1 → 1,2,3 
 *         1,1,5 → 1,5,1
 * 
 * 
 */
/*
 * demo:
 *     6   3   4   9   8   7   1
 *            i-1  i       j    
 *  
 *  step 1: find two consecutive number from tail to head that satisfies latter one is greater than former one
 *  step 2: find first number from tail to head which is greater than nums[i-1]
 *  step 3: swap i-1 and j
 *          6   3   7   9   8   4   1
 *  step 4: reverse the sequence from i to the end
 *          because the first consecutive two numbers which satisfies latter one is greater than former one
 *          is i - 1 and i. So after swap, the sequence from i to the end must a decreasing sequence, (nums[i]
 *          must be greater than nums[i+1]), so to make it smallest, just reverse this sequence. And this operation
 *          also guarantees nums[j] is the smallest number which is greater than nums[i-1], so this can guarantee
 *          the new number will be the valid next permutation
 */
public class NextPermutation {
	public void nextPermutation(int[] nums) {
		if (nums == null || nums.length <= 1) {
			return;
		}
		int i = nums.length - 1;
		for (; i > 0; i--) {
			if (nums[i] > nums[i - 1]) {
				break;
			}
		}
		if (i != 0) { // this means the sequence is not monotonically decreasing like 5, 4, 3, 2, 1
			// find the first number which is greater than nums[i-1]
			int j = nums.length - 1;
			for (; j >= 0; j--) {
				if (nums[j] > nums[i - 1]) {
					break;
				}
			}
			swap(nums, i - 1, j);
		}
		reverse(nums, i, nums.length - 1);
	}
	
	private void swap(int[] nums, int a, int b) {
		int tmp = nums[a];
		nums[a] = nums[b];
		nums[b] = tmp;
	}
	
	private void reverse(int[] nums, int a, int b) {
		while (a < b) {
			swap(nums, a++, b--);
		}
	}
}
