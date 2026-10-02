import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ansh = new ArrayList<>();
        generate("", 0, 0, n, ansh);
        return ansh;
    }

    void generate(String s, int open, int close, int n, List<String> ansh) {
        if (s.length() == 2 * n) {
            ansh.add(s);
            return;
        }

        if (open < n) {
            generate(s + "(", open + 1, close, n, ansh);
        }

        if (close < open) {
            generate(s + ")", open, close + 1, n, ansh);
        }
    }
}