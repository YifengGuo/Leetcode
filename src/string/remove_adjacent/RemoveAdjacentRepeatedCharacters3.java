package string.remove_adjacent;

import java.util.Arrays;
/**
 * 
 * @author yifengguo
  Remove adjacent, repeated characters in a given string, 
  leaving no character for each group of such characters. 
  The characters in the string are sorted in ascending order.

	Assumptions
	
	Try to do it in place.
	Examples
	
	“aaaabbbc” is transferred to “c”
	Corner Cases
	
	If the given string is null, we do not need to do anything.
 */
/*
 * basic idea: same as array remove duplicates 3
 */
public class RemoveAdjacentRepeatedCharacters3 {
	public String deDup(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		char[] arr = input.toCharArray();
		int slow = 0;
		int fast = 0;
		while (fast < arr.length) {
			int begin = fast;
			while (fast < arr.length && arr[fast] == arr[begin]) {
				fast++;
			}
			if (fast - begin == 1) {
				arr[slow++] = arr[begin];
			}
		}
		return String.valueOf(Arrays.copyOfRange(arr, 0, slow));
	}
}
