package array.meeting_room;

import java.util.Arrays;
import java.util.PriorityQueue;

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
/**
 * 
 * @author guoyifeng
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
 * find the minimum number of conference rooms required.

	Example 1:
	
	Input: [[0, 30],[5, 10],[15, 20]]
	Output: 2
	Example 2:
	
	Input: [[7,10],[2,4]]
	Output: 1
 */
/*
 * basic idea: sort intervals by its start 
 *             and use a minHeap based on intervals' end
 */
public class MeetingRoom2 {
	public int minMeetingRooms(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) {
			return 0;
		}
		if (intervals.length == 1) {
			return 1;
		}
		// [[0, 30],[5, 10],[15, 20]]
		// [[2,4], [7,10]]
		Arrays.sort(intervals, (i1, i2) -> i1.start - i2.start);
		PriorityQueue<Interval> pq = new PriorityQueue<>((i1, i2) -> i1.end - i2.end);
		for (Interval i : intervals) {
			if (pq.isEmpty()) {
				pq.offer(i);
			} else {
				Interval top = pq.poll();
				if (top.end <= i.start) {  // two intervals can be merged
					pq.offer(new Interval(top.start, i.end));
				} else { // need new meeting room
					pq.offer(i);
					pq.offer(top);
				}
			}
		}
		return pq.size();
	}
}
