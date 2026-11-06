
import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int m = queries.length;
        int[][] sortedQueries = new int[m][2];

        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        Arrays.sort(sortedQueries, (a, b) ->
            Integer.compare(a[0], b[0]));

        int[] ans = new int[m];
        int[] count = new int[n + 1];

        int left = 0, right = 0;
        int activeServers = 0;

        for (int[] q : sortedQueries) {

            int t = q[0];
            int start = t - x;

            // Add logs within the query's time window
            while (right < logs.length && logs[right][1] <= t) {
                int server = logs[right][0];

                if (count[server] == 0) {
                    activeServers++;
                }

                count[server]++;
                right++;
            }

            // Remove logs before the window
            while (left < right && logs[left][1] < start) {
                int server = logs[left][0];

                count[server]--;

                if (count[server] == 0) {
                    activeServers--;
                }

                left++;
            }

            ans[q[1]] = n - activeServers;
        }

        return ans;
    }
}
