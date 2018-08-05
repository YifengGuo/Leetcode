package graph.DepthFirstSearch.leetcode.combination_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author guoyifeng
 * Given a collection of candidate numbers (candidates) and a target number (target), 
 * find all unique combinations in candidates where the candidate numbers sums to target.

	Each number in candidates may only be used once in the combination.
	
	Note:
	
	All numbers (including target) will be positive integers.
	The solution set must not contain duplicate combinations.
	Example 1:
	
	Input: candidates = [10,1,2,7,6,1,5], target = 8,
	A solution set is:
	[
	  [1, 7],
	  [1, 2, 5],
	  [2, 6],
	  [1, 1, 6]
	]
	Example 2:
	
	Input: candidates = [2,5,2,1,2], target = 5,
	A solution set is:
	[
	  [1,2,2],
	  [5]
	]
 */
public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0 || target < 0) {
            return res;
        } 
        Arrays.sort(candidates);
        List<Integer> plan = new ArrayList<>();
        helper(res, plan, candidates, 0, target);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> plan, int[] candidates, int start, int target) {
        // base case
        if (target < 0) {
            return;
        }
        if (target == 0) {
            // Collections.sort(plan); // the array has been sorted already
            if (!res.contains(plan)) { // if plan's element combination has been existed, res.contains() will return true
                res.add(new ArrayList<>(plan));
            }
            return;
        }
        // 1 1 2 5 6 7 10
        // 1 1 1 3 3 5
        for (int i = start; i < candidates.length; i++) {
            plan.add(candidates[i]);
            helper(res, plan, candidates, i + 1, target - candidates[i]);
            plan.remove(plan.size() - 1); // backtracking
        }
    }
}
