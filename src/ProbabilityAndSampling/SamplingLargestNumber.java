package ProbabilityAndSampling;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yifengguo
 * return a random largest number's index
 * e.g: given a stream like 1 2 3 4 5a 4 3 2 5b
 * return 5a or 5b's index randomly
 */
/*
 * stream   1 2 5a 3 4 3 4 5b
 * index    0 1 2  3 4 5 6 7
 * return random one of {2,7}
 */
public class SamplingLargestNumber {
	private int currentMax; // record current max value of read stream
	private int currentMaxCount; // record occurrence of current max value
	private int index;
	private List<Integer> largestIndexList; 
	private Integer largestNum_IndexSample;
	
	// default constructor
	public SamplingLargestNumber() {
		this.index = -1;
		this.largestIndexList = new ArrayList<>(); 
		this.currentMax = Integer.MIN_VALUE;
		this.currentMaxCount = 0;
		this.largestNum_IndexSample = null;
	}
	
	public void read(int value) {
		index++;
		if (value > currentMax) {
			currentMax = value;
			currentMaxCount = 1;
			largestIndexList.clear();
			largestIndexList.add(index);
		} else if (value == currentMax) {
			largestIndexList.add(index);
			currentMaxCount++;
		}
		if (currentMaxCount == 1) {
			largestNum_IndexSample = largestIndexList.get(0);
		} else {
			int random = (int)(Math.random() * largestIndexList.size());
			largestNum_IndexSample = largestIndexList.get(random);
		}
		
	}
	
	public Integer getLargestNumberSampleIndex() {
		return this.largestNum_IndexSample;
	}
	
	public static void main(String[] args) {
		SamplingLargestNumber test = new SamplingLargestNumber();
		int[] a = new int[] {1,2,5,3,4,3,4,5};
		for (int i = 0; i < a.length; i++) {
			test.read(a[i]);
		}
		System.out.println(test.getLargestNumberSampleIndex());
	}
}
