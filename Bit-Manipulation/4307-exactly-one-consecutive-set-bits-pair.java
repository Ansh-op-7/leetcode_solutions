class Solution {
    public boolean consecutiveSetBits(int n) {
        int count = 0;

        for (int i = 0; i < 31; i++) {
            if ((n & (1 << i)) != 0 && (n & (1 << (i + 1))) != 0) {
                count++;
            }
        }

        return count == 1;
    }
}