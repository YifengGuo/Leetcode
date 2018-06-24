package linkedlist;

/**
 * 
 * @author guoyifeng Reorder the given singly-linked list N1 -> N2 -> N3 -> N4
 *         -> … -> Nn -> null to be N1- > Nn -> N2 -> Nn-1 -> N3 -> Nn-2 -> … ->
 *         null
 * 
 *         Examples
 * 
 *         L = null, is reordered to null L = 1 -> null, is reordered to 1 ->
 *         null L = 1 -> 2 -> 3 -> 4 -> null, is reordered to 1 -> 4 -> 2 -> 3
 *         -> null L = 1 -> 2 -> 3 -> null, is reordred to 1 -> 3 -> 2 -> null
 */
public class ReorderLinkedList {
	public ListNode reorder(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		// step 1: separate the linkedlist into two parts by middle node
		ListNode cur = head;
		ListNode prev = null;
		ListNode fast = head;
		ListNode slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			prev = slow;
			slow = slow.next;
		}
		ListNode mid = slow;
		prev.next = null;

		// step 2: reverse second half
		ListNode newHead = reverse(mid);

		// step 3: merge two halves
		ListNode res = merge(head, newHead);
		return res;
	}

	private ListNode reverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode next = head.next;
		ListNode newHead = reverse(next);
		head.next = null;
		next.next = head;
		return newHead;
	}

	private ListNode merge(ListNode one, ListNode two) {
		if (one == null || two == null) {
			return one == null ? two : one;
		}
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		ListNode cur1 = one;
		ListNode cur2 = two;
		while (cur1 != null && cur2 != null) {
			cur.next = cur1;
			cur1 = cur1.next;
			cur = cur.next;
			cur.next = cur2;
			cur2 = cur2.next;
			cur = cur.next;
		}
		cur.next = cur1 != null ? cur1 : cur2;
		return dummy.next;
	}
}
