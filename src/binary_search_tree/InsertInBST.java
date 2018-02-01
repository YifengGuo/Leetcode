package binary_search_tree;
/**
 * 
 * @author yifengguo
 *   Insert a key in a binary search tree if the binary search tree does not already contain the key. Return the root of the binary search tree.

Assumptions

There are no duplicate keys in the binary search tree

If the key is already existed in the binary search tree, you do not need to do anything

Examples

        5

      /    \

    3        8

  /   \

 1     4

insert 11, the tree becomes

        5

      /    \

    3        8

  /   \        \

 1     4       11

insert 6, the tree becomes

        5

      /    \

    3        8

  /   \     /  \

 1     4   6    11
 */
class TreeNode {
	public int key;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int key) {
		this.key = key;
	}
}

public class InsertInBST {
	// method 1: recursion
	public TreeNode insert(TreeNode root, int key) {
		if (root == null) {
			return new TreeNode(key);
		}
		helper(root, key, null);
		return root;
	}

	private void helper(TreeNode root, int key, TreeNode parent) {
		// base case
		// after find the right place to insert
		// check parent and determine insert in left side or right side
		if (root == null) {
			if (parent.key > key) {
				parent.left = new TreeNode(key);
			} else {
				parent.right = new TreeNode(key);
			}
			return;
		}
		if (key < root.key) {
			helper(root.left, key, root);
		} else if (key > root.key) {
			helper(root.right, key, root);
		} else { // else key == root.key, do not have to insert, return directly
			return;
		}
	}
	
	// method 2: iteration
//	public TreeNode insert(TreeNode root, int key) {
//		if (root == null) {
//			return new TreeNode(key);
//		}
//		TreeNode cur = root;
//		TreeNode parent = null;
//		while (cur != null) {
//			if (cur.key > key) {
//				parent = cur;
//				cur = cur.left;
//			} else if (cur.key < key) {
//				parent = cur;
//				cur = cur.right;
//			} else {
//				return root;
//			}
//		}
//		// now cur is null and parent is the root for the place to insert
//		if (parent.key > key) {
//			parent.left = new TreeNode(key);
//		} else {
//			parent.right = new TreeNode(key);
//		}
//		return root;
//	}
}
