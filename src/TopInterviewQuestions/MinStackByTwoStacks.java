package TopInterviewQuestions;

import java.util.Deque;
import java.util.LinkedList;
/**
 * 
 * @author yifengguo
 	Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

	push(x) -- Push element x onto stack.
	pop() -- Removes the element on top of the stack.
	top() -- Get the top element.
	getMin() -- Retrieve the minimum element in the stack.
	Example:
	MinStack minStack = new MinStack();
	minStack.push(-2);
	minStack.push(0);
	minStack.push(-3);
	minStack.getMin();   --> Returns -3.
	minStack.pop();
	minStack.top();      --> Returns 0.
	minStack.getMin();   --> Returns -2.

 */
public class MinStackByTwoStacks {
	private Deque<Integer> stack;
	private Deque<Integer> min;
	private int globalMin;

	public MinStackByTwoStacks() {
		stack = new LinkedList<>();
		min = new LinkedList<>();
		globalMin = Integer.MAX_VALUE;
	}

	public void push(int x) {
		if (stack.isEmpty()) {
			stack.push(x);
			globalMin = x;
			min.push(globalMin);
		} else {
			stack.push(x);
			if (x <= globalMin) {
				min.push(x);
				globalMin = x;
			} else {
				min.push(globalMin);
			}
		}
	}

	public void pop() {
		if (stack.isEmpty()) {
			return;
		}
		stack.pop();
		min.pop();
		// if stack has no element == globalMin
		// we need to update globalMin with current top element of minStack
		if (!min.isEmpty() && globalMin <= min.peek()) {
			globalMin = min.peek();
		}
	}

	public int top() {
		if (stack.isEmpty()) {
			return -1;
		}
		return stack.peek();
	}

	public int getMin() {
		if (stack.isEmpty()) {
			return -1;
		}
		return min.peek();
	}
}
