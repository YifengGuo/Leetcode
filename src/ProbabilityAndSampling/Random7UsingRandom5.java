package ProbabilityAndSampling;
/**
 * 
 * @author yifengguo
 * Given a random generator random5(), 
 * the return value of random5() is 0 - 4 with equal probability. Use random5() to implement random7().
 */
/*
 * basic idea: it is a little bit harder than using7 to generate random5
 *             main goal is to guarantee each return case has uniform
 *             1 / 7 probability
 *             
 *             0  1  2  3  4
 *             5  6  7  8  9
 *             10 11 12 13 14
 *             15 16 17 18 19
 *             20 21 22 23 24
 *             
 *             0, 7, 14 % 7 == 0
 *             1, 8, 15 % 7 == 1
 *             2, 9, 16 % 7 == 2
 *             3, 10, 17 % 7 == 3
 *             4, 11, 18 % 7 == 4
 *             5, 12, 19 % 7 == 5
 *             6, 13, 20 % 7 == 6
 *             
 *    so using random5() to generate [0, 25) range, and we fetch first 21 numbers,
 *    there are 7 pairs and which pair contains 3 numbers. SO each pair has probability
 *    of 1 / 7
 */
public class Random7UsingRandom5 {
	public static int random5() {
		while (true) {
			int random = (int)(Math.random() * 5);
			if (random < 5) {
				return random;
			}
		}
	}
	
	public static int random7() {
		while (true) { // impossible dead loop for chance is less and less with no
                       // return (4/25) ^ n for n times no return
			int num = 5 * random5() + random5(); // generate [0 - 25)
			if (num < 21) { // fetch first 20 numbers
				return num % 7; // return 0 - 6
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(random5());
		for (int i = 0; i < 20; i++) {
			System.out.print(random7() + " ");
		}
	}
}
