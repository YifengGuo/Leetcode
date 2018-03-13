package forusall_OA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author yifengguo 
 *         Given an unsorted array, find two elements that are
 *         adjacent if the array is sorted and make sure the distance between
 *         the two elements are largest when the array is unsorted.
 */
public class SortAndFindLargestDistance {
	public int largest(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(arr[i])) {
				map.get(arr[i]).add(i);
			} else {
				List<Integer> list = new ArrayList<>();
				list.add(i);
				map.put(arr[i], list);
			}
		}

		Set<Integer> ts = new TreeSet<>();
		for (int i : arr) {
			ts.add(i);
		}
		int[] tmp = new int[ts.size()];
		int index = 0;
		int res = 0;
		Iterator<Integer> it = ts.iterator();
		while (it.hasNext()) {
			tmp[index++] = it.next();
		}
		for (int i = 1; i < tmp.length; i++) {
			res = Math.max(res, Math.abs(map.get(tmp[i]).get(map.get(tmp[i]).size() - 1) - map.get(tmp[i - 1]).get(0)));
		}
		return res;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 7, 2, 5, 4, 6, 5, 5, 5, 5, 5, 3 };
		System.out.println(new SortAndFindLargestDistance().largest(arr));
	}
}
