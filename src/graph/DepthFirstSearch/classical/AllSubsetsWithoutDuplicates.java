package graph.DepthFirstSearch.classical;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author guoyifeng
 	Given a set of distinct integers, nums, return all possible subsets.

	Note: The solution set must not contain duplicate subsets.
	
	For example,
	If nums = [1,2,3], a solution is:
	
	[
	  [3],
	  [1],
	  [2],
	  [1,2,3],
	  [1,3],
	  [2,3],
	  [1,2],
	  []
	]
 */
/*
 * basic idea: dfs add or not add
 * time = O(2 ^ n) for each element of n, we need to determine add or not add
 * space = O(n) n layers recursion tree
 */
public class AllSubsetsWithoutDuplicates {
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}
		List<Integer> plan = new ArrayList<>();
		helper(res, plan, 0, nums);
		return res;
	}
		
	private void helper(List<List<Integer>> res, List<Integer> plan, int level, int[] nums) {
		// base case
		if (level == nums.length) {
			res.add(new ArrayList<Integer>(plan));
			return;
		}
		// add
		plan.add(nums[level]);
		helper(res, plan, level + 1, nums);
		plan.remove(plan.size() - 1); // backtracking
		// not add
		helper(res, plan, level + 1, nums);
	}
}
