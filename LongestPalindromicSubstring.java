/**
 * Given a string s, return the longest palindromic substring in s.

 

Example 1:

Input: s = "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"
Example 3:

Input: s = "a"
Output: "a"
Example 4:

Input: s = "ac"
Output: "a"
 

Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.

 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        
        int n = s.length();
        if (n==0)
            return s;
        boolean dp[][] = new boolean[n][n];
        
        int start = 0, maxLength = 1;
        for(int i=0; i<n;i++) {
            dp[i][i] = true;
        }
        
        for(int i=0; i<n-1;i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                dp[i][i+1] = true;
                start = i;
                maxLength = 2;
            }
        }
        
        for(int k=3; k<=n;k++) {
            for (int i=0; i<n-k+1; i++) {
                int j = i+k-1;
                
                if (dp[i+1][j-1] && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    if (k > maxLength) {
                        maxLength = k;
                        start = i;
                    }
                }
            }
        }
        
        return s.substring(start, start+maxLength);
    }
}
