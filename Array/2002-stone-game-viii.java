class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        // Convert stones into prefix sums
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }

        // Initially, taking all stones
        int dp = stones[n - 1];

        // Work backwards
        for (int i = n - 2; i > 0; i--) {
            dp = Math.max(dp, stones[i] - dp);
        }

        return dp;
    }
}