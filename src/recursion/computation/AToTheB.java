package recursion.computation;
/**
 * 
 * @author Yifeng
 *  Evaluate a to the power of b, assuming both a and b are integers and b is non-negative. 

	Examples
	
	power(2, 0) = 1
	power(2, 3) = 8
	power(0, 10) = 0
	power(-2, 5) = -32
	Corner Cases

 */
/*
 * time = logb
 * space = logb
 */
public class AToTheB {
	public long power(int a, int b) {
		if (b == 0) {
			return 1;
		}
		if (a == 0) {
			return 1;
		}
		long half_res = power(a, b / 2);
		if (b % 2 == 0) {
			return half_res * half_res;
		} else {
			return a * half_res * half_res;
		}
	}
}
