package recursion.tree.reconstruct_tree;

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
class ListNode {
	ListNode next;
	int val;
	ListNode(int val) {
		this.val = val;
	}
}
public class ReconstructSortedListToBST {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        return helper(list);
    }
    
    private TreeNode helper(List<Integer> list) {
        if (list.size() == 0) {
            return null;
        }
        if (list.size() == 1) {
            return new TreeNode(list.get(0));
        }
        TreeNode root = new TreeNode(list.get(list.size() / 2));
        List<Integer> left = partition(list, 0, list.size() / 2 - 1);
        List<Integer> right = partition(list, list.size() / 2 + 1, list.size() - 1);
        root.left = helper(left);
        root.right = helper(right);
        return root;
    }
    
    // partition list into two halves except middle element
    private List<Integer> partition(List<Integer> list, int start, int end) {
        List<Integer> res = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            res.add(list.get(i));
        }
        return res;
    }
}