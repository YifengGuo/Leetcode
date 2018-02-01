package linkedlist;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
/**
 * 
 * @author yifengguo
 * Merge K sorted lists into one big sorted list in ascending order.

	Assumptions
	
	ListOfLists is not null, and none of the lists is null.
 */
/*
 * basic idea: minHeap initialized with each list's head pointer
 */
public class MergeKSortedLists {
	public ListNode merge(List<ListNode> listOfLists) {
		if (listOfLists == null) {
			return null;
		}
		PriorityQueue<ListNode> minHeap = new PriorityQueue<>(new Comparator<ListNode>() {
			@Override
			public int compare(ListNode n1, ListNode n2) {
				if (n1.val == n2.val) {
					return 0;
				}
				return n1.val < n2.val ? -1 : 1;
			}
		});
		for (ListNode tmp : listOfLists) {
			if (tmp != null) {
				minHeap.offer(tmp);
			}
		}
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		while (!minHeap.isEmpty()) {
			ListNode n = minHeap.poll();
			cur.next = n; // first element polled from minHeap is the head of result list
			if (n.next != null) { // sanity check
				n = n.next;
				minHeap.offer(n);
			}
			cur = cur.next; // move cur
		}
		return dummy.next;
	}
}
