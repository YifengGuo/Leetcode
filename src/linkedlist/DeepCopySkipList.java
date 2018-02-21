package linkedlist;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author guoyifeng
 *  A Skip List is a special type of linked list,
 *  where each of the nodes has a forward pointer to another node 
 *  in the front and forward pointers are guaranteed to be in non-descending order.

	Make a deep copy of the original skip list.
 */
class SkipListNode {
	int value;
	SkipListNode next;
	SkipListNode forward;

	public SkipListNode(int value) {
		this.value = value;
		next = null;
		forward = null;
	}
}
/*
 * same idea as deep copy
 * remember sanity check when mapping original nodes with copy nodes
 */
public class DeepCopySkipList {
	public SkipListNode copy(SkipListNode head) {
		if (head == null) {
			return null;
		}
		Map<SkipListNode, SkipListNode> map = new HashMap<>();
		SkipListNode copyHead = new SkipListNode(head.value);
		map.put(head, copyHead);
		SkipListNode cur = copyHead;
		while (head != null) {
			// check copy of next pointer
			if (head.next != null) {  // remember sanity check
				if (map.get(head.next) == null) {
					map.put(head.next, new SkipListNode(head.next.value));
				}
			}
			cur.next = map.get(head.next);
			
			if (head.forward != null) {   // remember sanity check
				if (map.get(head.forward) == null) {
					map.put(head.forward, new SkipListNode(head.forward.value));
				}
			}
			cur.forward = map.get(head.forward); // if null, link null
			
			head = head.next;
			cur = cur.next;
		}
		return copyHead;
	}
}
