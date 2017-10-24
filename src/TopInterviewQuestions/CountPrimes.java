package TopInterviewQuestions;
/**
 * 
 * @author guoyifeng
 	Count the number of prime numbers less than a non-negative number, n.
 */
/*
 * primes are starting from 2 (0 and 1 are not primes)
 * basic idea: 
 * 				use a boolean array to record current number is prime or not
 * 				if i is prime, count++, and all the products produced by i and j where (i * j < n) are all not primes
 */
public class CountPrimes {
	public int countPrimes(int n) {
		if (n <= 2) { // 0 and 1 are not primes
			return 0;
		}
		boolean[] notPrime = new boolean[n]; // false by default
		int count = 0;
		for (int i = 2; i < n; i++) {
			if (notPrime[i] == false) { // if current i is prime, count++, 2 is prime initially
				count++;
				for (int j = 2; i * j < n; j++) { // update all products by i and j (i * j < n) that they are primes
					notPrime[i * j] = true;
				}
			}
		}
		return count;
	}
}

