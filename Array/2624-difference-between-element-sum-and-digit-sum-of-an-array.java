
class Solution {
    public int differenceOfSum(int[] nums) {
        int s1 = 0;
        int s2 = 0;

        for (int num : nums) {
            s1 += num;

            while (num > 0) {
                s2 += num % 10; 
                num /= 10;
            }
        }

        return Math.abs(s1 - s2);
    }
}
