package linkedlist;

/**
 * 
 * @author yifengguo 
 *         Given a linked list, return the node where the cycle
 *         begins. If there is no cycle, return null.
 * 
 *         Note: Do not modify the linked list.
 * 
 *         Follow up: Can you solve it without using extra space?
 * 
 * 
 */
/*
 * basic idea:
 * 			use fast and slow pointer to detect cycle
 * 		    if list has cycle, move fast or slow pointer 
 *          back to head, and move both pointer one step
 *          forward each time
 *          once they meet again, the node they meet is 
 *          start of cycle
 *          
 *          This problem is prefix of find the Duplicate number (LeetCode 287)
 * time = O(n)
 * space = O(1)
 */
public class LinkedListCycle2StartOfCycle {
	public ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null) {
			return null;
		}
		ListNode fast = head;
		ListNode slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (slow == fast) {
				fast = head;
				while (slow != fast) {
					fast = fast.next;
					slow = slow.next;
				}
				return slow;
			}
		}
		return null;
	}
}