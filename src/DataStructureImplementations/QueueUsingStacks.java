package DataStructureImplementations;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author guoyifeng Implement the following operations of a queue using stacks.
 * 
 *         push(x) -- Push element x to the back of queue. pop() -- Removes the
 *         element from in front of queue. peek() -- Get the front element.
 *         empty() -- Return whether the queue is empty. Notes: You must use
 *         only standard operations of a stack -- which means only push to top,
 *         peek/pop from top, size, and is empty operations are valid. Depending
 *         on your language, stack may not be supported natively. You may
 *         simulate a stack by using a list or deque (double-ended queue), as
 *         long as you use only standard operations of a stack. You may assume
 *         that all operations are valid (for example, no pop or peek operations
 *         will be called on an empty queue).
 */
/*
 * basic idea: two stacks
 * 			1 2 3||   || 4 5 6
 *            out           in
 *      when poll() and peek, check if out is empty or not
 *      if empty, move all the elements in instack to outstack
 *      move() can guarantee FIFO property
 */
public class QueueUsingStacks {
	private Deque<Integer> in;
	private Deque<Integer> out;

	// constructor
	public QueueUsingStacks() {
		in = new LinkedList<>();
		out = new LinkedList<>();
	}

	public void offer(int x) {
		in.push(x);
	}

	public int peek() {
		if (out.isEmpty()) {
			move();
		}
		return out.peek();
	}

	public int poll() {
		if (out.isEmpty()) {
			move();
		}
		return out.pop();
	}

	public boolean isEmpty() {
		return in.size() + out.size() == 0;
	}

	private void move() {
		if (out.isEmpty()) {
			while (!in.isEmpty()) {
				out.push(in.pop());
			}
		}
	}

}
