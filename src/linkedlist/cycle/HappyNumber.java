package linkedlist.cycle;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author guoyifeng
 * Write an algorithm to determine if a number is "happy".

	A happy number is a number defined by the following process: 
	Starting with any positive integer, replace the number by the sum of the squares of 
	its digits, and repeat the process until the number equals 1 (where it will stay), 
	or it loops endlessly in a cycle which does not include 1. Those numbers for which this 
	process ends in 1 are happy numbers.
	
	Example: 
	
	Input: 19
	Output: true
	Explanation: 
	12 + 92 = 82
	82 + 22 = 68
	62 + 82 = 100
	12 + 02 + 02 = 1
 */
/*
 * basic idea: convert the problem into cycle in the linked list
 * when fast and slow meets again, if they are both 1, it means the number is happy number
 * and can be converted to 1
 * if not, it means the loop is endless without 1 so the number is not a happy number
 */
public class HappyNumber {
    public boolean isHappy(int n) {
        int fast = n;
        int slow = n;
        while (true) {
            fast = helper(helper(fast));
            slow = helper(slow);
            if (slow == fast) {
                if (slow == 1) return true;
                else return false;
            }
        }
    }
    
    // the helper function is to divide n into several digits 
    // each digit reprents integer on that digit
    // calculate and return the sum of squares on each digit
    private int helper(int n) {
        List<Integer> list = new ArrayList<>();
        while (n > 0) {
            list.add(n % 10);
            n /= 10;
        }
        int sum = 0;
        for (Integer i : list) {
            sum += i * i;
        }
        return sum;
    }
}