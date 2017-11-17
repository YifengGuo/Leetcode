package bit_manipulation;
/**
 * 
 * @author guoyifeng
 * Determine if a given integer is power of 2.
 */
/*
 * basic idea: if one number is power of 2, its hamming weight must be 1
 * time = O(n)
 */
public class IsPowerOfTwo {
	public static boolean isPowerOfTwo(int num) {
		if (num <= 0) {
			return false;
		}
		int count = 0;
		while (num > 0) {
			count += num & 1;
			num = num >> 1;
		}
		return count == 1;
	}
	
	public static void main(String[] args) {
		int a = Integer.MAX_VALUE;
		System.out.println(isPowerOfTwo(a));
	}
}

