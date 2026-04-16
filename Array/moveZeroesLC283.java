/*Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
Note that you must do this in-place without making a copy of the array.

Example 1:

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0] */

public class moveZeroesLC283 {

        public static void moveZeroesBF(int[] nums) {
            // int ar[] = new int[nums.length];
            int k=0;
            for (int i = 0; i < nums.length; i++) {
                if(nums[i]!=0) nums[k++] = nums[i];
            }

            for(int i=k;i<nums.length;i++){
                nums[i] = 0;
            }

            System.out.println(java.util.Arrays.toString(nums));
    }

    public static void main(String[] args) {
        int a[] = {0,1,0,3,12};
        moveZeroesBF(a);
    }

}
