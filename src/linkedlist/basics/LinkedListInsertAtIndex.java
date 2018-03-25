package linkedlist.basics;
/**
 * 
 * @author guoyifeng
 * Insert a new element at a specific index in the given linked list. 
 * The index is 0 based, and if the index is out of the list's scope, 
 * you do not need to do anything.

	Examples:
	
	1 -> 2 -> 3 -> null, insert 4 at index 3, --> 1 -> 2 -> 3 -> 4 -> null
	
	1 -> 2 -> null, insert 4 at index 0, --> 4 -> 1 -> 2 -> null


 */
class ListNode {
	public int value;
	public ListNode next;

	public ListNode(int value) {
		this.value = value;
		next = null;
	}
}
// the insertion position is before the cur node
//            1   ->   2   ->   3   -> 4
// index    0      1       2        3     4
public class LinkedListInsertAtIndex {
	public ListNode insert(ListNode head, int index, int value) {
		if (head == null && index == 0) {
			return new ListNode(value);
		}
		if (head.next == null && index == 1) {
			head.next = new ListNode(value);
			return head;
		}
		int count = 0;
		ListNode cur = head;
		ListNode prev = null;
		while (cur != null) {
			if (index == count) {
				if (prev == null) {
					ListNode newHead = new ListNode(value);
					newHead.next = cur;
					return newHead;
				} else {
					prev.next = new ListNode(value);
					prev.next.next = cur;
					return head;
				}
			}
			count++;
			prev = cur;
			cur = cur.next;
		}
		
		if (index == count) {
	    	prev.next = new ListNode(value);  
	    }
		return head;
	}
}
