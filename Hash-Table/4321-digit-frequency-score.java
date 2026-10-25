class Solution {
    public int digitFrequencyScore(int n) {
        int[] f = new int[10];

        for (char digit : String.valueOf(n).toCharArray()) {
            f[digit - '0']++;
        }

        int score = 0;

        for (int i = 0; i < 10; i++) {
            if (f[i] > 0) {
                score += i * f[i];
            }
        }

        return score;
    }
}