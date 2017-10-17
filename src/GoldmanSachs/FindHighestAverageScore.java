package GoldmanSachs;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class FindHighestAverageScore {
	public int highest(String[][] input) {
		if (input == null || input.length == 0 || input[0].length == 0) {
			return 0;
		}
		Map<String, Integer> occurrence = new HashMap<>(); // key: student name, value: subject number
		Map<String, Double> totalScore = new HashMap<>(); // key: student name, value: total score of student
		double globalMax = Integer.MIN_VALUE;
		for (int i = 0; i < input.length; i++) {
			if (!occurrence.containsKey(input[i][0])) {
				occurrence.put(input[i][0], 1);
			} else {
				occurrence.put(input[i][0], occurrence.get(input[i][0]) + 1);
			}
			if (!totalScore.containsKey(input[i][0])) {
				totalScore.put(input[i][0], Double.parseDouble(input[i][1]));
			} else {
				totalScore.put(input[i][0], totalScore.get(input[i][0]) + Double.parseDouble(input[i][1]));
			}
		}
		for (Entry<String, Integer> e : occurrence.entrySet()) {
			if (totalScore.get(e.getKey()) >= 0 && totalScore.get(e.getKey()) / e.getValue() > globalMax) {
				globalMax = totalScore.get(e.getKey()) / e.getValue();
			} else if (totalScore.get(e.getKey()) / e.getValue() < 0) {
				double avg = Math.floor(totalScore.get(e.getKey()) / e.getValue());
				if (avg > globalMax) {
					globalMax = avg;
				}
			}
		}
		return (int) globalMax;
	}
	
	public static void main(String[] args) {
		String[][] input = new String[][]{{"bob", "-100"}, {"Ted", "-100"}, {"Ted", "-20"}, {"bob", "-10"},{"bob", "-10"},{"bob", "-1"},{"bob", "-1"},{"bob", "-1"}};
		int res = new FindHighestAverageScore().highest(input);
		System.out.println(res);
		System.out.println(-123.0 / 6);
	}
}
