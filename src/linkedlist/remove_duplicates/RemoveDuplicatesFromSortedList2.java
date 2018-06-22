package linkedlist.remove_duplicates;

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}
 
//   0   1->1->1->2->3
//       h
//             c
//   p

//    0  1 2 3 3 4 4 5
//       h
//               c
//         p
public class RemoveDuplicatesFromSortedList2 {
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null)
			return head;
		ListNode dummy = new ListNode(0);
		ListNode cur = head;
		dummy.next = cur;
		ListNode prev = dummy;
		while (cur != null) {
			while (cur.next != null && cur.val == cur.next.val) {
				cur = cur.next;
			}
			if (prev.next == cur) {  // no duplicates between cur and prev, move prev directly
				prev = cur;
			} else {
				prev.next = cur.next; // skip all duplicates cur has traversed
			}
			cur = cur.next; // move cur
		} 
		return dummy.next; // dummy.next is cur
	}
    //   0   1   1   2  2
    //       h  
    //                    c
    //   p
}
