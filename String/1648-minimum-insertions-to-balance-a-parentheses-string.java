class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int insertions = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                open++;
            } else {
                // We need two ')' together
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    // Add one ')' because only one ')' is present
                    insertions++;
                }

                // Match the pair of ')'
                if (open > 0) {
                    open--;
                } else {
                    // No '(' available, so add one '('
                    insertions++;
                }
            }
        }

        // Every remaining '(' needs two ')'
        insertions += open * 2;

        return insertions;
    }
}