class Solution {
    public boolean increasingTriplet(int[] nums) {
        int f = Integer.MAX_VALUE;
        int s = Integer.MAX_VALUE;
        int t = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= f) {
                f = num;
            } else if (num <= s) {
                s = num;
            } else {
                t= num;
                return true;
            }
        }

        return false;
    }
}