
import java.util.*;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            int sumA = digitSum(a[0]);
            int sumB = digitSum(b[0]);

            if (sumA != sumB) {
                return sumA - sumB;
            }

            return a[0] - b[0];
        });

        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i][1] != i) {
                swaps++;

                int[] temp = arr[i];
                arr[i] = arr[arr[i][1]];
                arr[temp[1]] = temp;
                i--;
            }
        }

        return swaps;
    }

    private int digitSum(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }
}
