package array.slidingwindow;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
/**
 * 
 * @author guoyifeng
 * Given an array nums, there is a sliding window of size k which is moving from the very left 
 * of the array to the very right. You can only see the k numbers in the window. 
 * Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

	Window position                Max
	---------------               -----
	[1  3  -1] -3  5  3  6  7       3
	 1 [3  -1  -3] 5  3  6  7       3
	 1  3 [-1  -3  5] 3  6  7       5
	 1  3  -1 [-3  5  3] 6  7       5
	 1  3  -1  -3 [5  3  6] 7       6
	 1  3  -1  -3  5 [3  6  7]      7
	Therefore, return the max sliding window as [3,3,5,5,6,7].
	
	Note: 
	You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 */
/*
 * time = amortized O(n)
 * space = O(k)
 * 
 * essential idea: when in a k size window, if j < i && array[j] < array[i], then array[j] has no chance to become the max
 *                 because when it is in the same window with array[i], array[i] is a better candidate, and then array[j]
 *                 will be polled out from deque earlier than array[i], so we do not need to consider these cases 
 * 
 * demo:   [] represents window and () represents deque
 *  [1  3  -1] -3  5  3  6  7       3       [x 3 -1] -3 5 3 6 7  3 and 1 are in same window 3 > 1 and index of 3 > index of 1, so 1 has no chance to become max
	 1 [3  -1  -3] 5  3  6  7       3       x [(3 -1 -3)] 5 3 6 7  for -1 and -3 are both < 3
	 1  3 [-1  -3  5] 3  6  7       5       x x [x x (5)] 3 6 7    for 5 > -1 and -3 and -1 and -3 has no chance to be max, discard them from deque
	 1  3  -1 [-3  5  3] 6  7       5       x x x [x (5 3)] 6 7
	 1  3  -1  -3 [5  3  6] 7       6       x x x x [x x (6)] 7    window now contains [5, 3, 6] and 6 > 3 and 5, so discard 5 and 3 from deque
	 1  3  -1  -3  5 [3  6  7]      7       x x x x x [x x (7)]    above all we can see that max value is always maintained as the head of deque
 */
public class SlidingWindowMaximum {
	/*
	 * better solution using deque
	 */
	public int[] maxSlidingWindow(int[] array, int k) {
		if (array == null || array.length == 0 || k <= 0) {
			return new int[]{};
		}
		int[] res = new int[array.length - k + 1];
		// to store the index
		Deque<Integer> deque = new ArrayDeque<>();
		int index = 0; // index of res
		
		// traverse on the array
		// i is the current index about to be offered into deque
		for (int i = 0; i < array.length; i++) {
			// discard the head elements which do not belong to current window [i - k + 1, i]
			while (!deque.isEmpty() && deque.peek() < i - k + 1) {
				deque.poll();
			}
			// Now only those elements within [i- k + 1),i] are in the deque. 
			// We then discard elements smaller than a[i] from the tail. 
			// This is because if a[x] < a[i] and x < i, then a[x] has no chance to be the “max” in [i-(k-1),i], 
			// or any other subsequent window: a[i] would always be a better candidate.
			while (!deque.isEmpty() && array[deque.peekLast()] < array[i]) {
				deque.pollLast();
			}
			
			// add current element's index into deque's tail
			deque.offer(i);
			if (i - k + 1 >= 0) { // if current i is in a valid window
				res[index++] = array[deque.peek()]; // max is always at the head of deque
			}
		}
		return res;	
	}
	/*
	 * my method:
	 *      use a heap to record the order of elements in sliding window
	 *      when window is sliding, update heap correspondingly 
	 *      time = O(nk)
	 *      space = O(n)
	 */
	public int[] maxSlidingWindow2(int[] array, int k) {
		int[] res = new int[array.length - k + 1];
		if (array == null || array.length == 0 || k <= 0) {
			return new int[] {};
		}
		Deque<Integer> deque = new LinkedList<>();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer i1, Integer i2) {
				if (i1 == i2)
					return 0;
				return i1 < i2 ? 1 : -1;
			}
		});
		int index = 0;
		// construct the deque and heap with first sliding window
		for (int i = 0; i < k; i++) {
			maxHeap.offer(array[i]);
			deque.offerLast(array[i]);
		}
		res[index++] = maxHeap.peek();
		// start to traverse the remaining elements of array
		for (int i = k; i < array.length; i++) {
			if (array[i - k] != maxHeap.peek()) { // if element about to poll is not the max one
				                                  // remove it from the head of deque and remove it from heap
				int remove_e = deque.pollFirst();
				deque.offerLast(array[i]);
				maxHeap.remove(remove_e);
				maxHeap.offer(array[i]);
				res[index++] = maxHeap.peek();   // add max into res which did not change
			} else {     // if element about to poll is the max value
				maxHeap.poll(); // first poll out from heap
				maxHeap.offer(array[i]); // then new peek of heap is the max value
				res[index++] = maxHeap.peek();
				deque.pollFirst();  // update deque
				deque.offerLast(array[i]);
			}
		}
		return res;
	}
}