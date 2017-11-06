package linkedlist;
/**
 * 
 * @author yifengguo
 	Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that 
 	at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

	Note:
	You must not modify the array (assume the array is read only).
	You must use only constant, O(1) extra space.
	Your runtime complexity should be less than O(n2).
	There is only one duplicate number in the array, but it could be repeated more than once.
 */
/*
 * basic idea: LinkedList cycle detection problem
 */
public class FindDuplicateNumber_DetectCycleLinkedList {
	public int findDuplicate(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		// for value of element in the array is from 1 and n and array.length == n + 1
		// so nums[slow], nums[fast] or nums[nums[fast]] are always valid
		// we do not need to consider index out of bounds exceptions
		int fast = nums[nums[0]]; // to avoid infinite loop, we need initialize fast a step forward than slow
		int slow = nums[0];
		// detect cycle
		while (fast != slow) {
			fast = nums[nums[fast]];
			slow = nums[slow];
		}
		// move fast back to index == 0 (where slow begins)
		// because initially slow is nums[0]
		fast = 0;
		// move each pointer one step forward each time until they meet
		// the entry they meet is the duplicate number
		while (fast != slow) {
			fast = nums[fast];
			slow = nums[slow];
		}
		return slow;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {1, 2, 4, 6, 5, 7, 3, 5, 8};
		int duplicateNum = new FindDuplicateNumber_DetectCycleLinkedList().findDuplicate(nums);
		System.out.println(duplicateNum);
	}
}
