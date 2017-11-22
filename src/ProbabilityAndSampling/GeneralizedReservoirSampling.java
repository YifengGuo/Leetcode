package ProbabilityAndSampling;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author guoyifeng
 * Consider an unlimited flow of data elements. How do you sample k element from this flow, 
 * such that at any point during the processing of the flow, you can return a random set of k 
 * elements from the n elements read so far. 

	Assumptions
	
	k >= 1
	You will implement two methods for a sampling class:
	
	read(int value) - read one number from the flow
	sample() - return at any time the k samples as a list, return the list of all values read when 
	the number of values read so fas <= k.
	
	You may need to add more fields for the class.
 */
/*
 * basic idea: similar to reservoir sampling
 *             and now we need sample k elements of a data flow
 *             step 1: first k elements, we can directly add them into samples
 *             step 2: the recently read data, it shall have uniform probability of k / n 
 *                     to selected as sample (1 / n for single sample, and we need k samples
 *                     so k * 1 / n = k / n)
 * space = O(k)
 */
public class GeneralizedReservoirSampling {
	private int counter; // represent total number of data we have so far
	private List<Integer> samples;
	private final int k;
	
	public GeneralizedReservoirSampling(int k) {
		if (k <= 0) {
			throw new IllegalArgumentException("k must be greater than 0");
		}
		this.k = k;
		this.counter = 0;
		this.samples = new ArrayList<>();
	}
	
	public void read(int value) {
		counter++;
		if (counter <= k) { //for the first k elements, add them into list directly
			samples.add(value);
		} else {
			int randomIdx = (int) (Math.random() * (counter)); // 1 / n generator
			if (randomIdx < k) { // k / n for we need k samples of n elements we have so far
			  samples.set(randomIdx, value);
		  }
		}
	}
	
	public List<Integer> sample() {
		List<Integer> res = new ArrayList<Integer>();
		if (counter == 0) {
			return res;
		}
		return this.samples;
	}
	
	public static void main(String[] args) {
		GeneralizedReservoirSampling test = new GeneralizedReservoirSampling(7);
		int[] arr = new int[50];
		for (int i = 1; i <= arr.length; i++) {
			arr[i - 1] = i;
		}
		for (int i = 0; i < arr.length; i++) {
			test.read(arr[i]);
		}
		for (Integer i : test.sample()) {
			System.out.print(i + " ");
		}
	}
}
