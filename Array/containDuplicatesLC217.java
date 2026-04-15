import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class containDuplicatesLC217 {

    // public boolean containDuplicates(int [] nums){
    //     if (nums == null || nums.length < 2) return false;

    // Arrays.sort(nums);                // O(n log n)
    // for (int i = 0; i < nums.length-1; i++) {
    //     if (nums[i] == nums[i + 1]) { // duplicates become neighbors
    //         return true;
    //     }
    // }
    // return false;
    // } this takes lots of time

    public boolean containDuplicates(int[] nums){
 Set<Integer> seen = new HashSet<>();
        for (int i =0; i< nums.length; i++){
            if (!seen.add(nums[i])){
                return true;
            }
        }
        return false;
    }
}