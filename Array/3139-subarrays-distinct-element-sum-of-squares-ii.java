import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_007L;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(n);
        Map<Integer, Integer> lastSeen = new HashMap<>();

        long answer = 0;

        for (int i = 0; i < n; i++) {
            int previous = lastSeen.getOrDefault(nums[i], -1);

            tree.update(previous + 1, i, 1);
            answer = (answer + tree.sumSquares[1]) % MOD;

            lastSeen.put(nums[i], i);
        }

        return (int) answer;
    }

    private static class SegmentTree {
        int n;
        long[] sum, sumSquares, lazy;

        SegmentTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            sumSquares = new long[4 * n];
            lazy = new long[4 * n];
        }

        void update(int l, int r, long value) {
            update(1, 0, n - 1, l, r, value);
        }

        private void update(int node, int start, int end,
                            int l, int r, long value) {
            if (l <= start && end <= r) {
                apply(node, start, end, value);
                return;
            }

            pushDown(node, start, end);
            int mid = (start + end) / 2;

            if (l <= mid)
                update(node * 2, start, mid, l, r, value);

            if (r > mid)
                update(node * 2 + 1, mid + 1, end, l, r, value);

            sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
            sumSquares[node] =
                (sumSquares[node * 2] + sumSquares[node * 2 + 1]) % MOD;
        }

        private void apply(int node, int start, int end, long value) {
            long length = end - start + 1L;

            sumSquares[node] = (
                sumSquares[node]
                + 2L * value % MOD * sum[node] % MOD
                + value * value % MOD * length % MOD
            ) % MOD;

            sum[node] = (sum[node] + value * length) % MOD;
            lazy[node] = (lazy[node] + value) % MOD;
        }

        private void pushDown(int node, int start, int end) {
            if (lazy[node] == 0 || start == end)
                return;

            int mid = (start + end) / 2;
            long pending = lazy[node];

            apply(node * 2, start, mid, pending);
            apply(node * 2 + 1, mid + 1, end, pending);

            lazy[node] = 0;
        }
    }
}