class Solution {
    public int smallestIndex(int[] nums) {

        int n = nums.length;
        int i = 0;

        while (i < n) {
            int num = nums[i];
            int sum = 0;

            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }

            if (sum == i) {
                return i;
            }

            i++;
        }

        return -1;
    }
}