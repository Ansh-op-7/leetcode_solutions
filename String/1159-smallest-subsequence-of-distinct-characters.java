class Solution {
    public String smallestSubsequence(String s) {
        int[] last = new int[26];
        boolean[] seen = new boolean[26];
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (seen[c - 'a']) {
                continue;
            }

            while (!st.isEmpty() && st.peek() > c &&
                   last[st.peek() - 'a'] > i) {
                seen[st.pop() - 'a'] = false;
            }

            st.push(c);
            seen[c - 'a'] = true;
        }

        StringBuilder ans = new StringBuilder();

        for (char c : st) {
            ans.append(c);
        }

        return ans.toString();
    }
}