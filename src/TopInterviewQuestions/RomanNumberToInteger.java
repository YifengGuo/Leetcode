package TopInterviewQuestions;
/**
 * 
 * @author yifengguo
 	Given a roman numeral, convert it to an integer.

	Input is guaranteed to be within the range from 1 to 3999.
 */
// 右加左减：
// 在较大的罗马数字的右边记上较小的罗马数字，表示大数字加小数字。
// 在较大的罗马数字的左边记上较小的罗马数字，表示大数字减小数字。
public class RomanNumberToInteger {
	public int romanToInt(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int[] arr = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case 'M':
				arr[i] = 1000;
				break;
			case 'D':
				arr[i] = 500;
				break;
			case 'C':
				arr[i] = 100;
				break;
			case 'L':
				arr[i] = 50;
				break;
			case 'X':
				arr[i] = 10;
				break;
			case 'V':
				arr[i] = 5;
				break;
			case 'I':
				arr[i] = 1;
				break;
			}
		}
		int sum = 0;
		// last element has no next element, so
		// it should be added at last
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] < arr[i + 1]) {
				sum -= arr[i];
			} else {
				sum += arr[i];
			}
		}
		return sum + arr[arr.length - 1];
	}
}
