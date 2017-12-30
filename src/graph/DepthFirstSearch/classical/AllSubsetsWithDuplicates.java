package graph.DepthFirstSearch.classical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 
 * @author guoyifeng
 	Given a collection of integers that might contain duplicates, nums, return all possible subsets.

	Note: The solution set must not contain duplicate subsets.
	
	For example,
	If nums = [1,2,2], a solution is:
	
	[
	  [2],
	  [1],
	  [1,2,2],
	  [2,2],
	  [1,2],
	  []
	]
 */
/*
 * basic idea:
 * 			when given array has duplicate, we first need to sort the array
 * 			to make duplicates are permuted together
 * 			then run DFS using add or not add
 * 			whenever we backtrack from deeper layer, it means we have find all the possible
 * 			conditions in terms of current layer number, so if next num is also this number
 * 			we just let level++ to skip the duplicate
 * 
 * 	Demo: 
 *                                      {}                                            ""
 *                          /                     \
 *                         {1}                       {}                               1
 *                       /    \                   /        \
 *                     {12}      {1}          {2}            {}                       2
 *                   /     \     /  \         /  \          /    \
 *                 {122}   {12} {d}   {1}    {22}  {2}     {d}     {}                  2
 *                       
 */
public class AllSubsetsWithDuplicates {
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}
		// sort the given array first
		Arrays.sort(nums);
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

        plan.add(nums[level]); // add
        helper(res, plan, level + 1, nums); // dfs
        plan.remove(plan.size() - 1); // backtracking
        // after backtracking, if meet duplicate which has been processed again, directly level++
        while (level < nums.length - 1 && nums[level] == nums[level + 1]) {
            level++;
        }
        helper(res, plan, level + 1, nums); // not add
	}
}
