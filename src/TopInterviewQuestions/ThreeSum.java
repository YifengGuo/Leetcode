package TopInterviewQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 
 * @author yifengguo
 	Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

	Note: The solution set must not contain duplicate triplets.
	
	For example, given array S = [-1, 0, 1, 2, -1, -4],
	
	A solution set is:
	[
	  [-1, 0, 1],
	  [-1, -1, 2]
	]
 */
public class ThreeSum {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) { // must guarantee at least 3 elements
			// if array is like 1 1 1 1, we only use first occurrence of element
			// and ignore the following duplicate to avoid duplicate result
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			int goal = 0 - nums[i];
			int left = i + 1;;
			int right = nums.length - 1;
			while (left < right) {
				if (nums[left] + nums[right] == goal) {
					res.add(Arrays.asList(nums[i], nums[left], nums[right]));
					left++;// move left pointer to find all the pairs
					while (left < right && nums[left] == nums[left - 1]) { // de-duplicate in two-sum just like outer loop of three sum
						left++;
					}
				} else if (nums[left] + nums[right] < goal) {
					left++;
				} else {
					right--;
				}
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {-1,0,1,2,-1,4};
		List<List<Integer>> res = new ThreeSum().threeSum(nums);
		for (List<Integer> list : res) {
			for (Integer i : list) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}

