package graph.DepthFirstSearch.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author guoyifeng
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

	Example:
	
	Input: n = 4, k = 2
	Output:
	[
	  [2,4],
	  [3,4],
	  [2,3],
	  [1,2],
	  [1,3],
	  [1,4],
	]
 */
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k > n) {
            return res;
        }
        List<Integer> plan = new ArrayList<>();
        // dfs
        helper(res, plan, n, k, 1);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> plan, int n, int k, int level) {
        // base case
        if (plan.size() == k) {
            res.add(new ArrayList<>(plan));
            return;
        }
        // dfs on the level
        // traversed number int the previous positions will not appear again
        for (; level <= n; level++) {
            plan.add(level);
            helper(res, plan, n, k, level + 1);
            plan.remove(plan.size() - 1);
        }
    }
}
