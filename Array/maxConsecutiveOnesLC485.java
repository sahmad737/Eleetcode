public class maxConsecutiveOnesLC485 {
    public static int findMaxConsecutiveOnes(int[] nums){ //2ms MyLogic
        int t=0,k=0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]==0) {
               if(k>t) {t=k;   }
               k=0;
               continue; 
            }

            k++;
            if(k>t) t=k;
        }

        return t;
    }
    //Simplified for some reason this is 3ms maybe due to Math.max
    /*
int max = 0;
int count = 0;

for (int i = 0; i < nums.length; i++) {
    if (nums[i] == 1) {
        count++;
        max = Math.max(max, count);
    } else {
        count = 0;
    }
}
 */


    public static void main(String[] args) {
        // int a[] = {1,1,0,1,1,1};
        int a[] = {1,0,1,1,0,1};
        System.out.println(findMaxConsecutiveOnes(a));
    }
}
