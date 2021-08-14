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
public class WordLadderOptimized {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(beginWord.equals(endWord)) return 0;
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        Set<String> list = new HashSet<>();
        for(String w: wordList){
            list.add(w);
        }
        if(!wordList.contains(endWord)){
            return 0;
        }
        int level=2;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- >0){
                String curr = queue.poll();
                for( int i= 0; i< curr.length(); i++){
                    char[] chars= curr.toCharArray();
                    for(char c='a'; c<='z' ; c++){
                        chars[i]=c;
                        String word = new String(chars);
                        if(word.equals(endWord)){
                            return level;
                        }
                        if(list.contains(word)){
                            queue.add(word);
                            list.remove(word);
                        }
                    }
                }
            }
                
            level ++;
        }
        return 0;
    }
}
