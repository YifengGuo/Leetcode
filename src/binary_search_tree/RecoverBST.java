package binary_search_tree;
/**
 * 
 * @author guoyifeng
 * Two elements of a binary search tree (BST) are swapped by mistake.

	Recover the tree without changing its structure.
	
	Note:
	A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

 */
//       5
//      / \
//     3   6
//    / \   \  
//   1   4   7
/*
 * basic idea: to traverse binary tree with O(1) space, we need to use Morris Traversal
 *            Morris Traversal (in-order):
 *                       1. if current.left == null  -> print current node and set current = current.right
 *                       2. if current.left != null  -> find the predecessor of current in in-order traversal:
 *                       	2.1 if predecessor.right == null -> set predecessor.right = current and set current = current.right
 *                          2.2 if predecessor.right is current -> set predecessor.right = null to recover the structure of tree.
 *                              print current and set current = current.right
 *                       3. loop on 1 and 2 until current is null
 *                       
 *                       when move current, it means left is null or we have print all elements in left subtree for it is in-order traversal
 *                       so current pointer is always moved to its right child
 *                              
 * for graph demo: http://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html
 * 
 *     time = O(n)
 *     space = O(1)
 */
public class RecoverBST {
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }
        // cur is current node
        // prev is the previous node of current which is to check monotony of BST
        TreeNode cur = root, prev = null;
        TreeNode first = null, second = null; // two nodes which needs swapping to recover the BST
        TreeNode tmp = null;
        while (cur != null) {
            if (cur.left == null) { // case 1
                if (prev != null && prev.key > cur.key) {
                    if (first == null) {
                        first = prev;
                        second = cur;
                    } else {
                        second = cur;
                    }
                }
                prev = cur;
                cur = cur.right;
            } else { // case 2
                tmp = cur.left; // tmp is the predecessor of cur in in-order traversal
                while (tmp.right != null && tmp.right != cur) { // and this node is the right-most node in cur's left subtree
                    tmp = tmp.right;
                }
                if (tmp.right == null) {  // case 2.1
                    tmp.right = cur;
                    cur = cur.left;
                } else { // case 2.2   tmp.right != null (is cur)
                    tmp.right = null; // reset tmp.right = null to recover the structure of the tree
                    if (prev != null && prev.key > cur.key) {
                        if (first == null) {
                            first = prev;
                            second = cur;
                        } else {
                            second = cur;
                        }
                    }
                    // move cur and prev
                    prev = cur;
                    cur = cur.right;
                }
            }     
        }
        // swap the value of two wrong ordered nodes 
        if (first != null && second != null) {
            int val = first.key;
            first.key = second.key;
            second.key = val;
        }
    }
}
