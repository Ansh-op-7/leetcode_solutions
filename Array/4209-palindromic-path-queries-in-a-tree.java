import java.util.*;

class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        int[] parent = new int[n];
        int[] depth = new int[n];
        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] order = new int[n];
        int[] iter = new int[n];
        int[] stack = new int[n];

        Arrays.fill(parent, -1);

        int timer = 0, top = 0;
        stack[0] = 0;

        while (top >= 0) {
            int u = stack[top];

            if (iter[u] == 0) {
                tin[u] = timer;
                order[timer++] = u;
            }

            if (iter[u] < adj[u].size()) {
                int v = adj[u].get(iter[u]++);
                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[++top] = v;
            } else {
                tout[u] = timer - 1;
                top--;
            }
        }

        int LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        int[][] up = new int[LOG][n];

        for (int i = 0; i < n; i++) {
            up[0][i] = parent[i] == -1 ? i : parent[i];
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        char[] chars = s.toCharArray();
        int[] prefix = new int[n];

        for (int u : order) {
            prefix[u] = 1 << (chars[u] - 'a');
            if (parent[u] != -1) prefix[u] ^= prefix[parent[u]];
        }

        Fenwick fw = new Fenwick(n);

        for (int i = 0; i < n; i++) {
            int u = order[i];
            int delta = prefix[u];
            if (i > 0) delta ^= prefix[order[i - 1]];
            fw.update(i, delta);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");

            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);

                int delta = (1 << (chars[u] - 'a')) ^ (1 << (c - 'a'));

                if (delta != 0) {
                    fw.rangeXor(tin[u], tout[u], delta);
                    chars[u] = c;
                }
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);

                int a = lca(u, v, depth, up);

                int mask = fw.query(tin[u]) ^ fw.query(tin[v])
                        ^ (1 << (chars[a] - 'a'));

                ans.add((mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }

    private int lca(int u, int v, int[] depth, int[][] up) {
        if (depth[u] < depth[v]) {
            int t = u;
            u = v;
            v = t;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < up.length; k++) {
            if ((diff & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) return u;

        for (int k = up.length - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return up[0][u];
    }

    static class Fenwick {
        int n;
        int[] bit;

        Fenwick(int n) {
            this.n = n;
            bit = new int[n + 1];
        }

        void update(int i, int val) {
            for (i++; i <= n; i += i & -i) {
                bit[i] ^= val;
            }
        }

        int query(int i) {
            int res = 0;
            for (i++; i > 0; i -= i & -i) {
                res ^= bit[i];
            }
            return res;
        }

        void rangeXor(int l, int r, int val) {
            update(l, val);
            if (r + 1 < n) update(r + 1, val);
        }
    }
}