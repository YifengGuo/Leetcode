package graph.DepthFirstSearch.leetcode;

import java.util.List;
import java.util.ArrayList;

/**
 * 
 * @author guoyifeng
 * Find all possible combinations of k numbers that add up to a number n, 
 * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

	Note:
	
	All numbers will be positive integers.
	The solution set must not contain duplicate combinations.
	Example 1:
	
	Input: k = 3, n = 7
	Output: [[1,2,4]]
	Example 2:
	
	Input: k = 3, n = 9
	Output: [[1,2,6], [1,3,5], [2,3,4]]
 */
/*
 * time = O(nk)
 * space = O(k)
 */
public class CombinationSum3 {
	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> res = new ArrayList<>();
		if (k > 9 || n > 45) {
			return res;
		}
		List<Integer> plan = new ArrayList<>();
		helper(k, 1, n, plan, res);
		return res;
	}

	private void helper(int k, int i, int n, List<Integer> plan, List<List<Integer>> res) {
		// base case
		if (n < 0 || plan.size() > k) {
			return;
		}
		if (plan.size() == k && n == 0) {
			res.add(new ArrayList<>(plan));
			return;
		}
		for (int j = i; j <= 9; j++) {
			plan.add(j);
			helper(k, j + 1, n - j, plan, res);
			plan.remove(plan.size() - 1);
		}
	}
}