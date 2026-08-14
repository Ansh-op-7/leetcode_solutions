class Solution {
    public int minimumOperations(int[] nums) {
      int i = 0;
      int a = 0;
      while(i<nums.length){
        if(nums[i]%3==0){
            a = a + 0;
        }else{
            a++;
        }i++;
      }return a; 
    }
}