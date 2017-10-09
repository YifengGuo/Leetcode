package TopInterviewQuestions;

/*
 *  basic idea: any to the 0 == 1
 *              n is negative or positive? different cases
 *              on each recursion level, check if x is overflow
 *              if overflow, stop recursion and return 0
 *              
 *              time = O(logn)
 *              space = O(logn)
 */ 
public class MyPow {
	public double myPow(double x, int n) {
		if (n == 0) {
			return 1;
		}
		if (n < 0) {
			n = n * (-1);
			x = 1 / x;
		}
		// check if x is overflow
		// if so, stop recursion and return 0
        if (x >= Double.MAX_VALUE) {
            return 0;
        }
		// recursive call
		return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
	}
}
