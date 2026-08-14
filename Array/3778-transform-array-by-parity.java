class Solution {
    public int[] transformArray(int[] nums) {
        int i=0;
        int ans[]= new int[nums.length]; 
        while(i < nums.length){
            if(nums[i]%2==0){
                ans[i]=0;
            }else{
                ans[i]=1;
            }
            i++;
            
        }
        Arrays.sort(ans);
        return ans;
    }
}