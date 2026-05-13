/* 977. Squares of a Sorted Array
Given an integer array nums sorted in non-decreasing order, 
return an array of the squares of each number sorted in non-decreasing order.

Example 1:
Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
Example 2:
Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]
*/

import java.lang.reflect.Array;
import java.util.Arrays;

public class squareOfSortedArrayLC977 {
    //MY Approach        
    public static int[] sortedSquares(int[] nums) {
    
         for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i]*nums[i];
         }
         Arrays.sort(nums); //LaterAnalysis --This take nlogn so this makes it tuff, also array was already sorted so 
         return nums;    
    }
     //End MY Approach

     //Optimal — Two Pointers fill from back — O(n) time O(n) space
     // Signal: "sorted array" → Two Pointers. Largest squares always at the extremes.
     // Pay O(n) space for result (mandatory output anyway) to avoid O(n log n) re-sort.
     public static int[] sortedSquaresOptimal(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];
    int left = 0, right = n - 1; //Two Pointers at extremes
    int pos = n - 1;              // fill result from the back (largest first)

    while (left <= right) { // Square both ends, place the larger one at pos
        int lsq = nums[left] * nums[left];
        int rsq = nums[right] * nums[right];
        if (lsq > rsq) {
            result[pos--] = lsq;
            left++;
        } else {
            result[pos--] = rsq;
            right--;
        }
    }
    return result;
}
    public static void main(String[] args) {
        int a[] = {-4,-1,0,3,10};
        for (int i : sortedSquares(a)) {
            System.out.println(i);
        }
    }
}
