class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ansh = new int[n];

        ansh[0] = 1;

        for (int i = 1; i < n; i++) {
            ansh[i] = ansh[i - 1] * nums[i - 1];
        }

        int right = 1;

        for (int i = n - 1; i >= 0; i--) {
            ansh[i] = ansh[i] * right;
            right = right * nums[i];
        }

        return ansh;
    }
}