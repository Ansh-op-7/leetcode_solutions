
import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        long[] cost = new long[n - x + 1];

        // Two heaps to maintain the median of each window
        PriorityQueue<Integer> left =
            new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> right =
            new PriorityQueue<>();

        long leftSum = 0;
        long rightSum = 0;

        // Lazy deletion
        Map<Integer, Integer> delayed = new HashMap<>();

        // This implementation uses a TreeMap-based multiset
        // instead, to support exact removal.
        TreeMap<Integer, Integer> low = new TreeMap<>();
        TreeMap<Integer, Integer> high = new TreeMap<>();

        long lowSum = 0, highSum = 0;
        int lowSize = 0, highSize = 0;

        for (int i = 0; i < n; i++) {
            int val = nums[i];

            if (low.isEmpty() || val <= low.lastKey()) {
                low.merge(val, 1, Integer::sum);
                lowSum += val;
                lowSize++;
            } else {
                high.merge(val, 1, Integer::sum);
                highSum += val;
                highSize++;
            }

            // Balance the two sets
            while (lowSize > highSize + 1) {
                int v = low.lastKey();
                remove(low, v);
                lowSum -= v;
                lowSize--;

                high.merge(v, 1, Integer::sum);
                highSum += v;
                highSize++;
            }

            while (lowSize < highSize) {
                int v = high.firstKey();
                remove(high, v);
                highSum -= v;
                highSize--;

                low.merge(v, 1, Integer::sum);
                lowSum += v;
                lowSize++;
            }

            // Remove element outside the window
            if (i >= x) {
                int old = nums[i - x];

                if (low.containsKey(old)) {
                    remove(low, old);
                    lowSum -= old;
                    lowSize--;
                } else {
                    remove(high, old);
                    highSum -= old;
                    highSize--;
                }

                // Rebalance after removal
                while (lowSize > highSize + 1) {
                    int v = low.lastKey();
                    remove(low, v);
                    lowSum -= v;
                    lowSize--;

                    high.merge(v, 1, Integer::sum);
                    highSum += v;
                    highSize++;
                }

                while (lowSize < highSize) {
                    int v = high.firstKey();
                    remove(high, v);
                    highSum -= v;
                    highSize--;

                    low.merge(v, 1, Integer::sum);
                    lowSum += v;
                    lowSize++;
                }
            }

            if (i >= x - 1) {
                long median = low.lastKey();

                cost[i - x + 1] =
                    median * lowSize - lowSum
                    + highSum - median * highSize;
            }
        }

        // DP: minimum cost to choose j windows
        // among the first i elements.
        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[k + 1][n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(dp[j], INF);
        }

        for (int j = 1; j <= k; j++) {
            for (int i = 1; i <= n; i++) {
                dp[j][i] = dp[j][i - 1];

                if (i >= x && dp[j - 1][i - x] != INF) {
                    dp[j][i] = Math.min(
                        dp[j][i],
                        dp[j - 1][i - x] + cost[i - x]
                    );
                }
            }
        }

        return dp[k][n];
    }

    private void remove(TreeMap<Integer, Integer> map, int val) {
        int count = map.get(val);

        if (count == 1) {
            map.remove(val);
        } else {
            map.put(val, count - 1);
        }
    }
}
