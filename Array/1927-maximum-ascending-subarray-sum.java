class Solution {
    public int maxAscendingSum(int[] nums) {
        int sum = nums[0];
        int ansh = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }

            ansh = Math.max(ansh, sum);
        }

        return ansh;
    }
}