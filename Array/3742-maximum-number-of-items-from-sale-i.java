class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] dp = new int[budget + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            minPrice = Math.min(minPrice, price);

            int cnt = 0;

            for (int[] x : items) {
                if (x[0] % factor == 0) {
                    cnt++;
                }
            }

            for (int j = budget; j >= price; j--) {
                dp[j] = Math.max(dp[j], dp[j - price] + cnt);
            }
        }

        int ansh = 0;

        for (int i = 0; i <= budget; i++) {
            ansh = Math.max(ansh, dp[i] + (budget - i) / minPrice);
        }

        return ansh;
    }
}
