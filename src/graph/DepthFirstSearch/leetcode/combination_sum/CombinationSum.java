package graph.DepthFirstSearch.leetcode.combination_sum;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author guoyifeng
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), 
 * find all unique combinations in candidates where the candidate numbers sums to target.

	The same repeated number may be chosen from candidates unlimited number of times.
	
	Note:
	
	All numbers (including target) will be positive integers.
	The solution set must not contain duplicate combinations.
	Example 1:
	
	Input: candidates = [2,3,6,7], target = 7,
	A solution set is:
	[
	  [7],
	  [2,2,3]
	]
	Example 2:
	
	Input: candidates = [2,3,5], target = 8,
	A solution set is:
	[
	  [2,2,2,2],
	  [2,3,3],
	  [3,5]
	]
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0 || target < 0) {
            return res;
        }
        List<Integer> plan = new ArrayList<>();
        helper(res, plan, candidates, target, 0);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> plan, int[] candidates, int target, int start) {
        // base case
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(plan));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            plan.add(candidates[i]);
            helper(res, plan, candidates, target - candidates[i], i);
            plan.remove(plan.size() - 1);
        }
    }
}