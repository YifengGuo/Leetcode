package array.Sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author guoyifeng
 * Given two integer arrays A1 and A2, sort A1 in such a way that the 
 * relative order among the elements will be same as those are in A2.

	For the elements that are not in A2, append them in the right end of the A1 in an ascending order.
	
	Assumptions:
	
	A1 and A2 are both not null.
	There are no duplicate elements in A2.
	Examples:
	
	A1 = {2, 1, 2, 5, 7, 1, 9, 3}, A2 = {2, 1, 3}, A1 is sorted to {2, 2, 1, 1, 3, 5, 7, 9}

 */
/*
 * basic idea: find each element in A2 and put it in the corresponding positions in res[]
 *             a tmp[] to record the unadded elements in A1 which should be swapped at at last part
 *             
 *     time = O(n ^ 2)
 *     space = o(n)
 */
public class SortInSpecifiedOrder {
	public int[] sortSpecial(int[] A1, int[] A2) {
		int[] res = new int[A1.length];
		int[] tmp = A1;
		int len = 0;
		for (int i = 0; i < A2.length; i++) {
			for (int j = 0; j < A1.length; j++) {
				if (A1[j] == A2[i]) {
					res[len] = A2[i];
					swap(tmp, len, j);
					len++;
				}
			}
		}
		int[] tmp2 = Arrays.copyOfRange(tmp, len, tmp.length);
		Arrays.sort(tmp2);
//		for (int i = 0; i < tmp2.length; i++) {
//			System.out.print(tmp2[i] + " ");
//		}
//		System.out.println("");
		int index = 0;
		for (int i = len; i < res.length; i++) {
			res[len++] = tmp2[index++];
		}
		return res;
	}
	
	private void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
//	public static void main(String[] args) {
//		int[] A1 = {4,7,9,5,1,3,2,3,6,1};
//		int[] A2 = {2,4,3,1};
//		SortInSpecialOrder test = new SortInSpecialOrder();
//		int[] res = test.sortSpecial(A1, A2);
//		for (Integer i : res) {
//			System.out.print(i + " ");
//		}
//	}
	
	/*
	 * better solution
	 * construct a MyComparator class to sort the array by the specified order we want
	 * this skill or trick is very useful and essential
	 */
	public static class MyComparator implements Comparator<Integer> {
		// key: element in A2, value: index of element in A2
		// the usage of Map is to check if current element in A1 exists in A2 or not by
		// checking its value's(index) validity in the map
		private Map<Integer, Integer> map;
		public MyComparator(int[] array) {
			map = new HashMap<>();
			for (int i = 0; i < array.length; i++) {
				map.put(array[i], i);
			}
		}
		@Override
		public int compare(Integer i1, Integer i2) {
			Integer index1 = map.get(i1);
			Integer index2 = map.get(i2);
			// if both Integer exist in the A2[], then indices will not be null
			// for number in A2, we need to sort it by its position(index)
			if (index1 != null && index2 != null) {
				return index1.compareTo(index2);
			} else if (index1 == null && index2 == null) { // for numbers which do not exist in A2[]
				                                           // sort them in ascending order
				return i1.compareTo(i2);
			} else {
				return index1 != null ? -1 : 1; // two number, if one is in A2 and the other is not
				                                // the one appeared in A2 shall be put in advance
			}
		}
	}
	public int[] sortSpecial2(int[] A1, int[] A2) {
		Integer[] tmp1 = toIntegerArray(A1);
		Arrays.sort(tmp1, new MyComparator(A2));
		int[] res = toIntArray(tmp1);
		return res;
	}
	
	private Integer[] toIntegerArray(int[] A1) {
		Integer[] res = new Integer[A1.length];
		for (int i = 0; i < A1.length; i++) {
			res[i] = Integer.valueOf(A1[i]);
		}
		return res;
	}
	
	private int[] toIntArray(Integer[] tmp) {
		int[] res = new int[tmp.length];
		for (int i = 0; i < tmp.length; i++) {
			res[i] = (int)tmp[i];
		}
		return res;
	}
}
