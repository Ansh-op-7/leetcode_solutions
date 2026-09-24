class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);

        long total = mass;

        for (int a : asteroids) {
            if (total < a) return false;
            total += a;
        }

        return true;
    }
}