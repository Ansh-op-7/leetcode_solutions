import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int startX = 0;
        int startY = 0;

        // Give every litter an index
        int[][] litter = new int[m][n];
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startX = i;
                    startY = j;
                }

                if (ch == 'L') {
                    litter[i][j] = count;
                    count++;
                }
            }
        }

        // No litter
        if (count == 0) {
            return 0;
        }

        /*
         * mask:
         * 1 = litter still needs to be collected
         * 0 = litter already collected
         */
        int startMask = (1 << count) - 1;

        // visited[row][col][energy][mask]
        boolean[][][][] visited =
            new boolean[m][n][energy + 1][1 << count];

        Queue<int[]> queue = new LinkedList<>();

        // x, y, energy, mask
        queue.add(new int[] {
            startX, startY, energy, startMask
        });

        visited[startX][startY][energy][startMask] = true;

        int moves = 0;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int size = queue.size();

            // Process one BFS level
            for (int s = 0; s < size; s++) {

                int[] current = queue.poll();

                int x = current[0];
                int y = current[1];
                int currentEnergy = current[2];
                int mask = current[3];

                // All litter collected
                if (mask == 0) {
                    return moves;
                }

                // Cannot move without energy
                if (currentEnergy == 0) {
                    continue;
                }

                // Try 4 directions
                for (int k = 0; k < 4; k++) {

                    int nx = x + dx[k];
                    int ny = y + dy[k];

                    // Outside grid
                    if (nx < 0 || nx >= m ||
                        ny < 0 || ny >= n) {
                        continue;
                    }

                    // Obstacle
                    if (classroom[nx].charAt(ny) == 'X') {
                        continue;
                    }

                    // Moving costs 1 energy
                    int newEnergy = currentEnergy - 1;

                    // Reset area
                    if (classroom[nx].charAt(ny) == 'R') {
                        newEnergy = energy;
                    }

                    int newMask = mask;

                    // Litter found
                    if (classroom[nx].charAt(ny) == 'L') {

                        int index = litter[nx][ny];

                        // Mark litter as collected
                        newMask = newMask & ~(1 << index);
                    }

                    // Avoid repeated states
                    if (!visited[nx][ny][newEnergy][newMask]) {

                        visited[nx][ny][newEnergy][newMask] = true;

                        queue.add(new int[] {
                            nx, ny, newEnergy, newMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}