/*LC 344 Reverse
Write a function that reverses a string. The input string is given as an array of characters s.
You must do this by modifying the input array in-place with O(1) extra memory.
Example 1:
Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
Example 2:
Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
*/

public class reverseStringLC344 {

    //My approach Start
    //I know this is a TwoPointer Problem but let me think why is it a two pointer  
    //Swapping easily the two 
    public void reverseString(char[] s) {
           int n=s.length;
        int j=n-1,i=0;
        char temp;
        while (i<j) {
            temp=s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
    //MY Approach End
    
    
}
