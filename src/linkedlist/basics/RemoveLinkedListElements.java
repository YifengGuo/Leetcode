package linkedlist.basics;
/**
 * 
 * @author guoyifeng
 * Remove all elements from a linked list of integers that have value val.

	Example:
	
	Input:  1->2->6->3->4->5->6, val = 6
	Output: 1->2->3->4->5
 */
//        1     1
//       cur
// prev
// dummy
public class RemoveLinkedListElements {
	public ListNode removeElements(ListNode head, int val) {
		if (head == null) {
			return null;
		}
		ListNode dummy = new ListNode(0);
		ListNode cur = head;
		ListNode prev = dummy;
		dummy.next = cur;
		while (cur != null) {
			if (cur.value == val) {
				prev.next = cur.next;
			} else {
				prev = cur;
			}
			cur = cur.next;
		}
		return dummy.next;
	}
}
