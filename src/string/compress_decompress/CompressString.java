package string.compress_decompress;

//abbcccdeee
//ab2c3de3
/**
 * 
 * @author yifengguo
 * Given a string, replace adjacent, repeated characters with the character followed by the number 
 * of repeated occurrences. If the character does not has any adjacent, repeated occurrences, it is not changed.

	Assumptions
	
	The string is not null
	
	The characters used in the original string are guaranteed to be ‘a’ - ‘z’
	
	There are no adjacent repeated characters with length > 9
	
	Examples
	
	“abbcccdeee” → “ab2c3de3”

 
 */
public class CompressString {
	public String compress(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		StringBuilder sb = new StringBuilder();
		int num = 1;
		for (int i = 0; i < s.length(); i++) {
			if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
				num++;
				continue;
			} else {
				if (num == 1) {
					sb.append(s.charAt(i));
				} else {
					sb.append(s.charAt(i));
					sb.append(num);
					num = 1;
				}
			}
		}
		return sb.toString();
	}
}
