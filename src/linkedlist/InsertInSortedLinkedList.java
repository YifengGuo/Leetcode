package linkedlist;

/**
 * 
 * @author @Yifeng Insert a value in a sorted linked list.
 * 
 *         Examples
 * 
 *         L = null, insert 1, return 1 -> null L = 1 -> 3 -> 5 -> null, insert
 *         2, return 1 -> 2 -> 3 -> 5 -> null L = 1 -> 3 -> 5 -> null, insert 3,
 *         return 1 -> 3 -> 3 -> 5 -> null L = 2 -> 3 -> null, insert 1, return
 *         1 -> 2 -> 3 -> null
 * 
 */
public class InsertInSortedLinkedList {
	public ListNode insert(ListNode head, int value) {
		if (head == null) {
			return new ListNode(value);
		}
		if (value < head.val) {
			ListNode node = new ListNode(value);
			node.next = head;
			return node;
		}
		ListNode cur = head;
		ListNode prev = null;
		// insert in the middle
		while (cur != null) {
			if (value <= cur.val) {
				ListNode node = new ListNode(value);
				node.next = cur;
				// check if prev is null
				// if prev is null, means node is new head
				if (prev != null) {
					prev.next = node;
				} else {
					head = node;
				}
				return head;
			}
			// move pointers
			prev = cur;
			cur = cur.next;
		}
		// if fail to insert at head or middle
		// it means node should be appended at tail
		prev.next = new ListNode(value);
		return head;
	}
}