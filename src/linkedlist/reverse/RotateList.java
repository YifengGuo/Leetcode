package linkedlist.reverse;
/**
 *  
 * @author guoyifeng
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.

	Example 1:
	
	Input: 1->2->3->4->5->NULL, k = 2
	Output: 4->5->1->2->3->NULL
	Explanation:
	rotate 1 steps to the right: 5->1->2->3->4->NULL
	rotate 2 steps to the right: 4->5->1->2->3->NULL
	Example 2:
	
	Input: 0->1->2->NULL, k = 4
	Output: 2->0->1->NULL
	Explanation:
	rotate 1 steps to the right: 2->0->1->NULL
	rotate 2 steps to the right: 1->2->0->NULL
	rotate 3 steps to the right: 0->1->2->NULL
	rotate 4 steps to the right: 2->0->1->NULL
 */
/*
 * time = O(n)
 * space = O(1)
 */
public class RotateList {
	public ListNode rotateRight(ListNode head, int k) {
		if (head == null || head.next == null) {
			return head;
		}
		int len = getLen(head);
		k = k % len;
		if (k == 0) {
			return head;
		}
		// step 1: reverse whole list
		ListNode reverseHead = reverse(head);
		// step 2: find offset node
		ListNode cur = reverseHead;
		int count = 1;
		while (count < k) {
			cur = cur.next;
			count += 1;
		}
		// reverse two partial list and connect each other
		ListNode secondHead = cur.next;
		cur.next = null;
		ListNode newHead = reverse(reverseHead);
		secondHead = reverse(secondHead);
		reverseHead.next = secondHead;
		return newHead;
	}

	private ListNode reverse(ListNode head) {
		ListNode cur = head;
		ListNode prev = null;
		while (cur != null) {
			ListNode next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		return prev;
	}

	// get the length of this linked list
	private int getLen(ListNode head) {
		int res = 0;
		ListNode cur = head;
		while (cur != null) {
			cur = cur.next;
			res += 1;
		}
		return res;
	}
}
