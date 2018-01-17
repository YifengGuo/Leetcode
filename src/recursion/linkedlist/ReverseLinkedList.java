package recursion.linkedlist;
/**
 * 
 * @author yifengguo
 * Reverse a singly-linked list.

	Examples
	
	L = null, return null
	L = 1 -> null, return 1 -> null
	L = 1 -> 2 -> 3 -> null, return 3 -> 2 -> 1 -> null
 


 */
class ListNode {
	public int value;
	public ListNode next;
	public ListNode(int value) {
		this.value = value;
		next = null;
	}
}
public class ReverseLinkedList {
	public ListNode reverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode next = head.next;
		ListNode newHead = reverse(next);
		next.next = head;
		head.next = null;
		return newHead;
	}
	
	public ListNode reverse_iterative(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode cur = head;
		ListNode prev = null;
		ListNode next = null;
		while (cur != null) {
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		return prev;
	}
}
