/****Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.
Consider the number of elements in nums which are not equal to val
Input: nums = [0,1,2,2,3,0,4,2], val = 2
Output: 5, nums = [0,1,4,0,3,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.*/

public class RemoveElemLC27 {
public int removeElement(int[] nums, int val) {
        if(nums == null || nums.length == 0) return 0;

        int  k = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]!=val) nums[k++] = nums[i];
        }
        return k;
    }    
}
