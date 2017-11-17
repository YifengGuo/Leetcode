package bit_manipulation;
/**
 * 
 * @author guoyifeng
 	Determine if the characters of a given string are all unique.

	Assumptions
	
	The only set of possible characters used in the string are 'a' - 'z', the 26 lower case letters.
	The given string is not null.
	Examples
	
	the characters used in "abcd" are unique
	the characters used in "aba" are not unique
 */
/*
 * basic idea: A table maintains each character occurrence
 * time = O(n)
 */
public class AllUniqueCharacters1 {
	public boolean allUnique(String word) {
		if (word == null || word.length() == 0) {
			return true;
		}
		int[] table = new int[26];
		for (int i = 0; i < word.length(); i++) {
			table[word.charAt(i) - 'a']++;
		}
		for (int i = 0; i < table.length; i++) {
			if (table[i] > 1) {
				return false;
			}
		}
		return true;
	}
}


