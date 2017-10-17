package GoldmanSachs;
/**
 * 
 * @author guoyifeng
 	input: aaabbbccccc output: a3b3c5
 */
/*
 * time = O(n)
 * space = O(1)
 */
public class CompressString {
	public String compress(String s) {
		StringBuilder sb = new StringBuilder();
		int count = 1;
		// corner case
		if (s.charAt(0) != s.charAt(1)) {
			sb.append(s.charAt(0));
			sb.append(count);
		}
		int idx = 0;
		for (idx = 1; idx < s.length(); idx++) {
			if (s.charAt(idx - 1) == s.charAt(idx)) {
				count++;
			} else {
				sb.append(s.charAt(idx - 1));
				sb.append(count);
				count = 1;
			}
		}
		// add last character, idx now == s.length() - 1
		sb.append(s.charAt(idx - 1));
		sb.append(count);
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String s = "aaabbbccccsdiasdsdc";
		System.out.println(new CompressString().compress(s));
	}
}
