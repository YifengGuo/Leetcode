package array.Deduplication;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author guoyifeng 
 * 		   Given an unsorted integer array, remove adjacent duplicate
 *         elements repeatedly, from left to right. For each group of elements
 *         with the same value do not keep any of them.
 * 
 *         Do this in-place, using the left side of the original array. Return
 *         the array after deduplication.
 * 
 *         Assumptions
 * 
 *         The given array is not null Examples
 * 
 *         {1, 2, 3, 3, 3, 2, 2} → {1, 2, 2, 2} → {1}, return {1}
 */
// -1 0 1 2 3 4 5 6 7
//    1 2 3 3 3 2 2 3
//      e
//                   i
//    1 3
/*
 * basic idea: implicitly treat part of original array as stack
 * 			   initialize a pointer as top and use it to handle
 *             when to push and when to pop
 * time = O(n)
 * space = O(1)
 */
public class ArrayDeduplication4 {
	public int[] dedup(int[] array) {
		if (array == null || array.length == 0) {
		return array;
		}
		int end = -1;
		// implicitly treat left part to the end of original array as
		// a stack. if new discovered element of array != array[end] then
		// we push it into stack. Otherwise, we meet a consecutive sequence
		// and we should skip this sequence of duplications and then pop
		// out the top element of stack for it is the head of skipped 
		// duplication sequence
		for (int i = 0; i < array.length; i++) {
			if (end == -1 || array[i] != array[end]) { // stack.top() != array[i]
				array[++end] = array[i]; // stack.push(array[i])
			} else {
				while (i + 1 < array.length && array[i + 1] == array[i]) {
					i++;
				}
				end--; // stack.pop()
			}
		}
		return Arrays.copyOfRange(array, 0, end + 1);
	}
	/*
	 * cannot solve it!
	 */
//	public int[] dedup(int[] array) {
//		if (array == null || array.length == 0) {
//			return array;
//		}
//		Deque<Integer> stack = new LinkedList<>();
//		int slow = 0;
//		int fast = 0;
//		while (fast < array.length) {
//			int begin = fast;
//			if (stack.size() != 0 && stack.peekLast() == array[fast]) {
//				stack.pop();
//				System.out.println("stack pops");
//				slow--;
//			}
//			while (fast < array.length && array[fast] == array[begin]) {
//				fast++;
//			}
//			if (fast - begin == 1) {
//				if (begin + 1 < array.length && array[begin] != array[begin + 1]) {
//					int top = 0;
//					if (!stack.isEmpty()) {
//						top = stack.peekLast();
//						System.out.println("top " + top);
//					}
//					stack.offer(array[begin]);
//					if (stack.peekLast() == top) {
//						stack.pop();
//					}
//					System.out.println("stack offers" + array[begin]);
//				}
//				array[slow++] = array[begin];
//			}
//		}
//		return Arrays.copyOfRange(array, 0, slow);
//
//	}

	public static void main(String[] args) {
		int[] array = new int[] { 1, 1, 2, 3, 4, 5, 5, 4, 4, 4, 3, 2, 5 };
		int[] res = new ArrayDeduplication4().dedup(array);
		for (int i : res) {
			System.out.print(i + " ");
		}
	}
}
