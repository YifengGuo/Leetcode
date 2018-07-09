package recursion.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author guoyifeng
 * Given a binary tree, return all root-to-leaf paths.

	Note: A leaf is a node with no children.
	
	Example:
	
	Input:
	
	   1
	 /   \
	2     3
	 \
	  5
	
	Output: ["1->2->5", "1->3"]
	
	Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */

class TreeNode {
	int key;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		key = x;
	}

}
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            return res;
        }
        helper(res, sb, root);
        return res;
    }
    
    private void helper(List<String> res, StringBuilder sb, TreeNode root) {
        // base case
        if (root.left == null && root.right == null) {
            String val = String.valueOf(root.key);
            sb.append(val);
            res.add(sb.toString());
            sb.setLength(sb.length() - val.length()); // remove leaf node value
            return;
        }
        String val = String.valueOf(root.key);
        sb.append(val);
        if (root.left != null) {
            sb.append("->");
            helper(res, sb, root.left);
            sb.setLength(sb.length() - 2); // remove "->"
        }
        
        if (root.right != null) {
            sb.append("->");
            helper(res, sb, root.right);
            sb.setLength(sb.length() - 2); // remove "->"
        }
        // backtracking on current level
        sb.setLength(sb.length() - val.length());
    }
}
