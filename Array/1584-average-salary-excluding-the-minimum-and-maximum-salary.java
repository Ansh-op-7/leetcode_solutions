class Solution {
    public double average(int[] salary) {
        int sum = 0;
        int min = salary[0];
        int max = salary[0];

        for (int x : salary) {
            sum += x;

            if (x < min) {
                min = x;
            }

            if (x > max) {
                max = x;
            }
        }

        sum = sum - min - max;

        return (double) sum / (salary.length - 2);
    }
}