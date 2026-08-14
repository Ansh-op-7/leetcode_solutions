class Solution {
    public int minOperations(int[] nums, int k) {
        int i = 0;
        int n = nums.length;
        int num = 0;
        int sum =0;
        while(i<n){
            sum = sum + nums[i];
            i++;

        }
        if(sum%k==0){
            num = 0;
        }else{
            num = num +(sum%k);
        }return num;
    }
}