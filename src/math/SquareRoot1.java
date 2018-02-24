package math;
/**
 * 
 * @author yifengguo
  Given an integer number n, find its integer square root.
	
	Assumption:
	
	n is guaranteed to be >= 0.
	Example:
	
	Input: 18, Return: 4
	
	Input: 4, Return: 2
 */
/*
 * basic idea: binary reduction
 * do not code like mid * mid anywhere for x could be quite large
 */
public class SquareRoot1 {
	public int sqrt(int x) {
		if (x <= 0) {
			return 0;
		}
		int start = 1;
		int end = x;
		int res = 0;
		while (start <= end) {
			int mid = (end + start) / 2;
			if (mid == x / mid) {  // do not write mid * mid anywhere buz it will be overflow if x is too large!!!!!!!
				return mid;
			}
			if (mid < x / mid) {
				start = mid + 1;
				res = mid;
			} else {
				end = mid - 1;
			}
		}
		return res;
	}
}
