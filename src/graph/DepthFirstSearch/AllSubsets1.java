package graph.DepthFirstSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yifengguo
 * Given a set of distinct integers, nums, return all possible subsets (the power set).

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
 * time: O(2 ^ n) n = nums.length
 */
public class AllSubsets1 {
	public class Solution {
		public List<List<Integer>> subsets(int[] nums) {
			List<List<Integer>> res = new ArrayList<>();
			if (nums == null || nums.length == 0) {
				return res;
			}
			List<Integer> cur = new ArrayList<>();
			dfs(res, cur, 0, nums);
			return res;
		}

		private void dfs(List<List<Integer>> res, List<Integer> cur, int level, int[] nums) {
			// base case
			if (level == nums.length) {
				res.add(new ArrayList<Integer>(cur));
				return;
			}
			// add 'a'
	        cur.add(nums[level]);
			dfs(res, cur, level + 1, nums);
	        cur.remove(cur.size() - 1); // backtracking
	        dfs(res, cur, level + 1, nums); // not add 'a'
		}
	}
}
