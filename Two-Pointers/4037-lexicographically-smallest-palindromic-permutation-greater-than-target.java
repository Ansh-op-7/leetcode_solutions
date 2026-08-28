class Solution {

    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // A palindrome can have at most one odd frequency
        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) return "";

        // Count for left half
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        StringBuilder left = new StringBuilder();
        int halfLen = n / 2;

        // Build left half greedily
        for (int pos = 0; pos < halfLen; pos++) {
            boolean found = false;

            for (int c = 0; c < 26; c++) {
                if (halfCount[c] == 0) continue;

                // Try this character
                halfCount[c]--;
                left.append((char) ('a' + c));

                // Check if the maximum possible completion
                // can still be > target
                if (isPossible(left, halfCount, middle, n, target)) {
                    found = true;
                    break;
                }

                // Undo
                left.deleteCharAt(left.length() - 1);
                halfCount[c]++;
            }

            if (!found) return "";
        }

        String ans = buildPalindrome(left, middle, n);

        return ans.compareTo(target) > 0 ? ans : "";
    }

    private boolean isPossible(StringBuilder left, int[] count,
                               char middle, int n, String target) {

        StringBuilder temp = new StringBuilder(left);

        // Add remaining characters in descending order
        // to create lexicographically largest completion
        for (int i = 25; i >= 0; i--) {
            for (int j = 0; j < count[i]; j++) {
                temp.append((char) ('a' + i));
            }
        }

        String palindrome = buildPalindrome(temp, middle, n);

        return palindrome.compareTo(target) > 0;
    }

    private String buildPalindrome(StringBuilder left, char middle, int n) {
        StringBuilder ans = new StringBuilder();

        ans.append(left);

        if (n % 2 == 1) {
            ans.append(middle);
        }

        for (int i = left.length() - 1; i >= 0; i--) {
            ans.append(left.charAt(i));
        }

        return ans.toString();
    }
}