class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1000000007;

        long[] dp = new long[26];

        for (char ch : s.toCharArray()) {
            int index = ch - 'a';

            long sum = 0;

            for (int i = 0; i < 26; i++) {
                sum = (sum + dp[i]) % MOD;
            }

            dp[index] = (sum + 1) % MOD;
        }

        long ans = 0;

        for (long x : dp) {
            ans = (ans + x) % MOD;
        }

        return (int) ans;
    }
}