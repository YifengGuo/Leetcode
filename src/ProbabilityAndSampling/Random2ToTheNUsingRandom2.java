package ProbabilityAndSampling;
/**
 * 
 * @author yifengguo
 * How to design a random number to generate Random(1,000,000), with random2?
 */
/*
 * basic idea: need to find 2 ^ k - 1 >= 1,000,000
 *             for generator implemented by random2()
 *             is ranged in [0,x)
 *             
 *   2 ^ 10 = 1024 = 1k
 *   2 ^ 20 = 1M
 *   2 ^ 30 = 1G
 *   2 ^ 40 = 1T
 *   2 ^ 50 = 1P
 *   
 *   so we need to call random2() for 20 times to from a binary representation of 
 *   the number [0, 2 ^ 20 - 1] which is greater than 1,000,000
 *   
 *   	if random falls into [0, 1,000,000 - 1], then return random it
 *      else re-generate random2() 20 times until the value falls into [0, 1,000,000 - 1] 
 */
public class Random2ToTheNUsingRandom2 {
	public static int random10_to_the_6() { // generate a random number from 0 to 1,000,000
		while (true) {
			// the following 4 lines of code is usually used to compute:
			// a0*x^0 + a1*x^1 + a2*x^2 + a3*x^3 + ... + ak*x^k
			// e.g:  a*2^3 + b*2^2 + c*2^1 + d*2^0
			//     = 2 * (a*2^2 + b* 2^1 + c*2^0) + d*2^0
			// so this is an iterative algorithm to compute
			// a0*x^0 + a1*x^1 + a2*x^2 + a3*x^3 + ... + ak*x^k
			// which can efficiently grow up to be greater than bound we want(e.g 1,000,000)
			// for x^0 + x^1 + x^2 + ...+ x^(n-1) = x^n - 1
			int num = 0;
			for (int i = 0; i < 20; i++) {
				num = 2 * num + random2();
			}
			// if random number num falls in range [0, 1000000 - 1], then return 
			if (num < 1000000) {
				return num;
			}
		}
	}
	
	private static int random2() {
		while(true) {
			int random = (int)(Math.random() * 2);
			if (random < 2) {
				return random;
			}
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(random10_to_the_6() + " ");
			int a = random10_to_the_6();
			if (a < 100) {
				System.out.println(a);
			}
		}
	}
}
