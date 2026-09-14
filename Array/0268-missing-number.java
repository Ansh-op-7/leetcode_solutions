class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int as = (n * (n + 1)) / 2;
        int ms = 0;
        int i = 0;

        while (i < n) {
            ms = ms + nums[i];
            i++;
        }

        int ans = as - ms;
        return ans;
    }
}