package array.majority_number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MajorityNumber3 {
	public List<Integer> majority(int[] array, int k) {
		if (array == null || array.length == 0) {
			return new ArrayList<Integer>();
		}
		List<Integer> res = new ArrayList<>();
		int[] candidates = new int[k - 1];
		for (int i = 0; i < candidates.length; i++) {
			candidates[i] = i;
		}
		int[] count = new int[k - 1];
		for (int num : array) {
			boolean settled = false; // for each element in array
			                         // it can only go through one kind of process
			                         // 1. if num is one of the existed candidate, then do something and check next element in the array
			                         // 2. if num is not any existed candidate and then meet one candidate's count == 0, set this candidate as current num
			                         //    then check the next element in the array
			                         // 3. if num is not any existed candidate, and none of candidate's count == 0, then decrease each candidate's count by 1
			                         //    if its count > 0
			
			for (int i = 0; i < candidates.length; i++) {
				if (num == candidates[i]) {
					count[i]++;
					settled = true;
					break;
				}
			}
			if (settled)
				continue;
			for (int i = 0; i < candidates.length; i++) {
				if (count[i] == 0) {
					candidates[i] = num;
					count[i]++;
					settled = true;
					break;
				}
			}
			if (settled)
				continue;
			for (int i = 0; i < candidates.length; i++) {
				count[i] = (count[i] > 0) ? (count[i] - 1) : 0;
			}
		}
		
		// after finding the possible candidates
		// reset count of each candidate
		// iterate again on the array and update the count (dedup)
		Arrays.fill(count, 0);
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < candidates.length; j++) {
				if (array[i] == candidates[j]) {
					count[j]++;
					break;
				}
			}
		}
		for (int i = 0; i < candidates.length; i++) {
			if (count[i] > array.length / k) {
				res.add(candidates[i]);
			}
		}
		Collections.sort(res);
		return res;
	}
	
	public static void main(String[] args) {
		MajorityNumber3 test = new MajorityNumber3();
		int[] array = { 1, 2, 2, 2, 2, 2, 2 };
		int k = 2;
		List<Integer> res = test.majority(array, k);
		for (int i : res) {
			System.out.print(i + " ");
		}
	}
}
