package forusall_OA;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author guoyifeng
 * Given a number, return all its permutation, skip all cases which the heading numbers are 0
 * example:
 *        101:
 *           return {101, 110}      skip 011
 */
public class PermutationWithoutHeadingZero {
	public List<Integer> permute(int num) {
		int[] nums = toIntArray(num);
		List<Integer> res = new ArrayList<>();
		helper(res, 0, nums);
		return res;
	}
	
	private int[] toIntArray(int num) {
		List<Integer> tmp = new ArrayList<>();
		while (num > 0) {
			tmp.add(num % 10);
			num /= 10;
		}
		int idx = 0;
		int[] res = new int[tmp.size()];
		for (int i = 0; i < tmp.size(); i++) {
			res[idx++] = tmp.get(i);
		}
		return res;
	}
	
	private void helper(List<Integer> res, int level, int[] nums) {
		// base case
		if (level == nums.length) {
			int cur = toInteger(nums);
			if (sameLength(nums, cur)) {
				res.add(cur);
			}
		}
		Set<Integer> set = new HashSet<>();
		for (int i = level; i < nums.length; i++) {
			swap(nums, level, i);
			if (!set.contains(nums[level])) {
				set.add(nums[level]);
				helper(res, level + 1, nums);
			}
			swap(nums, level, i);
		}
	}
	
	private boolean sameLength(int[] nums, int cur) {
		int res = 0;
		while (cur > 0) {
			res++;
			cur /= 10;
		}
		return res == nums.length;
	}
	
	private int toInteger(int[] nums) {
		int res = 0;
		for (int i = 0; i < nums.length; i++) {
			res = res * 10 + nums[i] ;
		}
		return res;
	}
	
	private void swap(int[] nums, int a, int b) {
		int tmp = nums[a];
		nums[a] = nums[b];
		nums[b] = tmp;
	}
	
	public static void main(String[] args) {
		int num = 101;
		List<Integer> res = new PermutationWithoutHeadingZero().permute(num);
		for (int i : res) {
			System.out.print(i + " ");
		}
	}
}