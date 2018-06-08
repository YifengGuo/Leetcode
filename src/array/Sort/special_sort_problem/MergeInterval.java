package array.Sort.special_sort_problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * 
 * @author guoyifeng
 * 
	Given a collection of intervals, merge all overlapping intervals.
	
	Example 1:
	
	Input: [[1,3],[2,6],[8,10],[15,18]]
	Output: [[1,6],[8,10],[15,18]]
	Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
	Example 2:
	
	Input: [[1,4],[4,5]]
	Output: [[1,5]]
	Explanation: Intervals [1,4] and [4,5] are considerred overlapping.
 */
class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}
}

public class MergeInterval {
	public List<Interval> merge(List<Interval> intervals) {
		List<Interval> res = new ArrayList<>();
		if (intervals == null || intervals.size() == 0) {
			return res;
		}
		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval i1, Interval i2) {
				return i1.start - i2.start;
			}
		});
		Interval prev = null; // previous Interval object of the current one
		for (Interval cur : intervals) {
			if (prev == null || cur.start > prev.end) { // add when head one or
														// cur and prev cannot merge
				res.add(cur);
				prev = cur;
			} else if (cur.end >= prev.end) { // we have sort the interval by the start, prev.start <= cur.start
				                              // so if cur.end >= prev.end, they can be merged
				prev.end = cur.end; // directly modify prev in the res
			}
		}
		return res;
	}
}
