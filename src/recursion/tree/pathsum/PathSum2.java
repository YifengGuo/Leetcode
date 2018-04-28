package recursion.tree.pathsum;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author yifengguo
 * DescriptionHintsSubmissionsDiscussSolution
	Pick One
	Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
	
	Note: A leaf is a node with no children.
	
	Example:
	
	Given the below binary tree and sum = 22,
	
	      5
	     / \
	    4   8
	   /   / \
	  11  13  4
	 /  \    / \
	7    2  5   1
	Return:
	
	[
	   [5,4,11,2],
	   [5,8,4,5]
	]
 */
/*
 * dfs
 * 	time = O(n)
 * 	space = O(height)
 */
public class PathSum2 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> plan = new ArrayList<>();

        helper(root, res, plan, sum, 0);

        return res;
        
    }
    
    private void helper(TreeNode root, List<List<Integer>> res, List<Integer> plan, int sum, int cur) {
        if (root == null) {
            return;
        }
        cur += root.val;
        plan.add(root.val);
        if (cur == sum && root.left == null && root.right == null) {
            res.add(new ArrayList<>(plan));
            plan.remove(plan.size() - 1); // do not forget recovering here
            return;
        }
        
        // DFS on both subtree
        helper(root.left, res, plan, sum, cur);
        helper(root.right, res, plan, sum, cur);
        plan.remove(plan.size() - 1); // recover
    }
}
