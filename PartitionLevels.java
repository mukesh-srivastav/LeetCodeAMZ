/**
 * You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part.

Return a list of integers representing the size of these parts.

 

Example 1:

Input: s = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
Example 2:

Input: s = "eccbbbbdec"
Output: [10]
 */
import java.util.*;
public class PartitionLevels {
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        
        if (S==null || S.length() == 0) {
            return res;    
        }
        
        int max = 0, start = 0;
        
        for (int i=0; i<S.length(); i++) {
            max = Math.max(max, S.lastIndexOf(S.charAt(i)));
            
            if (i == max) {
                res.add(i - start + 1);
                start = i+1;
            }
        }
        
        return res;
    }
}
