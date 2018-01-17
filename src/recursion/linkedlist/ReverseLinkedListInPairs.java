package recursion.linkedlist;
/**
 * 
 * @author yifengguo
 * Reverse pairs of elements in a singly-linked list.

	Examples
	
	L = null, after reverse is null
	L = 1 -> null, after reverse is 1 -> null
	L = 1 -> 2 -> null, after reverse is 2 -> 1 -> null
	L = 1 -> 2 -> 3 -> null, after reverse is 2 -> 1 -> 3 -> null

 */
/*
 * basic idea:
 *       for 1 -> 2 -> 3 -> 4 -> 5 as example
 *       we need to reverse 1 -> 2 to 2 -> 1
 *       and what is next to node 1 is the result of subproblem
 *       and the subproblem is starting from the third node
 *       which is the next of the head.next
 *       and this is the recursion rule:
 *       	reverse the first two nodes
 *          linked reversed tail to the result of subproblem (no matter it returns null, one node or a linkedlist)
 */
public class ReverseLinkedListInPairs {
	public ListNode reverseInPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode cur = head;
		ListNode next = cur.next;
		ListNode res = reverseInPairs(next.next);
		next.next = cur;
		cur.next = res;
		return next;
	}
}
