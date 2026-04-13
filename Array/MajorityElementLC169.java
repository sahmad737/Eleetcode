/*Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
Input: nums = [3,2,3]
Output: 3
Input: nums = [2,2,1,1,1,2,2]
Output: 2
*/

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

public class MajorityElementLC169 {
        public static int majorityElement(int[] nums) {
        if( nums.length == 1) return nums[0];
                    Arrays.sort(nums);
            int k=0;
            for (int i = 0; i < nums.length-1; i++) {
                if(nums[i]==nums[i+1]){
                    k++;
                    if(k>=nums.length/2) return nums[i];
                }
                else k=0;
            }
            return 0;


            
    // HashMap<Integer, Integer> map = new HashMap<>();

    // for (int num : nums) {
    //     map.put(num, map.getOrDefault(num, 0) + 1);

    //     if (map.get(num) > nums.length / 2) {
    //         return num;
    //     }
    // }
    // return -1; // technically unreachable since majority element exists

        
    }

    // int noDefault(int[] nums){
        
    // HashMap<Integer, Integer> map = new HashMap<>();
    // int n = nums.length;

    // for (int i = 0; i < n; i++) {
    //     int num = nums[i];

    //     if (map.containsKey(num)) {
    //         int currentCount = map.get(num);
    //         map.put(num, currentCount + 1);
    //     } else {
    //         map.put(num, 1);
    //     }

    //     if (map.get(num) > n / 2) {
    //         return num;
    //     }
    // }

    // return -1; // unreachable for LC169

    }

    public static void main(String[] args) {
        int a[] = {3,2,3};
        int b[] = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(b));
    }
}
