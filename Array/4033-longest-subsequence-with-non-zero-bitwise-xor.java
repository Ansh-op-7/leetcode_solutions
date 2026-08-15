class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int xor = 0;
        int i = 0;
        boolean nonZero = false;

        while (i < n) {
            xor = xor ^ nums[i];

            if (nums[i] != 0) {
                nonZero = true;
            }

            i++;
        }

        if (xor != 0) {
            return n;
        }

        if (nonZero) {
            return n - 1;
        }

        return 0;
    }
}