class Solution {

    class State {
        long score;
        List<Integer> list;

        State(long score, List<Integer> list) {
            this.score = score;
            this.list = list;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        for (int i = 0; i < n; i++) {
            intervals.get(i).add(i);
        }

        intervals.sort((a, b) -> {
            if (!a.get(0).equals(b.get(0))) {
                return a.get(0) - b.get(0);
            }
            return a.get(1) - b.get(1);
        });

        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int end = intervals.get(i).get(1);
            int left = i + 1;
            int right = n;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (intervals.get(mid).get(0) > end) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            next[i] = left;
        }

        State[][] dp = new State[n + 1][5];

        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new State(0, new ArrayList<>());
        }

        for (int i = n - 1; i >= 0; i--) {
            dp[i][0] = new State(0, new ArrayList<>());

            for (int k = 1; k <= 4; k++) {
                State skip = dp[i + 1][k];
                State after = dp[next[i]][k - 1];

                List<Integer> takeList = new ArrayList<>();
                takeList.add(intervals.get(i).get(3));
                takeList.addAll(after.list);
                Collections.sort(takeList);

                State take = new State(
                    intervals.get(i).get(2) + after.score,
                    takeList
                );

                dp[i][k] = better(skip, take);
            }
        }

        List<Integer> answer = dp[0][4].list;
        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    private State better(State a, State b) {
        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        return compareLists(a.list, b.list) <= 0 ? a : b;
    }

    private int compareLists(List<Integer> a, List<Integer> b) {
        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) - b.get(i);
            }
        }

        return a.size() - b.size();
    }
}