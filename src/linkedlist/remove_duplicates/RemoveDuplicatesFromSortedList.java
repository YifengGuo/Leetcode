package linkedlist.remove_duplicates;
/**
 * 
 * @author guoyifeng
 * 
	Given a sorted linked list, delete all duplicates such that each element appear only once.
	
	Input: 1->1->2
	
	Output: 1->2
 */
public class RemoveDuplicatesFromSortedList {
  public ListNode removeDup(ListNode head) {
  	if (head ==  null || head.next == null) {
    	return head;  
    }
    //      1  1  1  2  3  4
    //     cur
    // prev
    ListNode cur = head;
    ListNode prev = null;
    while (cur != null) {
   		if (prev == null) {
      	prev = cur;
        cur = cur.next;
      } else if (cur.val == prev.val) {
      	cur = cur.next;
        prev.next = cur;
      } else {
      	prev.next = cur;
        prev = cur;
        cur = cur.next;
      }
    }
    return head;
  }
}
