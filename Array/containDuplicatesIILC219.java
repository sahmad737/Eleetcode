/*Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
Example 2:


indices difference <= k
Input: nums = [1,0,1,1], k = 1
Output: true
Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false
 
  */

import java.util.HashSet;

// public class containDuplicatesIILC219 {
//         public boolean containsNearbyDuplicateBF(int[] nums, int k) { //TLE
//         for (int i = 0; i < nums.length; i++) {
//           for (int j = i+1 ; j < nums.length; j++) {
//               if (nums[i]==nums[j] && Math.abs((i-j))<=k) {System.out.println("i= "+i+ " j= "+j); return true; 
                
//               }
//           }
//         }
//         return false;
//     }

    //This is sliding window appraoch kinda where we are checking for range and in num
    public boolean containsNearbyDuplicate(int[] nums,int k){

 HashSet<Integer> set = new HashSet<>();

    for (int i = 0; i < nums.length; i++) {

        // If current element already exists in the window
        if (set.contains(nums[i])) {
            return true;
        }

        // Add current element to the window
        set.add(nums[i]);

        // Maintain window size of at most k
        if (set.size() > k) {
            set.remove(nums[i - k]);
        }
    }
    return false;

    }

    public static void main(String[] args) {
      
    }
}
