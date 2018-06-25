package recursion.tree.reconstruct_tree;

import java.util.Arrays;
/**
 * 
 * @author guoyifeng
 * Given the postorder traversal sequence of a binary search tree, reconstruct the original tree.

Assumptions

The given sequence is not null
There are no duplicate keys in the binary search tree
Examples

postorder traversal = {1, 4, 3, 11, 8, 5}

the corresponding binary search tree is

        5

      /    \

    3        8

  /   \        \

1      4        11

How is the binary tree represented?

We use the pre order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, #, #, 3, 4, #, #, #] represents the following binary tree:

    1

  /   \

 2     3

      /

    4
 */
public class ReconstructBinarySearchTreeWithPostorderTraversal {
	public TreeNode reconstruct(int[] post) {
		if (post == null || post.length == 0) {
			return null;
		}
		return helper(post);
	}

	private TreeNode helper(int[] post) {
		// base case
		if (post.length <= 0) {
			return null;
		}
		int rootVal = post[post.length - 1];
		TreeNode root = new TreeNode(rootVal);
		int leftEnd = 0;
		int rightStart = 0;
		for (int i = post.length - 1; i >= 0; i--) {
			if (post[i] < rootVal) {
				leftEnd = i;
				rightStart = i + 1;
				break;
			}
		}
		int[] left = Arrays.copyOfRange(post, 0, rightStart);
		int[] right = Arrays.copyOfRange(post, rightStart, post.length - 1);
		root.left = helper(left);
		root.right = helper(right);
		return root;
	}
}
