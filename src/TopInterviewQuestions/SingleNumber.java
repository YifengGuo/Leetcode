package TopInterviewQuestions;

/**
 * 
 * @author guoyifeng 
 * 		   Given an array of integers, every element appears twice
 *         except for one. Find that single one.
 * 
 *         Note: Your algorithm should have a linear runtime complexity. Could
 *         you implement it without using extra memory?
 * 
 * 
 */
/*
 * basic idea:
 * 		use bit operation to solve problem with O(1) space and O(n) time
 * 		Here explain basic property of exclusive or (XOR)
 *      for any integer i:
 *      		i ^ 0 = i
 *              i ^ i = 0
 *              i ^ 0 ^ i = 0
 *      for any two integers a and b:
 *              a ^ b ^ a = (a ^ a) ^ b = 0 ^ b = b
 *      so for this problem, we have only one single number while others are all in pairs
 *      so from the beginning of array, do the XOR with 0, and then do the XOR with next integer
 *      numbers which occur twice will result in 0 and only that single number XOR with 0 will 
 *      return itself
 */
public class SingleNumber {
	public int singleNumber(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int res = 0;
		for (int i = 0; i < nums.length; i++) {
			res = res ^ nums[i];
		}
		return res;
	}
}
