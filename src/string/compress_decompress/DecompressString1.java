package string.compress_decompress;
/**
 * 
 * @author yifengguo
 * Given a string in compressed form, decompress it to the original string. 
 * The adjacent repeated characters in the original string are compressed to 
 * have the character followed by the number of repeated occurrences. 
 * If the character does not have any adjacent repeated occurrences, it is not compressed.

	Assumptions
	
	The string is not null
	
	The characters used in the original string are guaranteed to be ‘a’ - ‘z’
	
	There are no adjacent repeated characters with length > 9
	
	Examples
	
	“acb2c4” → “acbbcccc”
 */
public class DecompressString1 {
	public String decompress(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (isNum(s.charAt(i))) {
				int num = toNum(s.charAt(i));
				for (int j = 0; j < num; j++) {
					sb.append(s.charAt(i - 1));
				}
			} else {
				if (i + 1 < s.length() && isNum(s.charAt(i + 1))) {
					continue;
				} else {
					sb.append(s.charAt(i));
				}
			}
		}
		return sb.toString();
	}

	private boolean isNum(char c) {
		if (c - '0' >= 0 && c - '0' <= 9) {
			return true;
		}
		return false;
	}

	private int toNum(char c) {
		return c - '0';
	}
}
