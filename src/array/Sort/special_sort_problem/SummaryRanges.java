package array.Sort.special_sort_problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author guoyifeng
 * Given a sorted integer array without duplicates, return the summary of its ranges.

	Example 1:
	
	Input:  [0,1,2,4,5,7]
	Output: ["0->2","4->5","7"]
	Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
	Example 2:
	
	Input:  [0,2,3,4,6,8,9]
	Output: ["0","2->4","6","8->9"]
	Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
 */
/*
 * special test case : 
 * 	[-2147483648,-2147483647,2147483647]
 *  [-2, -1, 1, 2, 2147483646, 2147483647]
 *  so consider the numerical overflow cases
 */
public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        // sanity check
        if (nums == null || nums.length == 0) {
            return res;
        }
        // corner case
        if (nums.length == 1) {
            res.add(String.valueOf(nums[0]));
            return res;
        }
        List<String> list = new ArrayList<>();
        int start = 0;
        // initialize list with first element in the array
        list.add(String.valueOf(nums[start]));
        for (int end = 1; end < nums.length; end++) {
            if (Math.abs(nums[end] - nums[end - 1]) > 1) { // look at previous number to see if both two are contiguous
                if (list.size() > 1) { // if list contains more than one numbers, use "->"
                    res.add(String.valueOf(nums[start] + "->" + nums[end - 1]));
                } else {
                    res.add(String.valueOf(nums[start]));
                }
                // after add current ranges to res, move start and reset list as before
                start = end;
                list.clear();
                list.add(String.valueOf(nums[start]));
            } else {
                list.add(String.valueOf(nums[end]));
            }
        }
        // last ranges cannot be appended in the for loop
        // so at last check the list to see if the range has only one number or multiple ones
        if (list.size() <= 1) {
            res.add(list.get(0));
        } else {
            res.add(String.valueOf(list.get(0) + "->" + list.get(list.size() - 1)));
        }
        return res;
    }
}
