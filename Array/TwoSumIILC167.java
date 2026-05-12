/* Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.

Return the indices of the two numbers index1 and index2, each incremented by one, as an integer array [index1, index2] of length 2.
Example 1:
Input: numbers = [2,7,11,15], target = 9 Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
Example 2:
Input: numbers = [2,3,4], target = 6 Output: [1,3]
Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
Example 3:
Input: numbers = [-1,0], target = -1 Output: [1,2]
Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].
*/
public class TwoSumIILC167 {
    //MYApproach    
    public static int[] twoSum(int[] numbers, int target) {
        //Array is sorted, Two Pointers I have to use, so what I can do is maybe Keep moving pointers until i find the Target(AI Hint) 
        //I should have thought about it, idk why i didnt
    
        int i,j;
        i=0;j=numbers.length-1;

        while(j>=i){
            if (numbers[i]+numbers[j]==target) {
                return new int[]{i+1,j+1};
            }
            else if(numbers[i]+numbers[j]>target){
                j--;
            }
            else{i++;}
        }
        return new int[]{-1, -1}; //Remember This Return Trick 
        //  //will never reach as one solution always there 
}//MYApproach End 

//Pattern -- Two Pointer Maybe , both ends increase and decrease on sum > or < 
public static void main(String[] args) {
    int a[]= {2,7,11,15};
    int target = 9;
    int res[] = TwoSumIILC167.twoSum(a,target);

    for (int i : res) {
        System.out.println(i);
    }
}
}
