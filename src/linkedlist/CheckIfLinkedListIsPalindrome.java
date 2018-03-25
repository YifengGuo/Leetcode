package linkedlist;
/**
 * 
 * @author guoyifeng
 * Given a linked list, check whether it is a palindrome.

	Examples:
	
	Input:   1 -> 2 -> 3 -> 2 -> 1 -> null
	
	output: true.
	
	Input:   1 -> 2 -> 3 -> null  
	
	output: false.
	
	Requirements:
	
	Space complexity must be O(1)
 */
/*
 * basic idea: to achieve O(1) space complexity:
 *       1. Get the middle node of the list
 *       2. reverse second half
 *       3. 
 *          3.1 if second half is null, which means the length of original list is <= 2
 *              so compare the head and head.next
 *          3.2 if second half is not null, then compare each element from first and second
 *              half until second half meets its end because if the length of list is odd,
 *              then the first half will have one more single node
 */
public class CheckIfLinkedListIsPalindrome {
	public boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}
		ListNode fast = head;
		ListNode slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		ListNode second = slow.next;
		ListNode reverseHead = reverse(second);
		if (second == null) {
			if (head.next.val != head.val) {
				return false;
			}
		}
		while (reverseHead != null) {
			if (reverseHead.val != head.val) {
				return false;
			}
			reverseHead = reverseHead.next;
			head = head.next;
		}
		return true;
	}

	private ListNode reverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode cur = head;
		ListNode prev = null;
		ListNode next = null;
		while (cur != null) {
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		return prev;
	}
}