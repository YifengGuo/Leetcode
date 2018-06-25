package recursion.tree.uniqueBST;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
	TreeNode left;
	TreeNode right;
	int val;
	
	TreeNode(int val) {
		this.val = val;
	}
}
/**
 * 
 * @author guoyifeng
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

	Example:
	
	Input: 3
	Output:
	[
	  [1,null,3,2],
	  [3,2,null,1],
	  [3,1,null,null,2],
	  [2,1,3],
	  [1,null,2,null,3]
	]
	Explanation:
	The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 */
public class UniqueBST2 {
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        } 
        return genTrees(1, n);
     }
     /**
      * generate all possible combination of BST given start and end integer
      * eg: given 1, 2, 3   first consider 1 as root, left will return null, and right is the recursion case for {2, 3}
      * @param start
      * @param end
      * @return
      */
     private List<TreeNode> genTrees(int start, int end) {
         List<TreeNode> res = new ArrayList<>();
         // base case
         // invalid case, need to add null to list
         if (start > end) {
             res.add(null);
             return res;
         }
         // eg: when 2 is root, it only has 1 on the left side
         if (start == end ) {
             res.add(new TreeNode(start));
             return res;
         }
         
         // process on normal cases
         // try to find all combinations on both left side and right side for current integer as root
         for (int i = start; i <= end; i++) {
             List<TreeNode> left = genTrees(start, i - 1);
             List<TreeNode> right = genTrees(i + 1, end);
             
             
             for (TreeNode lnode : left) {
                 for (TreeNode rnode : right) {
                     TreeNode curRoot = new TreeNode(i);
                     curRoot.left = lnode;
                     curRoot.right = rnode;
                     res.add(curRoot);
                 }
             }
         }
         return res;
     }
 }