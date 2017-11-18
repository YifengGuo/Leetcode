package bit_manipulation;
/**
 * 
 * @author guoyifeng
 	Determine if the characters of a given string are all unique.

	Assumptions
	
	We are using ASCII charset, the value of valid characters are from 0 to 255
	The given string is not null
	Examples
	
	all the characters in "abA+\8" are unique
	"abA+\a88" contains duplicate characters
 */
/*
 * basic idea: construct a bit map like:
 * 		0000 .... 0000
 * 		0000 .... 0000
 * 		0000 .... 0000
 *		0000 .... 0000
 * 		0000 .... 0000
 *		0000 .... 0000
 * 		0000 .... 0000
 * 		0000 .... 0000
 * 
 * and one of the bit represents a character in the ascii. 
 * time = O(n)
 * space = O(8) = O(1)
 */
public class AllUniqueCharacters2 {
	public boolean allUnique(String s) { 
		if (s == null || s.length() == 0) {
			return true;
		}
		int[] bitMap = new int[8];
		char[] arr = s.toCharArray();
		for (char c : arr) {
			int index = c; // automatically convert char to int
			int row = index / 32; // row index in bitMap
			int col = index % 32; // col index in bitMap
			if ((bitMap[row] & (1 << col)) > 1) { // occur more than once in bitMap
				return false;
			} else {
				bitMap[row] = (bitMap[row] | (1 << col));
			}
		}
		return true;
	}
}
