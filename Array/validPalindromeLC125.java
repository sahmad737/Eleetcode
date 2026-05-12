/*LC125 valid Palin
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters 
and removing all non-alphanumeric characters, it reads the same forward and backward. 
Alphanumeric characters include letters and numbers.
Example 1:
Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
Example 2:
Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
Example 3:
Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.
*/


public class validPalindromeLC125 {
    //MY Approach Start
        public static boolean isPalindrome(String s) {
            if(s.length()==0)return true;
            s= s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            int i=0,j = s.length()-1;
            while (i<j) {
                if(s.charAt(i) != s.charAt(j)) return false;
                i++;j--;
            }
            return true;
    }
    //My Approach End 

    //Optimal AI approach -- 2ms 
    //Skip Junk -- will skip all the junk character and compare real alphanumeric

        public boolean isPalindromeAI(String s) {
int i = 0, j = s.length() - 1;
while (i < j) {
    while (i < j && !Character.isLetterOrDigit(s.charAt(i))) i++;  // skip junk left keep skipping till character that is why loop "  a"
    while (i < j && !Character.isLetterOrDigit(s.charAt(j))) j--;  // skip junk right
    if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
        return false;
    i++; j--; //On successful check check next
}
return true;
    }

    public static void main(String[] args) {
        System.out.println(validPalindromeLC125.isPalindrome("Race  car")?"Palindrome":"Not a palind");
    }   
}
