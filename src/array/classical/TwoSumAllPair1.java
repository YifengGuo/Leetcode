package array.classical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author yifengguo
 * Find all pairs of elements in a given array that sum to the given target number. Return all the pairs of indices.

	Assumptions
	
	The given array is not null and has length of at least 2.
	
	Examples
	
	A = {1, 3, 2, 4}, target = 5, return [[0, 3], [1, 2]]
	
	A = {1, 2, 2, 4}, target = 6, return [[1, 3], [2, 3]]


 */
/*
 * basic idea: use hashMap: key: current number array[i]
 *                          value: list of indices of duplicate elements in the array
 *                          
 *  time = O(n ^ 2)
 *  space = O(n ^ 2) each element need to stored in hash_map and its value is a list
 */
public class TwoSumAllPair1 {
	public static List<List<Integer>> allPairs(int[] array, int target) {
		List<List<Integer>> res = new ArrayList<>();
		if (array == null || array.length == 0) {
			return res;
		}
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < array.length; i++) { // O(n)
			if (map.containsKey(target - array[i])) {
				if (map.get(target - array[i]).size() == 1) {
					List<Integer> list = new ArrayList<>();
					list.add(i);
					list.add(map.get(target - array[i]).get(0));
					res.add(list);
				} else {
					for (int j = 0; j < map.get(target - array[i]).size(); j++) { // O(n)
						List<Integer> list = new ArrayList<>();
						list.add(i);
						list.add(map.get(target - array[i]).get(j)); // O(1) for ArrayList get()
						res.add(list);
					}
				}
			}
			// try to process current element after check existence of 2-sum
			// if process it before 2-sum check, current element would be checked twice
			if (map.containsKey(array[i])) {
				map.get(array[i]).add(i);
			} else {
				List<Integer> list = new ArrayList<>();
				list.add(i);
				map.put(array[i], list);
			}
		}
		return res;
	}

	
	public static void main(String[] args) {
		int[] a = {2,3,2,4,7};
		int target = 6;
		List<List<Integer>> res = allPairs(a, target);
		for (List<Integer> list : res) {
			for (int i : list) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
