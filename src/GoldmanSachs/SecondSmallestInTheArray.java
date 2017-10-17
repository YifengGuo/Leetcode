package GoldmanSachs;
/**
 * 
 * @author guoyifeng
 *	find the second smallest number in the given array
 */
/*
 * time = O(n) for one pass on array
 * space = O(1)
 */
public class SecondSmallestInTheArray {
	public int second(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int min = Integer.MAX_VALUE; // minimum of array
		int sec = 0; // second smallest element in the array
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] <= min) { // update min and sec
				sec = min;
				min = nums[i];
			} else if (nums[i] > min && nums[i] <= sec) { // update sec
				sec = nums[i];
			} else {
				continue;
			}
		}
		return sec;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[]{2,99,5,123,6,23,12,756,232,1223};
		int sec = new SecondSmallestInTheArray().second(nums);
		System.out.println(sec);
	}
}
