/**
 * Convert a non-negative integer num to its English words representation.

 

Example 1:

Input: num = 123
Output: "One Hundred Twenty Three"
Example 2:

Input: num = 12345
Output: "Twelve Thousand Three Hundred Forty Five"
Example 3:

Input: num = 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Example 4:

Input: num = 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 

Constraints:

0 <= num <= 231 - 1
 */
public class IntegerToWords {
        //index: 0-18, value = 1-19
    String[] lessTwenty = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", 
   "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"}; 
    String[] greaterTwenty = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" }; 

    String[] units = {" Billion ", " Million ", " Thousand "};
    int[] values = {1000000000, 1000000, 1000};

    public String numberToWords(int num)
    {
        StringBuilder builder = new StringBuilder();
        if (num == 0) 
            return "Zero";

        for(int i =0; i< units.length; i++)
        {
            int uni = values[i];
            String digit = units[i];
            if(num >= uni)
            {
                builder.append(convertIntToString(num/uni));
                builder.append(digit);
                //builder.append(" ");
                num %= uni;
            }
        }
        builder.append(convertIntToString(num));

        return builder.toString().trim();
    }

    public String convertIntToString(int num)
    {
        StringBuilder builder = new StringBuilder();
        if(num<= 0)
         return "";
        if (num <= 19) {
            builder.append(lessTwenty[num-1]); //start from 0
        } else if (num <100 && num> 19) {
            builder.append(greaterTwenty[num/10 - 2]); //20/10 = 2 -2 (start from 0)
            if(num%10 > 0){
                builder.append(" ");
                builder.append(convertIntToString(num%10));
            }
        } else if(num > 99) {
            builder.append(convertIntToString(num/100)); //get hundred value only
            builder.append(" Hundred");
            if(num%100 > 0){
                builder.append(" ");
                builder.append(convertIntToString(num%100));
            }
        }

        return builder.toString();
    }
}
