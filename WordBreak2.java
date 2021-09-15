/**
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:

Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]
Example 2:

Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []
 

Constraints:

1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
 */
import java.util.*;
public class WordBreak2 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>();
        
        for (String st : wordDict)
            dict.add(st);
        
        List<String> res = new ArrayList<>();
        
        boolean dp[] = new boolean[s.length() + 1];
        
        dp[0] = true;
        
        for (int i=0; i<dp.length; i++) {
            for (int j=0; j<i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        if (dp[s.length()] == false) return res;
        
        backTrack(res, s, dp, dict, new StringBuilder(), 0);
        
        return res;
    }
    
    void backTrack (List<String> res, String s, boolean dp[], Set<String> dict, StringBuilder sb, int first)
    {
        if (first == s.length()) {
            res.add(sb.toString().substring(1));
            return;
        } else {
            for (int i = first + 1; i< s.length() + 1; i++) {
                if (dp[i] && dict.contains(s.substring(first, i))) {
                    int len = sb.length();
                    sb.append(" " + s.substring(first, i));
                    backTrack(res, s, dp, dict, sb, i);
                    sb.delete(len, sb.length() + 1);
                }
            }
        }
    }
}
