/*Input: nums = [3,0,1]
Output: 2
Explanation:
n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums. */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class missingNumberLC268 {
    public static int  missingNumber(int[] nums){ //8ms Not acceptable but yeah

            // if (!(list.contains(i))) {//This fails at [0,1] as num = 2 and 0->2 then 2 missing //This also cause linear search making it n2 

int n = nums.length;
    Set<Integer> set = new HashSet<>();

    for (int num : nums) {
        set.add(num);
    }

    for (int i = 0; i <= n; i++) {
        if (!set.contains(i)) {
            return i;
        }
    }
    return -1;


    /*
Arrays.sort(nums);

for (int i = 0; i < nums.length; i++) {
    if (nums[i] != i) {
        return i;
    }
}
return nums.length; this was where i was lacking. 
 */

    }


public static int missingNumber1(int[] nums){
//0ms, in this if we consider all the sum of 0 to n it will be n*n+1 / 2 then we can just count actual sum and subtract    
    int n = nums.length;
    int expected = n * (n + 1) / 2;

    int actual = 0;
    for (int num : nums) {
        actual += num;
    }

    return expected - actual;

}
public static void main(String[] args) {
    // int a[] = {3,0,1};
    int a[] = {8,6,4,2,3,5,7,0,1};
    System.out.println(missingNumber(a));
}
}
