/**
 * 
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
 

Constraints:

1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
 */
import java.util.*;
public class WordBreak {
    HashMap<String, Boolean> canSeparate;

    public boolean wordBreak(String s, List<String> wordDict) {
        if(s.isEmpty()) return false;
        canSeparate = new HashMap<>();
        return canBeSeparated(s, wordDict);
    }

    private boolean canBeSeparated(String s, List<String> wordDict){
        if(s.isEmpty()) return true;
        if(canSeparate.containsKey(s)) return canSeparate.get(s);
        for(String word:wordDict){
            if(s.startsWith(word)){
                if(canBeSeparated(s.substring(word.length(), s.length()), wordDict)){
                    canSeparate.put(s, true);
                    return true;
                }
            }
        }
        canSeparate.put(s, false);
        return false;
    }
}
