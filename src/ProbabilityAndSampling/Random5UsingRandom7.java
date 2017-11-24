package ProbabilityAndSampling;
/**
 * 
 * @author yifengguo
 * implement a random number generator which can return 0 - 4 with
 * equal probability using Random7(which return 0 - 6 with equal probability)
 */
public class Random5UsingRandom7 {
	public int random5() {
		while (true) {
			int random = (int)(Math.random() * 7);
			if (random < 5) {
				return random;
			}
		}
	}
	
	public static void main(String[] args) {
		Random5UsingRandom7 test = new Random5UsingRandom7();
		for (int i = 0; i < 20; i++) {
			System.out.print(test.random5() + " ");
		}
	}
}
