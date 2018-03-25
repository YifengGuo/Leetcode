package linkedlist;

import java.util.HashMap;
import java.util.Map;

public class DeleteNodesAtIndices {
	public ListNode deleteNodes(ListNode head, int[] indices) {
		if (head == null) {
			return head;
		}
		Map<ListNode, Integer> map = new HashMap<>();
		ListNode cur = head;
		int index = 0;
		int i = 0;
		while (cur != null) {
			if (i < indices.length && index == indices[i]) {
				map.put(cur, index);
				i++;
			}
			index++;
			cur = cur.next;
		}
		cur = head;
		ListNode prev = null;
		// [[1,2,3,4,5],[0,3,4]]
		// 1     2     3      4       5
		//            prev
		//                           cur
		//      head
		while (cur != null) {
			if (map.containsKey(cur)) {
				if (prev == null) { // delete at head
					cur = cur.next;
					head = cur;
					continue;
				} else {
					prev.next = cur.next; // delete at middle or tail
				}
			}
			// when try to move pointers
			// move prev only when current node is NOT deleted node
			if (map.containsKey(cur)) {
				cur = cur.next;
			} else {
				prev = cur;
				cur = cur.next;
			}
		}
		return head;
	}
}