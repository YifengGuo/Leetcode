package linkedlist;
/**
 * 
 * @author guoyifeng
 * Given a linked list and a target value T, partition it such that all nodes less than 
 * T are listed before the nodes larger than or equal to target value T. The original relative 
 * order of the nodes in each of the two partitions should be preserved.

Examples

L = 2 -> 4 -> 3 -> 5 -> 1 -> null, T = 3, is partitioned to 2 -> 1 -> 4 -> 3 -> 5 -> null
 *
 */
/*
 * basic idea:
 *     step 1: partition original list into two linkedlist, one is nodes which smaller than target, the other is larger than target
 *     step 2: concatenate small and large sublist
 *     step 3: remove largeCur.next because it may link to small other node in original list
 */
public class PartitionLinkedList {
  public ListNode partition(ListNode head, int target) {
    // write your solution here
    if (head == null) {
    	return head;  
    }
    ListNode smallHead = new ListNode(0);
    ListNode smallCur = smallHead;
    ListNode largeHead = new ListNode(0);
    ListNode largeCur = largeHead;
    ListNode cur = head;
    //    2   4   3   5   1    t = 3
    //                      cur
    // sh -> 2 -> 1
    //            sc
    // lh -> 4 -> 3 -> 5
    //                 lc   
    while (cur != null) {
    	if (cur.val < target) {
      	smallCur.next = cur;
        cur = cur.next;
        smallCur = smallCur.next;
      } else {
      	largeCur.next = cur;
        cur = cur.next;
        largeCur = largeCur.next;
      }
    }
    smallCur.next = largeHead.next;
    largeCur.next = null; // largeCur is 5, it is linked to 1 in the original list, remove this reference
    return smallHead.next;
  }
}
