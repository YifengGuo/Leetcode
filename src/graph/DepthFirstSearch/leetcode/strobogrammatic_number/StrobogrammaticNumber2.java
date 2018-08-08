package graph.DepthFirstSearch.leetcode.strobogrammatic_number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author guoyifeng
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

	Find all strobogrammatic numbers that are of length = n.
	
	Example:
	
	Input:  n = 2
	Output: ["11","69","88","96"]
 */
public class StrobogrammaticNumber2 {
    public List<String> findStrobogrammatic(int n) {
        if (n <= 0) {
            return new ArrayList<String>();
        } 
        
        return helper(n, n);
    }
    
    private List<String> helper(int n, int m) {
        // base case
        if (n == 0) {
            return new ArrayList<String>(Arrays.asList(""));
        }   
        if (n == 1) {
            return new ArrayList<String>(Arrays.asList("1", "8", "0"));
        }
        
        // recursion call to get all the combinations with length - 2 (tail and head)
        List<String> list = helper(n - 2, m);
        
        List<String> res = new ArrayList<>();
        
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (n != m) {
                res.add("0" + s + "0"); // do not add 0 on head and tail
            }
            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("9" + s + "6");
            res.add("8" + s + "8");
        }
        return res;
    }
}