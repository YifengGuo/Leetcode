package TopInterviewQuestions;

/**
 * 
 * @author guoyifeng 
 * 		   Given a non-negative integer represented as a non-empty
 *         array of digits, plus one to the integer.
 * 
 *         You may assume the integer do not contain any leading zero, except
 *         the number 0 itself.
 * 
 *         The digits are stored such that the most significant digit is at the
 *         head of the list.
 */
public class PlusOne {
	public int[] plusOne(int[] digits) {
		if (digits == null || digits.length == 0) {
			return null;
		}
		for (int i = digits.length - 1; i >= 0; i--) {
			if (digits[i] != 9) {
				digits[i] += 1;
				break;
			} else { // some digit == 9
				digits[i] = 0;
			}
		}
		if (digits[0] == 0) {
			int[] res = new int[digits.length + 1];
			for (int i = 1; i < res.length; i++) {
				res[i] = digits[i - 1];
			}
			res[0] = 1;
			return res;
		}
		return digits;
	}
}
