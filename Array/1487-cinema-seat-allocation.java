import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

        // Store reserved seats row-wise
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            map.putIfAbsent(row, new HashSet<>());
            map.get(row).add(col);
        }

        // Rows with no reservations can fit 2 families
        int ans = (n - map.size()) * 2;

        // Check rows having reservations
        for (HashSet<Integer> seats : map.values()) {

            boolean left = true;
            boolean middle = true;
            boolean right = true;

            // Check 2,3,4,5
            for (int i = 2; i <= 5; i++) {
                if (seats.contains(i)) {
                    left = false;
                }
            }

            // Check 4,5,6,7
            for (int i = 4; i <= 7; i++) {
                if (seats.contains(i)) {
                    middle = false;
                }
            }

            // Check 6,7,8,9
            for (int i = 6; i <= 9; i++) {
                if (seats.contains(i)) {
                    right = false;
                }
            }

            // Left and right can both be used
            if (left && right) {
                ans += 2;
            }
            // Otherwise, if any block is available
            else if (left || middle || right) {
                ans += 1;
            }
        }

        return ans;
    }
}