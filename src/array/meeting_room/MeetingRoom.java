package array.meeting_room;

import java.util.Arrays;
import java.util.Comparator;
/**
 * 
 * @author guoyifeng
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
 * determine if a person could attend all meetings.

	Example 1:
	
	Input: [[0,30],[5,10],[15,20]]
	Output: false
	Example 2:
	
	Input: [[7,10],[2,4]]
	Output: true
 */
public class MeetingRoom {
	public boolean canAttendMeetings(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) {
			return true;
		}
		Arrays.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval i1, Interval i2) {
				if (i1.start == i2.start) {
					if (i1.end == i2.end) {
						return 0;
					} else {
						return i1.end - i2.end < 0 ? -1 : 1;
					}
				} else {
					return i1.start - i2.start < 0 ? -1 : 1;
				}
			}
		});

		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i].start < intervals[i - 1].end) {
				return false;
			}
		}
		return true;
	}
}
