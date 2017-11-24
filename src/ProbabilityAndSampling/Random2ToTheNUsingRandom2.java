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
 *   	if random falss into [0, 1,000,000 - 1], then return random it
 *      else re-generate random2() 20 times until the value falls into [0, 1,000,000 - 1] 
 */
public class Random2ToTheNUsingRandom2 {
	public static int random10_to_the_6() { // generate a random number from 0 to 1,000,000
		while (true) {
			int num = 0;
			for (int i = 0; i < 20; i++) {
				num = 2 * num + random2();
			}
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
