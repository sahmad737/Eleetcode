/*You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.
Increment the large integer by one and return the resulting array of digits.
Input: digits = [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
Incrementing by one gives 4321 + 1 = 4322.
Thus, the result should be [4,3,2,2].
 */

public class PlusOneLC66 {
        public static int[] plusOne(int[] digits) {
     

 // Traverse from last digit
    for (int i = digits.length - 1; i >= 0; i--) {

        if (digits[i] < 9) {
            digits[i]++;      // no carry
            return digits;
        }

        // digit == 9
        digits[i] = 0;        // carry forward
    }

    // If we are here, all digits were 9
    int[] result = new int[digits.length + 1];
    result[0] = 1;
    return result;

    }
//basically traversing in reverse making every 9 as 0 then as soon as you find non 9 you increase by one making 19 as 20 199 as 200 


    public static void main(String[] args) {
        int a[] = {4,3,2,1};
        for (int i : plusOne(a)) {
            System.out.println(i);
            
        }
    }
}
