package graph.BreadthFirstSearch;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
/**
 * 
 * @author guoyifeng
 * Get the list of keys in a given binary tree layer by layer in zig-zag order.

Examples

        5

      /    \

    3        8

  /   \        \

 1     4        11

the result is [5, 3, 8, 11, 4, 1]

Corner Cases

What if the binary tree is null? Return an empty list in this case.
How is the binary tree represented?

We use the level order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

    1

  /   \

 2     3

      /

    4
 */
/*
 *  demo:
 *                  5                   <------  odd
 *                /   \ 
 *              2       10              --------> even
 *             / \     / \
 *            1  3     8  13            <--------  odd
 *               \        / \ 
 *               4       11  15          -------->  even
 *               
 *      
 *     from the graph above, we should know 
 *     1. when at odd level, we need to poll out
 *        TreeNode from tail to head in the deque, so we need to invoke pollLast() and 
 *        invoke pollFirst at even level
 *     2. Then focus on how to offer child when at different level
 *               when at first level,   deque  ===>         5 
 *               we either        (2, 10)  5        pollFirst
 *                            or      5 (10, 2)     pollLast
 *               but we know 2 ,10 are at even level, so they are needed to be polled as pollFirst() logic
 *               so (2, 10)   5 is the only way we offer child node of 5
 *               so when at odd level,   invoke offerFirst() for right child and then left child
 *               
 *               
 *               when at even level, 2, 10
 *               we need somehow make deque look like   (2) , 10 , 1, 3, 8, 13  ===> 13, 8, 3, 1
 *               because when at next level (odd) we will poll nodes as pollLast() 
 *               so 2's child 1, 3 shall be offered as offerLast for left then right, so does 10
 *              
 *      
 *  time = O(n)
 *  space = O(n)
 */
public class GetKeysInBinaryTreeLayerByLayerZigZagOrder {
	public List<Integer> zigZag(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		Deque<TreeNode> deque = new LinkedList<>();
		int level = 1;
		deque.offerFirst(root);
		while (!deque.isEmpty()) {
			int size = deque.size();
			for (int i = 0; i < size; i++) {
				if (level % 2 != 0) { // odd level
					TreeNode cur = deque.pollLast();  
					res.add(cur.val);
					if (cur.right != null) {
						deque.offerFirst(cur.right);
					}
					if (cur.left != null) {
						deque.offerFirst(cur.left);
					}
				} else { // even level
					TreeNode cur = deque.pollFirst();
					res.add(cur.val);
					if (cur.left != null) {
						deque.offerLast(cur.left);
					}
					if (cur.right != null) {
						deque.offerLast(cur.right);
					}
				}
			}
			level++; // advance level by 1
		}
		return res;
	}
}
