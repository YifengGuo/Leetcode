package binary_search_tree;
/**
 * 
 * @author yifengguo
 * Find the target key K in the given binary search tree, 
 * return the node that contains the key if K is found, otherwise return null.

	Assumptions
	
	There are no duplicate keys in the binary search tree
 */
/*
 * basic idea: three step of DFS in tree
 */
public class SearchInBST {
	public TreeNode search(TreeNode root, int key) {
		// Write your solution here.
		if (root == null || root.key == key) {
			return root;
		}
		TreeNode left = search(root.left, key);
		TreeNode right = search(root.right, key);
		return left == null ? right : left;
	}
}
