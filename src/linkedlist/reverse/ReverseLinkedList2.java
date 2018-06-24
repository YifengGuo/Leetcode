package linkedlist.reverse;
class ListNode {
	ListNode next;
	int val;
	public ListNode(int val) {
		this.val = val;
	}
}
/**
 * 
 * @author guoyifeng
 * Reverse a linked list from position m to n. Do it in one-pass.

	Note: 1 ≤ m ≤ n ≤ length of list.
	
	Example:
	
	Input: 1->2->3->4->5->NULL, m = 2, n = 4
	Output: 1->4->3->2->5->NULL
 */
/*
 * time = O(n)
 * space = O(1)
 */
public class ReverseLinkedList2 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        int step = 1;
        //               0 ->     1->2->3->4->5->NULL
        //               dummy  
        //                        p     c  n
        // sublistHead   0 -> 4       2  < - 3   <- 4
        // sublistTail   2       
        ListNode dummyHead = new ListNode(0);
        ListNode cur = head;
        dummyHead.next = head;
        ListNode prev = dummyHead;
        
        // always use sublistHead.next or sublistTail.next to represent real position of it
        ListNode sublistHead = new ListNode(0);
        ListNode sublistTail = new ListNode(0);
        
        while (step <= n) { // only need to update list within 0 - n and link the rest
            ListNode next = cur.next;
            if (step < m) {  // move prev normally when not arriving sublist
                prev = cur;
            } else if (step == m) {  // mth node will be the tail of reversed sublist
                sublistTail = cur;
                sublistHead.next = cur; // current sublistHead is cur
                
            } else if (step > m) {
                cur.next = sublistHead.next;  // reverse list by adding next node of cur to the front of sublistHead
                sublistHead.next = cur;
            }
            cur = next; // move cur and advance step
            step++;
        }
        prev.next = sublistHead.next;  // prev pointing to node before mth node
        sublistTail.next = cur;   // sublistTail is the mth node, cur is the (n + 1)th node
        return dummyHead.next;
    }
}