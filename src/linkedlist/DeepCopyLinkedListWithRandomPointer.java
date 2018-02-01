package linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author yifengguo
 * Each of the nodes in the linked list has another pointer pointing to a random node in the list or null. 
 * Make a deep copy of the original list.
 */
class RandomListNode {
	public int value;
	public RandomListNode next;
	public RandomListNode random;

	public RandomListNode(int value) {
		this.value = value;
	}
}
/*
 * use a hash_map to record if next or random node has been copied or not
 * time = O(n)
 * space = O(n)
 */
public class DeepCopyLinkedListWithRandomPointer {
	public RandomListNode copy(RandomListNode head) {
		if (head == null) {
			return head;
		}
		Map<RandomListNode, RandomListNode> map = new HashMap<>();
		RandomListNode copyHead = new RandomListNode(head.value);
		map.put(head, copyHead);
		RandomListNode cur = copyHead; // for copyHead shall be returned, use a cur pointer to record current copyHead position
		while (head != null) {
			if (head.next != null) { // remember sanity check
				if (!map.containsKey(head.next)) {
					map.put(head.next, new RandomListNode(head.next.value));
				}
			}
			cur.next = map.get(head.next);

			if (head.random != null) {
				if (!map.containsKey(head.random)) {
					map.put(head.random, new RandomListNode(head.random.value));
				}
			}
			cur.random = map.get(head.random);

			head = head.next; // move both pointers
			cur = cur.next;

		}
		return copyHead;
	}
}
