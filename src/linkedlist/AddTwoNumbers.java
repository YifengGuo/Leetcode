package linkedlist;
/**
 * 
 * @author guoyifeng
 * You are given two linked lists representing two non-negative numbers. 
 * The digits are stored in reverse order and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked list.  

	Example
	
	Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	
	Output: 7 -> 0 -> 8
 */
class ListNode {
	ListNode next;
	int val;
	ListNode(int val) {
		this.val = val;
	}
}
public class AddTwoNumbers {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) {
			return l1 == null ? l2 : l1;
		}
		ListNode p = l1;
		ListNode q = l2;
		int carry = 0;
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		while (p != null || q != null) {
			int v1 = p != null ? p.val : 0;
			int v2 = q != null ? q.val : 0;
			int sum = v1 + v2 + carry;
			cur.next = new ListNode(sum % 10);
			carry = sum / 10;
			p = p == null ? null : p.next;
			q = q == null ? null : q.next;
			cur = cur.next;
		}
		// if we have non-zero carry from low bit, we need to update it to the highest bit
		if (carry != 0) {
			cur.next = new ListNode(carry);
		}
		return dummy.next;
	}
}