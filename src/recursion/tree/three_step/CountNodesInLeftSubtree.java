package recursion.tree.three_step;
/**
 * 
 * @author yifengguo
 * count how many nodes belong to current node's left-subtree
 */
/*
 *                     root
 *                   /     \
 *                 l1(X)    r1
 *              /       \ 
 *             l11(X1)  r12(X2)
 *           /      \
 *          l21(X21) r22(X22)
 */
public class CountNodesInLeftSubtree {
	class TreeNode_1 {
		TreeNode_1 left;
		TreeNode_1 right;
		int value;
		int total_left; // how many nodes belong to its left-subtree
		public TreeNode_1(int value) {
			this.value = value;
			total_left = 0;
		}
	}
	
	public int countLeftSubtree(TreeNode_1 root) {
		// base case
		if (root == null) {
			return 0;
		}
		// step 1
		int left = countLeftSubtree(root.left);
		int right = countLeftSubtree(root.right);
		
		// step 2 current layer process
		root.total_left = left; // update current node's total_left field
		
		// step 3
		return left + right + 1; // parent node's sub-tree include current node's left and right and itself
		
	}
}
