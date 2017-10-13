package TopInterviewQuestions;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * @author yifengguo 
 *         Find the kth largest element in an unsorted array. Note
 *         that it is the kth largest element in the sorted order, not the kth
 *         distinct element.
 * 
 *         For example, Given [3,2,1,5,6,4] and k = 2, return 5.
 * 
 *         Note: You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
/*
 * time = O(nlogk)
 * space = O(k)
 */
public class KthLargestElementInArray {
	public int findKthLargest(int[] nums, int k) {
		int res = 0;
		if (nums == null || nums.length == 0) {
			return 0;
		}
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return b.compareTo(a);
			}
		});
		for (int i : nums) {
			maxHeap.offer(i);
		}
		for (int i = 0; i < k; i++) {
			res = maxHeap.poll();
		}
		return res;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 1 };
		int k = 1;
		int res = new KthLargestElementInArray().findKthLargest(arr, k);
		System.out.println(res);
	}
}
