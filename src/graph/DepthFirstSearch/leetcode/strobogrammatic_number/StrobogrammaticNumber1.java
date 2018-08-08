package graph.DepthFirstSearch.leetcode.strobogrammatic_number;
/**
 * 
 * @author guoyifeng
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

	Write a function to determine if a number is strobogrammatic. The number is represented as a string.

	Example 1:
	
	Input:  "69"
	Output: true
	Example 2:
	
	Input:  "88"
	Output: true
	Example 3:
	
	Input:  "962"
	Output: false

 */
public class StrobogrammaticNumber1 {
    public boolean isStrobogrammatic(String num) {
        if (num == null) return false;
        if (num.length() == 0) return true;
        int left = 0, right = num.length() - 1;
        while (left <= right) {
            if (num.charAt(left) == '6') {
                if (num.charAt(right) != '9') {
                    return false;
                }
                left++;
                right--;
            } else if (num.charAt(left) == '9') {
                if (num.charAt(right) != '6') {
                    return false;
                }
                left++;
                right--;
            } else if (num.charAt(left) == '8') {
                if (num.charAt(right) != '8') {
                    return false;
                }
                left++;
                right--;
            } else if (num.charAt(left) == '1') {
                if (num.charAt(right) != '1') {
                    return false;
                }
                left++;
                right--;
            } else if (num.charAt(left) == '0') {
                if (num.charAt(right) != '0') {
                    return false;
                }
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
}