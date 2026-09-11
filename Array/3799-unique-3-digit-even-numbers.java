class Solution {
    public int totalNumbers(int[] digits) {
        Set<Integer> set = new HashSet<>();

        int i = 0;

        while (i < digits.length) {

            if (digits[i] == 0) {
                i++;
                continue;
            }

            int j = 0;

            while (j < digits.length) {

                if (j == i) {
                    j++;
                    continue;
                }

                int k = 0;

                while (k < digits.length) {

                    if (k == i || k == j) {
                        k++;
                        continue;
                    }
                    if (digits[k] % 2 == 0) {
                        int num = digits[i] * 100
                                + digits[j] * 10
                                + digits[k];

                        set.add(num);
                    }

                    k++;
                }

                j++;
            }

            i++;
        }

        return set.size();
    }
}