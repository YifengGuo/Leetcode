package array.shortest_word_dis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author guoyifeng
 * Design a class which receives a list of words in the constructor, 
 * and implements a method that takes two words word1 and word2 and return the 
 * shortest distance between these two words in the list. Your method will be called
 * repeatedly many times with different parameters. 

	Example:
	Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
	
	Input: word1 = “coding”, word2 = “practice”
	Output: 3
	Input: word1 = "makes", word2 = "coding"
	Output: 1
	Note:
	You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
/*
 * for this problem, because the shortest() will be invoked for many times, say k times
 * so one-pass O(n) method times k would use O(nk) time complexity which would be huge if
 * the dictionary size is too large.
 * So we need to use a hashmap to cache word with all of its indices in the dict
 * say average occurrence of each word in the dict is m
 * with O(1) time we can get all the indices of word1 and word2 in the dict, and use
 * O(m ^ 2) to get all possible combination of index distance
 * 
 * time O(m ^ 2)
 * space O(n + m) ~ O(n) if n >> m
 */
public class ShortestWordDistance2 {
    Map<String, List<Integer>> map;
    public ShortestWordDistance2(String[] words) {
        map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (map.containsKey(words[i])) {
                map.get(words[i]).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(words[i], list);
            }
        }
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                res = Math.min(res, Math.abs(list1.get(i) - list2.get(j)));
            }
        }
        return res;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */
