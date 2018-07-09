package array.shortest_word_dis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author guoyifeng
 * Given a list of words and two words word1 and word2, return the shortest distance 
 * between these two words in the list.

	word1 and word2 may be the same and they represent two individual words in the list.
	
	Example:
	Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
	
	Input: word1 = “makes”, word2 = “coding”
	Output: 1
	Input: word1 = "makes", word2 = "makes"
	Output: 3
	Note:
	You may assume word1 and word2 are both in the list.


 */
/*
 * better solution would be one pass on the array
 */
public class ShortestWordDistance3 {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        if (words == null || words.length == 0) {
            return 0;
        }
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (map.containsKey(words[i])) {
                map.get(words[i]).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(words[i], list);
            }
        }
        int res = Integer.MAX_VALUE;
        if (word1.equals(word2)) {
            List<Integer> list = map.get(word1);
            for (int i = 1; i < list.size(); i++) {
                res = Math.min(res, Math.abs(list.get(i) - list.get(i - 1)));
            }
        } else {
            List<Integer> list1 = map.get(word1);
            List<Integer> list2 = map.get(word2);
            for (int i = 0; i < list1.size(); i++) {
                for (int j = 0; j < list2.size(); j++) {
                    res = Math.min(res, Math.abs(list1.get(i) - list2.get(j)));
                }
            }
        }
        return res;
    }
}
