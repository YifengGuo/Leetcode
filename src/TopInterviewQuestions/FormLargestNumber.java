package TopInterviewQuestions;

import java.util.Arrays;
import java.util.Comparator;

public class FormLargestNumber {
	/*
	 * basic idea:
	 * 		String s1 = 9;
	 * 		String s2 = 11;
	 * 		case 1: s1 + s2 = "911"
	 *      case 2: s2 + s1 = "119"
	 *    so we always need to maintain that two strings are sorted in an order that
	 *    s1 + s2 > s2 + s1
	 */
	public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        String[] arr = new String[nums.length];
        // convert int array to String array
        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(arr, new Comparator<String>() { 
            @Override
            public int compare(String s1, String s2) {
                String str1 = s1 + s2;
                String str2 = s2 + s1;
                // directly invoke compareTo() to compare String 
                // compareTo() for String is in lexicographic order
                // when we append string into StringBuilder, we want
                // larger case advanced, so here is reverse natural order
                // e.g: 11 and 9 should be ordered like 9, 11 for 911 > 119
                return str2.compareTo(str1);
            }
        });
        // corner case when most significant bit is 0, then whole number is "0"
        if (arr[0].charAt(0) == '0') {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }
        return sb.toString();
    }
	
	/*
	 * my solution based on PriorityQueue
	 * some corner cases cannot be accepted
	 */
//	public String largestNumber(int[] nums) {
//		if (nums == null || nums.length == 0) {
//			return null;
//		}
//		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
//			@Override
//			public int compare(Integer a, Integer b) {
//				return sort(a, b);
//			}
//		});
//		for (Integer i : nums) {
//			maxHeap.offer(i);
//		}
//		StringBuilder sb = new StringBuilder();
//		if (maxHeap.peek() == 0) {
//			sb.append(0);
//			return sb.toString();
//		}
//		while (!maxHeap.isEmpty()) {
//			sb.append(maxHeap.poll());
//		}
//		return sb.toString();
//	}
//
//	private int getMostSignificant(int n) {
//		int res = 0;
//		if (n < 10) {
//			return n;
//		}
//		while (n >= 10) {
//			res = n / 10;
//			n /= 10;
//		}
//		return res;
//	}
//
//	private int deleteMostSignificant(int n) {
//		int dight = 1;
//		int tmp = n;
//		while (tmp > 10) {
//			tmp /= 10;
//			dight *= 10;
//			if (tmp / 10 < 10) {
//				return tmp % 10;
//			}
//		}
//		return -1;
//	}
//
//	private int sort(int a, int b) {
//		if (getMostSignificant(a) > getMostSignificant(b)) {
//			return -1;
//		} else if (getMostSignificant(a) == getMostSignificant(b)) {
//			if (a >= 10 && b >= 10) {
//				return sort(deleteMostSignificant(a), deleteMostSignificant(b));
//			} else if (a >= 10 && b < 10) {
//				return sort(deleteMostSignificant(a), b);
//			} else if (a < 10 && b >= 10) {
//				return sort(a, deleteMostSignificant(b));
//			} else {
//				return 0;
//			}
//		}
//		return 1;
//	}
//
//	public static void main(String[] args) {
//		int[] nums = new int[] { 3544, 3013, 3061, 468 };
//		int a = 3061;
//		String res = new FormLargestNumber().largestNumber(nums);
//		System.out.println(res);
//		// System.out.println(new FormLargestNumber().deleteMostSignificant(a));
//	}
}
