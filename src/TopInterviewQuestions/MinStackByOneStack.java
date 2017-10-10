package TopInterviewQuestions;

import java.util.Deque;
import java.util.LinkedList;

public class MinStackByOneStack {
	private Deque<Integer> stack;
	int globalMin;
	public MinStackByOneStack() {
		stack = new LinkedList<>();
		globalMin = Integer.MAX_VALUE;
	}
	
	public void push(int x) {
		// if x <= globalMin(need update globalMin), push old minimum into the stack
		// then update current globalMin with x and push it into stack
		// || oldMin newMin to guarantee top() return right value
		if (x <= globalMin) {
			stack.push(globalMin);
			globalMin = x;
		}
		stack.push(x);
	}
	
	public void pop() {
		int cur = stack.pop();
		// if stack.pop() == globalMin, it means we
		// have pushed old globalMin before pushing newer stack
		// so we need call pop() again to pop out older globalMin
		if (cur == globalMin) {
			globalMin = stack.pop();
		}
		
	}
	
	public int top() {
		return stack.peek();
	}
	
	public int getMin() {
		return globalMin;
	}
}
