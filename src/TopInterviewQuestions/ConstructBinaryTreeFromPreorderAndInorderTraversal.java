package TopInterviewQuestions;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author yifengguo
 	Given preorder and inorder traversal of a tree, construct the binary tree.
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        // use a hash_map to map root.val with its idx in inorder[]
        // left elements to the idx of root is left subtree in inorder
        // right elements to the idx of root is right subtree in inorder
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode root = helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
        return root;
    }
    
    private TreeNode helper(int[] pre, int preLeft, int preRight, int[] in, int inLeft, int inRight, Map<Integer, Integer> map) {
        if (inLeft > inRight) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preLeft]);
        int leftSize = map.get(root.val) - inLeft; // leftsize could use to locate subtrees in inorder and preorder
        root.left = helper(pre, preLeft + 1, preLeft + leftSize, in, inLeft, inLeft + leftSize - 1, map);
        root.right = helper(pre, preLeft + leftSize + 1, preRight, in, inLeft + leftSize + 1, inRight, map);
        return root;
    }
}
