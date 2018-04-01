package graph.DepthFirstSearch.classical;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author guoyifeng
 
	Given a collection of numbers that might contain duplicates, return all possible unique permutations.
	
	For example,
	[1,1,2] have the following unique permutations:
	[
	  [1,1,2],
	  [1,2,1],
	  [2,1,1]
	]
 */

/*
 * 	Demo:
 *                                  1 1 2
 *                                   set
 *                    /               |                 \     
 *                  1(12)           1(12) (skip)       2(11)                      level 0
 *                  set                                set
 *                 /    \           /   \             /   \   
 *                1(2)   2(1)      1(2)  2(1)        1(1)  1(1)(skip)             level 1  
 *                |       |         |      |          |     |
 *                2       1         2      1          1     1                     level 2
 *                
 *                to de-duplicate, on each layer of dfs tree, we initialize a set.
 *                Only if set has not current element, shall we run dfs on it. If
 *                set already has it, it means we have run dfs on it and already have
 *                found all possible permutation of it
 *                
 *    Time = O(n!)
 *    Space = O(n)
 *                
 * 
 */
public class AllPermutationsWithDuplicates {
	public List<List<Integer>> permuteUnique(int[] nums) {
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
		Set<Integer> set = new HashSet<>();
		for (int i = level; i < nums.length; i++) {
            swap(nums, i, level);
			if (!set.contains(nums[level])) {
                set.add(nums[level]);
				helper(res, level + 1, nums);
			}
            swap(nums, i, level);
		}
	}

	private void swap(int[] nums, int a, int b) {
		int tmp = nums[a];
		nums[a] = nums[b];
		nums[b] = tmp;
	}
}

