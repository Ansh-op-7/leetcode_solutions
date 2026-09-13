class Solution {
    public int[] concatWithReverse(int[] nums) {
        int n = nums.length;
        int[] ansh = new int[2 * n];

        for (int i = 0; i < n; i++) {
            ansh[i] = nums[i];
            ansh[n + i] = nums[n - 1 - i];
        }

        return ansh;
    }
}