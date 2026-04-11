public class RemoveDupFromSortLC26 {
     public static int myremoveDuplicates(int[] nums){
        
   if (nums == null || nums.length == 0) return 0;

    int k = 0; // write index

    // Compare current element with the next one, up to n-2
    for (int i = 0; i < nums.length - 1; i++) {
        if (nums[i] != nums[i + 1]) {
            nums[k++] = nums[i];
        }
    }

    // Don't forget the last element; it’s always part of the unique set
    nums[k++] = nums[nums.length - 1];

    return k;

    }

    public static void main(String[] args) {
        
int a[] = {1, 1, 2};
    int k = myremoveDuplicates(a);
    System.out.println("k = " + k);
    System.out.print("nums (first k elements) = ");
    for (int i = 0; i < k; i++) System.out.print(a[i] + " ");

    }   
}
