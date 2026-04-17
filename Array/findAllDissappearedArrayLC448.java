/*Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.
Input: nums = [4,3,2,7,8,2,3,1]
Output: [5,6]
Example 2:
*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class findAllDissappearedArrayLC448 {
  public static List<Integer> findDisappearedNumbersSetM(int[] nums) {
        List<Integer> list = new ArrayList<>();
int n = nums.length;
    Set<Integer> set = new HashSet<>();

    for (int num : nums) {
        set.add(num);
    }

    for (int i = 1; i <= n; i++) {
        if (!set.contains(i)) {
            list.add(i);
        }
    }
    return list;    }

    public static List<Integer> findDissapearedNumBooleanMeth(int[] nums){
    int n = nums.length;
    boolean[] seen = new boolean[n + 1]; // index 1..n used
    List<Integer> result = new ArrayList<>();

    // Mark seen numbers
    for (int num : nums) {
        seen[num] = true;
    }

    // Collect numbers that were not seen
    for (int i = 1; i <= n; i++) {
        if (!seen[i]) {
            result.add(i);
        }
    }

    return result;
    }

    public static void main(String[] args) {
        int a[] = {4,3,2,7,8,2,3,1};
        for (int i : findDisappearedNumbersSetM(a)) {
            System.out.println(i);
        }

        System.out.println("Boolean Method");
                for (int i : findDisappearedNumbersSetM(a)) {
            System.out.println(i);
        }
    }


}
