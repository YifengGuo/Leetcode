package linkedlist;
/**
 * 
 * @author yifengguo
 * Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?
 */
class ListNode {
	ListNode next;
	int val;
	ListNode(int x) {
		val = x;
		next = null;
	}
}
public class LinkedListCycle1 {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
