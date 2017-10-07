package TopInterviewQuestions;
/**
 * 
 * @author guoyifeng
 	1.     1
	2.     11
	3.     21
	4.     1211
	5.     111221
	1 is read off as "one 1" or 11.
	11 is read off as "two 1s" or 21.
	21 is read off as "one 2, then one 1" or 1211.
	Given an integer n, generate the nth term of the count-and-say sequence.
	
	Note: Each term of the sequence of integers will be represented as a string.
	
	Example 1:
	
	Input: 1
	Output: "1"
	Example 2:
	
	Input: 4
	Output: "1211"
 */
// 1.     1
// 2.     11
// 3.     21
// 4.     1211
// 5.     111221 
// 6.     312211
// 7.     13112221
// 8.     1113213211
// 9.     31131211131221
// 10.    13211311123113112211
public class CountAndSay {
	public String countAndSay(int n) {
		if (n <= 0) {
			return "";
		}
		String s = "1";
		for (int i = 1; i < n; i++) {
			s = say(s); // input String is last iteration result and say it with
						// rule of say()
		}
		return s;
	}

	private String say(String s) {
		StringBuilder sb = new StringBuilder();
		int count = 1;
		char c = s.charAt(0);
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == c) {
				count++;
				continue;
			} else { // meet new character, now we need to say last character
				sb.append(count); // how many c
				sb.append(c); // c’s content
				// update c and count
				c = s.charAt(i);
				count = 1;
			}
		}
		// n == 1 case or last character block would not be recorded
		// append it to sb manually
		sb.append(count);
		sb.append(c);
		return sb.toString();
	}
}
