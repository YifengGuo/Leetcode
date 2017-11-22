package ProbabilityAndSampling;
/**
 * 
 * @author guoyifeng
 * Consider an unlimited flow of data elements. How do you sample one element from this flow, 
 * such that at any point during the processing of the flow, you can return a random element from the n elements read so far.

	You will implement two methods for a sampling class:
	
	read(int value) - read one number from the flow
	sample() - return at any time the sample, if n values have been read, the probability of returning any one of the n values is 1/n, 
	return null(Java)/INT_MIN(C++) if there is no value read so far
	You may need to add more fields for the class.
 */
/*
 * basic idea: 
 *            index = 0 1 2 3 4 5 6 7
 *              A[] = a b c d e f g h
 *              maintain a counter for the number of element we have read
 *              maintain (Integer)sample to record the sample of data we have so far
 *              
 *              step 1: counter = 1, call random(1), if the random number == 0, then the result_so_far = a
 *              for the first iteration, we have only one element, so result_so_far must be a
 *              
 *              step 2: counter = 2, call random(2), if the random number == 0, then result_so_far = b
 *              p(result_so_far == a) = 1/2
 *              p(result_so_far == b) = 1/2
 *              
 *              step 3: counter = 3, call random(3), if the random number == 0, then result_so_far = c
 *              p(result_so_far == c) = 1/3
 *              p(result_so_far == a) = 1/2 * (1-1/3) = 1/3
 *              p(result_so_far == b) = 1/2 * (1-1/3) = 1/3
 *              
 *              ...
 *              step n: count = n, call random(n), if the random number == 0, then the result_so_far = current
 *              read element
 *              
 *              in details: because for a non-empty sequence, index 0 must exist and the other indices are not guaranteed.
 *                          So we use random(x) == 0 to guarantee the probability is uniformly distributed among the data
 *                          we have had so far. Based on this, we can a 1/n generator
 *                          And sample should be chosen with probability == 1/n, so we can assign current read data as the 
 *                          sample of data we have so far if the random() return 0. In this way we can guarantee the probability
 *                          of choosing sample is 1/n
 */
public class ReservoirSampling {
	private int counter; // number of data we have so far
	private Integer sample;
	
	public ReservoirSampling() {
		this.counter = 0;
		this.sample = null;
	}
	
	public void read(int value) {
		counter++;
		int randomIdx = (int)(Math.random() * counter); // each element's probability of being chosen is 1 / counter
		if (randomIdx == 0) { // 0 is guaranteed to be existed
			sample = value;
		}
	}
	
	public Integer sample() {
		return this.sample;
	}
}

