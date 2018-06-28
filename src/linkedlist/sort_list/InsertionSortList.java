package linkedlist.sort_list;
/**
 * 
 * @author guoyifeng
 * Algorithm of Insertion Sort:

	1. Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
	2. At each iteration, insertion sort removes one element from the input data, 
	   finds the location it belongs within the sorted list, and inserts it there.
	3. It repeats until no input elements remain.
	
	Example 1:

	Input: 4->2->1->3
	Output: 1->2->3->4
	Example 2:
	
	Input: -1->5->3->4->0
	Output: -1->0->3->4->5
 */
class ListNode {
	ListNode next;
	int val;
	ListNode(int val) {
		this.val = val;
	}
}
/*
 * solved by fake head
 * 1. each time when cur moves on a new position, move prev from fake head 
 * until prev.next.val > cur.val
 * 2. then insert cur between prev and prev.next
 */
public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = head, next = null;
        
        while (cur != null) {
            next = cur.next;
            // every time cur arrives a new position
            // move prev from dummy until finds the insertion place
            ListNode prev = dummy;
            while (prev.next != null && prev.next.val < cur.val) {
                prev = prev.next;
            }
            // insert cur between prev and prev.next
            cur.next = prev.next;
            prev.next = cur;
            cur = next;
        }
        return dummy.next;
    }
}
