package string.remove_adjacent;

import java.util.Arrays;
/**
 * 
 * @author yifengguo
 * Remove adjacent, repeated characters in a given string, 
 * leaving only two characters for each group of such characters. 
 * The characters in the string are sorted in ascending order.

	Assumptions
	
	Try to do it in place.
	Examples
	
	“aaaabbbc” is transferred to “aabbc”
	Corner Cases
	
	If the given string is null, we do not need to do anything.
 */
/*
 * same idea as array remove duplicates 2
 */
public class RemoveAdjacentRepeatedCharacters2 {
	public String deDup(String input) {
		if (input == null || input.length() <= 2) {
			return input;
		}
		char[] arr = input.toCharArray();
		int slow = 2;
		for (int fast = 2; fast < arr.length; fast++) {
			if (arr[fast] == arr[slow - 2]) {
				continue;
			} else {
				arr[slow++] = arr[fast];
			}
		}
		return String.valueOf(Arrays.copyOfRange(arr, 0, slow));
	}
}
