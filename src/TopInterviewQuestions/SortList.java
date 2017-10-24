package TopInterviewQuestions;
/**
 * 
 * @author guoyifeng
 * Sort a linked list in O(n log n) time using constant space complexity.
 */
/*
 * basic idea: based on time and space complexity requirement,
 *             merge sort is a good way to solve the problem
 */
class ListNode {
	ListNode next;
	int val;
	public ListNode(int val) {
		this.val = val;
	}
}
public class SortList {
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode prev = null;
		ListNode fast = head;
		ListNode slow = head;
		// find middle node of list and cut it into two halves
		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		// slow now is the head node of second half
		prev.next = null;
		// recursively sort the two halves
		ListNode l1 = sortList(head);
		ListNode l2 = sortList(slow);

		// merge as classical merge two lists in sorting order
		return merge(l1, l2);

	}

	public ListNode merge(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				cur.next = l1;
				cur = cur.next;
				l1 = l1.next;
			} else {
				cur.next = l2;
				cur = cur.next;
				l2 = l2.next;
			}
		}
		if (l1 == null) {
			cur.next = l2;
		} else {
			cur.next = l1;
		}
		return dummy.next;
	}
}
