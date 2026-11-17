class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();

        // Step 1: Precompute proper divisors
        List<Integer>[] factors = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            factors[i] = new ArrayList<>();
        }

        for (int d = 1; d < n; d++) {
            for (int len = 2 * d; len <= n; len += d) {
                factors[len].add(d);
            }
        }

        // Step 2: Precompute substring costs
        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int len = j - i + 1;
                int best = n;

                for (int d : factors[len]) {
                    int changes = 0;

                    for (int offset = 0; offset < d; offset++) {
                        int l = i + offset;
                        int r = j - d + 1 + offset;

                        while (l < r) {
                            if (s.charAt(l) != s.charAt(r)) {
                                changes++;
                            }

                            l += d;
                            r -= d;
                        }
                    }

                    best = Math.min(best, changes);
                }

                cost[i][j] = best;
            }
        }

        // Step 3: Dynamic Programming
        int INF = 1_000_000_000;

        int[][] dp = new int[n + 1][k + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            for (int i = 2 * p; i <= n; i++) {

                for (int j = 2 * (p - 1); j <= i - 2; j++) {

                    if (dp[j][p - 1] != INF) {
                        dp[i][p] = Math.min(
                            dp[i][p],
                            dp[j][p - 1] + cost[j][i - 1]
                        );
                    }
                }
            }
        }

        return dp[n][k];
    }
}