package TopInterviewQuestions;
/**
 * 
 * @author guoyifeng
	Implement int sqrt(int x).
	
	Compute and return the square root of x.
 */
/*
 * basic idea:
 * 		use binary search to find the sqrt of x
 *      pay attention to overflow case
 *      
 *      time = O(logx)
 */
public class ImplementSqrt {
	public int mySqrt(int x) {
		if (x == 0) {
			return 0;
		}
		int left = 0;
		int right = Integer.MAX_VALUE;
		while (true) {
			int mid = left + (right - left) / 2;
			if (mid > x / mid) {  // cannot write like mid * mid > x for mid * mid may greater than MAX_VALUE
				right = mid - 1;
			} else { // mid <= x / mid 
				// if mid <= x / mid && (mid + 1) > x / (mid + 1), then mid is sprt of x
				if ((mid + 1) > x / (mid + 1)) { // also cannot write like (mid + 1) * (mid + 1) > x
					return mid;
				}
				left = mid + 1;
			}
		}
	}
}
