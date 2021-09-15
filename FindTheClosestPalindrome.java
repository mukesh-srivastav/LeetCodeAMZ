/**
 * Given a string n representing an integer, return the closest integer (not including itself), which is a palindrome. If there is a tie, return the smaller one.

The closest is defined as the absolute difference minimized between two integers.

 

Example 1:

Input: n = "123"
Output: "121"
Example 2:

Input: n = "1"
Output: "0"
Explanation: 0 and 2 are the closest palindromes but we return the smallest which is 0.
 

Constraints:

1 <= n.length <= 18
n consists of only digits.
n does not have leading zeros.
n is representing an integer in the range [1, 1018 - 1].

Take a palindrome number 12321 for example, the next bigger palindrome should be 12421, and the next smaller one should be 12221.

That is, if we define 123 as palindromeRoot of 12321, the next bigger palindrome number's palindromeRoot ispalindromeRoot + 1 (124), while the next smaller one's palindromeRoot ispalindromeRoot - 1(122).

For palindrome numbers with even digits, e.g., 123321, the change of palindromeRoot follows the same pattern.

The closest palindromic one should be among the palindromic numbers formed by these 2 palindromeRoots.

For numbers which are not palindromic, e.g., 12345, we still focus on the front half of the number, i.e., palindromeRoot as 123 in the example. Except for the bigger one formed by palindromeRoot + 1(124), the smaller one formed by palindromeRoot - 1(122), there should be one more possibility, i.e., the number formed by palindromeRoot (123). We chose the closest one among these 3 palindromic numbers formed.

There are some cases missing the rules above,
case 1. <= 10, OR equal to 100, 1000, 10000, ... We simply decrease n by 1.
case 2. 11, 101, 1001, 10001, 100001, ... We simply decrease n by 2.
case 3. 99, 999, 9999, 99999, ... We simply increase n by 2.
 */
public class FindTheClosestPalindrome {
    public String nearestPalindromic(String n) {
        int l = n.length();
        long nLong = Long.parseLong(n);
        
        // for numbers like - 10, 100, 1000
        if (nLong <= 10 || (nLong%10 == 0 && Long.valueOf(n.substring(1)) == 0 && n.charAt(0) == '1')) {
            return "" + (nLong - 1);
        }
        
        // for 11, 101, 1001, 10001 etc
        if (nLong == 11 || (l >= 3 && n.charAt(0) == '1' && nLong % 10 == 1 && Long.valueOf(n.substring(1, l - 1)) == 0)) {
            return "" + (nLong - 2);
        }
        
        // For 99, 999, 9999 
        if (allNineDigits(n)) {
            return "" + (nLong + 2);
        }
        //
        // Construct the closest palindrome and calculate absolute difference with n
        //
        boolean isEvenLength = (l%2 == 0); 
        
        long palindromicRoot = (isEvenLength) ? Long.valueOf(n.substring(0, l/2)) : Long.valueOf(n.substring(0, l/2 +1));
        
        // This will give substring like for 12345 -> 12312, 12212, 12412. 1234 -> 1212, 1331, 1111.
        String equal = createPalindrome("" + palindromicRoot, isEvenLength); 
        String bigger = createPalindrome("" + (palindromicRoot + 1), isEvenLength);
        String smaller = createPalindrome("" + (palindromicRoot - 1), isEvenLength);
        
        long equalDiff = Math.abs(nLong - Long.valueOf(equal));
        long biggerDiff = Math.abs(nLong - Long.valueOf(bigger));
        long smallerDiff = Math.abs(nLong - Long.valueOf(smaller));
        
        //
        // Find the palindrome with minimum absolute differences
        // If tie, return the smaller one
        //
        String closest = (biggerDiff < smallerDiff) ? bigger : smaller;
        long minDiff = Math.min(biggerDiff, smallerDiff);
        
        if (equalDiff != 0) { // n is not a palindrome, diffEqual should be considered
            if (equalDiff == minDiff) { // if tie
                closest = "" + Math.min(Long.valueOf(equal), Long.valueOf(closest));
            } else if (equalDiff < minDiff) {
                closest = equal;
            }
        }
        
        return "" + closest;
    }
    
    private String createPalindrome(String n, boolean isEvenLength) {
        StringBuilder reverse = (new StringBuilder(n)).reverse();
        
        String palindrome = n + ((isEvenLength) ? reverse.toString() : (reverse.deleteCharAt(0)).toString());
        
        return palindrome;
    }
    
    private boolean allNineDigits(String n) {
         for (char c : n.toCharArray()) {
             if (c != '9') {
                 return false;
             }
         }
        
        return true;
    }
}
