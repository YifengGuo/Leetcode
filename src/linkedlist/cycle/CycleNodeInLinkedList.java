package linkedlist.cycle;
/**
 * 
 * @author yifengguo
  Check if a given linked list has a cycle. Return the node where the cycle starts. Return null if there is no cycle.
 */
public class CycleNodeInLinkedList {
	public ListNode cycleNode(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode slow = head;
		ListNode fast = head;
		// use fast and slow pointer to check if list has cycle or not
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) { // found the node where two pointers meet
				slow = head;
				break;
			}
		}
		if (fast == null || fast.next == null || fast.next.next == null) {
			return null;
		}
		// if fast and its next or next.next is not null, means the list has cycle
		// then at that moment slow is at head, then move two pointers one step each time
		// the second where time they meet is the start of the cycle
		while (fast != slow) {
			slow = slow.next;
			fast = fast.next;
		}
		return fast;
	}
}
