package string.classical;
/**
 * 
 * @author yifengguo
 * Given two binary strings, return their sum (also a binary string).

	The input strings are both non-empty and contains only characters 1 or 0.
	
	Example 1:
	
	Input: a = "11", b = "1"
	Output: "100"
	Example 2:
	
	Input: a = "1010", b = "1011"
	Output: "10101"
 */
/*
 * demo:
 * 	 1111
 *     11
 *  -------   
 *  10010   = 18
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        if (a == null || b == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;
        
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) {
                sum += a.charAt(i--) - '0';
            }
            if (j >= 0) {
                sum += b.charAt(j--) - '0';
            }
            
            sb.append(sum % 2);
            carry = sum / 2;
        }
        // consider most significant bit has number
        if (carry != 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
