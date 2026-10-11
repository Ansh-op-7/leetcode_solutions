class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int ansh = 0;

        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                ansh += nums[i - 1] * nums[i - 1];
            }
        }

        return ansh;
    }
}