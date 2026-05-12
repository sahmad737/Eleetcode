//LC1 TwoSum 

import java.util.HashMap;
import java.util.Map;

public class TwoSumLC1 {
        public int[] twoSum(int[] nums,int target){
// {2,7,11,15} 
Map<Integer, Integer> idx = new HashMap<>(nums.length * 2);
    for (int i = 0; i < nums.length; i++) {
        int need = target - nums[i];
        if (idx.containsKey(need)) {
            return new int[]{idx.get(need), i};
        }
        idx.put(nums[i], i);
    }
    return new int[]{-1, -1}; // if not guaranteed

    }

    public int[] twoSumMW(int[] nums, int target) {
    if (nums == null || nums.length < 2) return new int[]{-1, -1};

    int n = nums.length;
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            if (nums[i] + nums[j] == target) {
                return new int[]{i, j};
            }
        }
    }
    return new int[]{-1, -1};
}
}
