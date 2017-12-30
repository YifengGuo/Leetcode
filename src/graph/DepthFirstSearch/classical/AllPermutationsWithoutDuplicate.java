package graph.DepthFirstSearch.classical;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author guoyifeng
 	Given a collection of distinct numbers, return all possible permutations.

	For example,
	[1,2,3] have the following permutations:
	[
	  [1,2,3],
	  [1,3,2],
	  [2,1,3],
	  [2,3,1],
	  [3,1,2],
	  [3,2,1]
	]
 */
/*
 * basic idea: swap + DFS + swap back
 * 	for problems which each element will occur in last permutation(or result) and 
 * 	initially they maybe in chaos order, this kind of problems we firstly think use
 *  swap + DFS + swap back
 *  
 *  unlike AllSubsets problem, permutations have no relationship to each other, so we 
 *  must initialize ArrayList (partial plan) whenever level == nums.length (arrive the bottom of DFS tree)
 *  
 *  for AllSubsets problem, for we use add or not add idea, so backtracking will remove latest added element, the last layer partial result
 *  is useful to next layer dfs, so we can initialize a ArrayList out of the function
 */

/*
 * Demo for all permutation
 * 							         1 2 3
 *                         /           |         \
 *                      1(23)        2(13)         3(12)                     level 0
 *                      /  \         /    \        /   \
 *                    2(3) 3(2)     1(3) 3(1)     1(2) 2(1)                  level 1
 *                     |    |         |    |       |    |    
 *                     3    2        3     1       2    1                    level 2
 *                     
 *      time = O(n!)
 *      space = O(n)
 */ 
public class AllPermutationsWithoutDuplicate {
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}
		helper(res, 0, nums);
		return res;
	}

	private void helper(List<List<Integer>> res, int level, int[] nums) {
        // base case
        if (level == nums.length) {
            List<Integer> plan = new ArrayList<>();
            for (int i : nums) {
                plan.add(i);
            }
            res.add(plan);
            return;
        }
        
        for (int i = level; i < nums.length; i++) {
            swap(nums, level, i);
            helper(res, level + 1, nums);
            swap(nums, level, i);
        }
	}

	private void swap(int[] nums, int a, int b) {
		int tmp = nums[a];
		nums[a] = nums[b];
		nums[b] = tmp;
	}
}
