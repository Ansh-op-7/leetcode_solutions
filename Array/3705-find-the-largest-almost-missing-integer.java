class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        // Case 1: k == n
        if (k == n) {
            int ans = -1;

            for (int i = 0; i < n; i++) {
                ans = Math.max(ans, nums[i]);
            }

            return ans;
        }

        // Count frequency of every number
        int[] count = new int[51];

        for (int i = 0; i < n; i++) {
            count[nums[i]]++;
        }

        // Case 2: k == 1
        if (k == 1) {
            int ans = -1;

            for (int i = 0; i < n; i++) {
                if (count[nums[i]] == 1) {
                    ans = Math.max(ans, nums[i]);
                }
            }

            return ans;
        }

        // Case 3: 1 < k < n
        int ans = -1;

        if (count[nums[0]] == 1) {
            ans = Math.max(ans, nums[0]);
        }

        if (count[nums[n - 1]] == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }

        return ans;
    }
}