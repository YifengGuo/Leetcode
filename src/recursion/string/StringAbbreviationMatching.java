package recursion.string;
/**
 * 
 * @author yifengguo
  Word “book” can be abbreviated to 4, b3, b2k, etc. Given a string and an abbreviation, 
  return if the string matches the abbreviation.

	Assumptions:
	
	The original string only contains alphabetic characters.
	Both input and pattern are not null.
	Examples:
	
	pattern “s11d” matches input “sophisticated” since “11” matches eleven chars “ophisticate”.

 */
/*
 * 	case 1: 2 pointers both point to letter, then determine if they are identical
	   string 1:   s   11d
	               i
	   string 2:   s   ophisticated
	               j
	               and then recurse from i + 1 and j + 1
	               pay attention: do not call s.substring() !!!!!!
	               for its time is O(n)
	               
 *  case 2: pattern pointer points to number while input pointer points to letter
 *          from s1 get the actual number
 *          and add it to the point j
 *  	string 1:  s 11d
 *  				 i i
 *  	string 2:  s ophisticated
 *                   j          j + 11
 *  	
 *  
 *  time = O(n) where n is the length of input
 */
public class StringAbbreviationMatching {
	public boolean match(String input, String pattern) {
		if (input == null || pattern == null) {
			return false;
		}
		// base case
		return helper(input, 0, pattern, 0);
	}
	
	private boolean helper(String input, int i, String pattern, int j) {
		// base case
		if (i == input.length() && j == pattern.length()) {
			return true;
		} else if (i == input.length() || j == pattern.length()) {
			return false;
		}
		
		// process two cases
		// case 1:
		if (!isDigit(pattern.charAt(j))) {
			if (input.charAt(i) == pattern.charAt(j)) {
				return helper(input, i + 1, pattern, j + 1);
			} else {
				return false;
			}
		} else { // case 2: pattern points to number while another points to letter
			int num = 0;
			while (j < pattern.length() && isDigit(pattern.charAt(j))) {
				num = num * 10 + toInt(pattern.charAt(j));
				j++;
			}
			i += num;
			return helper(input, i, pattern, j);
		}
	}
	
	private boolean isDigit(Character c) {
		if (c >= '0' && c <= '9') {
			return true;
		}
		return false;
	}
	
	private int toInt(Character c) {
		return (int)(c - '0');
	}
}
