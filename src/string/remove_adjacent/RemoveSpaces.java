package string.remove_adjacent;
/**
 * 
 * @author guoyifeng
 * Given a string, remove all leading/trailing/duplicated empty spaces.

	Assumptions:
	
	The given string is not null.
	Examples:
	
	“  a” --> “a”
	“   I     love MTV ” --> “I love MTV”
 */
/*
 * demo:
 * 	   " I love     MTV   "
 *      i
 *      
 *      I love MTV "
 *                 index (duplicate spaces in the tailings are removed)
 *      so if there are spaces in the end, index will be at next position of first space
 *      then we need to return arr[0, index - 1) exclusive the last position (which is space)
 *      otherwise return arr[0, index)
 *                 
 */
public class RemoveSpaces {
	public String removeSpaces(String input) {
		if (input == null) {
			return null;
		}
		char[] arr = input.toCharArray();
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == ' ' && (i == 0 || arr[i - 1] == ' ')) { // remove heading and internal duplicate spaces
				continue;
			}
			arr[index++] = arr[i];
		}
		// remove tail spaces
		if (index > 0 && arr[index - 1] == ' ') {
			return new String(arr, 0, index - 1);
		} else {
			return new String(arr, 0, index);
		}
	}
}

