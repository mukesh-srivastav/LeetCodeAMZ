/**
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

 

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 */
import java.util.*;
public class WordLadder {
    Set<String>visited=new HashSet<>();
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord))return 0;

        
        Queue<String>q=new LinkedList<>();
        q.offer(beginWord);
        visited.add(beginWord);
        
        int level=1;
        
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                String current=q.poll();
                if(current.equals(endWord))return level;
                
                List<String>adjacent=Adjacent(current,wordList);
                
                if(adjacent!=null){
                    for(String s:adjacent)
                        q.offer(s);
                }
            }
            level++;
        }
     return 0;
    }
    
    //Adjacent words 
    private List<String> Adjacent(String current,List<String> wordList){
        List<String>adjacents=new ArrayList<>();
        
        for(String s:wordList){
            if(visited.contains(s))continue;
            int compare=0;
            for(int i=0;i<s.length();i++){
                if(compare>1)break;
                if(current.charAt(i)!=s.charAt(i))
                    compare++;
            }
            if(compare==1){
                adjacents.add(s);
                visited.add(s);
            }
        }
        return adjacents;
    }
}
