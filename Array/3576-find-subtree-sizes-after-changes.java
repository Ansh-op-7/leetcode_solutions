class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        // Build original tree
        List<Integer>[] original = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            original[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            original[parent[i]].add(i);
        }

        // Stacks for characters a-z
        List<Integer>[] stacks = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            stacks[i] = new ArrayList<>();
        }

        int[] newParent = new int[n];
        newParent[0] = -1;

        // Iterative DFS using enter/exit events
        Stack<int[]> events = new Stack<>();
        events.push(new int[]{0, 0}); // node, event type

        while (!events.isEmpty()) {
            int[] event = events.pop();
            int node = event[0];
            int type = event[1];

            int ch = s.charAt(node) - 'a';

            if (type == 0) { // Enter node

                // Find nearest ancestor with same character
                if (!stacks[ch].isEmpty()) {
                    newParent[node] = stacks[ch].get(
                        stacks[ch].size() - 1
                    );
                } else {
                    newParent[node] = parent[node];
                }

                stacks[ch].add(node);

                // Exit event after processing children
                events.push(new int[]{node, 1});

                // Process original children
                for (int i = original[node].size() - 1; i >= 0; i--) {
                    events.push(new int[]{original[node].get(i), 0});
                }

            } else { // Exit node
                stacks[ch].remove(stacks[ch].size() - 1);
            }
        }

        // Build modified tree
        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[newParent[i]].add(i);
        }

        // Get traversal order
        List<Integer> order = new ArrayList<>();
        Stack<Integer> st = new Stack<>();
        st.push(0);

        while (!st.isEmpty()) {
            int node = st.pop();
            order.add(node);

            for (int child : tree[node]) {
                st.push(child);
            }
        }

        // Calculate subtree sizes in reverse order
        int[] size = new int[n];
        Arrays.fill(size, 1);

        for (int i = order.size() - 1; i >= 0; i--) {
            int node = order.get(i);

            if (newParent[node] != -1) {
                size[newParent[node]] += size[node];
            }
        }

        return size;
    }
}