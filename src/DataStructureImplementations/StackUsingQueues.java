package DataStructureImplementations;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author yifengguo Implement the following operations of a stack using queues.
 * 
 *         push(x) -- Push element x onto stack. pop() -- Removes the element on
 *         top of the stack. top() -- Get the top element. empty() -- Return
 *         whether the stack is empty. Notes: You must use only standard
 *         operations of a queue -- which means only push to back, peek/pop from
 *         front, size, and is empty operations are valid. Depending on your
 *         language, queue may not be supported natively. You may simulate a
 *         queue by using a list or deque (double-ended queue), as long as you
 *         use only standard operations of a queue. You may assume that all
 *         operations are valid (for example, no pop or top operations will be
 *         called on an empty stack).
 */
/*
 * basic idea: use two queues to implement stack
 * 			   in only stores one top element
 * 			   when pop() is called, in.poll() and
 * 			   move all the elements from out to in
 *             and then move (n - 1) elements back to
 *             out
 *  Time = O(n)
 *  Space = O(n)
 */
public class StackUsingQueues {
	private Queue<Integer> in;
	private Queue<Integer> out;
	// constructor
	public StackUsingQueues() {
		in = new LinkedList<>();
		out = new LinkedList<>();
	}

	public void push(int x) {
		if (in.isEmpty()) {
			in.offer(x);
		} else {
			out.offer(in.poll());
			in.offer(x);
		}
	}

	public int top() {
		return in.peek();
	}

	public int pop() {
		int res = in.poll();
		while (!out.isEmpty()) {
			in.offer(out.poll());
		}
		while (in.size() > 1) {
			out.offer(in.poll());
		}
		return res;
	}

	public boolean isEmpty() {
		return in.size() == 0;
	}
}
