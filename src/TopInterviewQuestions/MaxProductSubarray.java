package TopInterviewQuestions;
/**
 * 
 * @author guoyifeng 
 *         Find the contiguous subarray within an array (containing at
 *         least one number) which has the largest product.
 * 
 *         For example, given the array [2,3,-2,4], the contiguous subarray
 *         [2,3] has the largest product = 6.
 */
/*
 * basic idea: dynamic programming
 * 	maintain max and min and result globalMax
 * 	for we are trying to find max product subarray
 *  so extreme large or small partial product is useful
 *  to be maintained, because a very small negative product
 *  may become quite large after meeting a negative element in
 *  the subarray, but a large value may immediately become small
 *  after multiplied with a negative element
 *  Demo: [2, -5, -2, -4, 3]
 *  max:  2  -5   20   8   24
 *  min:  2  -10  -2  -80  -240
 *  glo:  2  2    20   20  24
 *  max flopped when 20 * (-4), subproduct (-80)
 *  is recorded as min and expected to become max
 *  again if there is a negative value afterwards
 *  
 *  time = O(n) for one pass on array
 *  space = O(1)
 */
public class MaxProductSubarray {
	public int maxProduct(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		int globalMax = nums[0];
		int max = nums[0];
		int min = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int temp = max; // record max at last this iteration
			max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
			min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);
			globalMax = Math.max(globalMax, max);
		}
		return globalMax;
	}
}
