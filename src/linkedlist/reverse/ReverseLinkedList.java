package linkedlist.reverse;

/**
 * 
 * @author yifeng
 * Reverse a singly-linked list.

    Examples

    L = null, return null
    L = 1 -> null, return 1 -> null
    L = 1 -> 2 -> 3 -> null, return 3 -> 2 -> 1 -> null
 */

/*	  to reverse 1 -> 2 -> 3 -> null  we need to reverse 2 -> 3 -> null .... after recursion  3 -> 2 -> 1 -> null
 * 	  
 *    1 -> 2 -> 3 -> null
 *    1 -->   2 -> 3 -> null
 *    1 -->   2 -->   <- 3
 *    1 --> 2 <- 3
 *    null <- 1 <- 2 <- 3
 *    
 *    and the base case should be head == null or head.next == null
 */
public class ReverseLinkedList {
	// method 1: recursion
//    public ListNode reverse(ListNode head) {
//        if(head == null || head.next == null) {
//            return head;
//        }
//        ListNode newHead = reverse(head.next);
//        head.next.next = head;
//        head.next = null;
//        return newHead;
//
//    }
	
	// method 2: iteration
	public ListNode reverse(ListNode head) {
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
