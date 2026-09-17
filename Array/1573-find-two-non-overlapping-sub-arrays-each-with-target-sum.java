class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        int ansh = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0;

        for (int i = 0; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            if (sum == target) {
                int len = right - left + 1;

                if (dp[left] != Integer.MAX_VALUE) {
                    ansh = Math.min(ansh, len + dp[left]);
                }

                dp[right + 1] = Math.min(dp[right], len);
            } else {
                dp[right + 1] = dp[right];
            }
        }

        return ansh == Integer.MAX_VALUE ? -1 : ansh;
    }
}