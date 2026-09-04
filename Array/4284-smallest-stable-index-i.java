class Solution {
    public int firstStableIndex(int[] nums, int k) {

        int n = nums.length;

        int i = 0;

        while (i < n) {

            int max = nums[0];
            int j = 1;

            // Find maximum from 0 to i
            while (j <= i) {
                max = Math.max(max, nums[j]);
                j++;
            }

            int min = nums[i];
            j = i + 1;

            // Find minimum from i to n-1
            while (j < n) {
                min = Math.min(min, nums[j]);
                j++;
            }

            // Check stable condition
            if (max - min <= k) {
                return i;
            }

            i++;
        }

        return -1;
    }
}