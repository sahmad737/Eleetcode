/* 392. Is Subsequence
Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) 
of the characters without disturbing the 
relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
Input: s = "abc", t = "ahbgdc"
Output: true
Example 2:

Input: s = "axc", t = "ahbgdc"
Output: false
*/

public class isSubsequenceLC392 {
           
    //MY Logic 
   public static boolean isSubsequence(String s, String t) {
    // s.length will be compared with counter of t with ordered matching like a .. b .. c 
        if(s.length()== 0) return true;
        int k=0;//To Store the count of all ordered match 
        char ch ;
        for (int i = 0; i < t.length(); i++) {
            ch = s.charAt(k);
            if(t.charAt(i)==ch) k++;
            if(s.length()==k) return true;
        }

    return false;
    }
    //ENd My Logic


    /*
    Cleaner version
    public static boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) return true;   // s.charAt(k) crashes if s="" — guard first
        int k = 0;
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == s.charAt(k)) {
                k++;
                if (k == s.length()) return true;  // found all chars
            }
        }
        return false;
    } */
    public static void main(String[] args) {

        System.out.println(isSubsequence("abc", "ahcgdb")?"Subsequence":"Not Subsequence");

    }
    
}
