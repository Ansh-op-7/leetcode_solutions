class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        int[][] states = new int[n + 1][26];
        System.arraycopy(count, 0, states[0], 0, 26);

        int matched = 0;
        for (int i = 0; i < n; i++) {
            int ch = target.charAt(i) - 'a';

            if (count[ch] == 0) {
                break;
            }

            count[ch]--;
            matched++;

            System.arraycopy(count, 0, states[i + 1], 0, 26);
        }
        for (int i = matched; i >= 0; i--) {

            int[] freq = states[i].clone();

            if (i < n) {
                int current = target.charAt(i) - 'a';
                for (int c = current + 1; c < 26; c++) {
                    if (freq[c] > 0) {
                        StringBuilder ans = new StringBuilder();
                        ans.append(target, 0, i);
                        ans.append((char) ('a' + c));
                        freq[c]--;
                        for (int j = 0; j < 26; j++) {
                            while (freq[j]-- > 0) {
                                ans.append((char) ('a' + j));
                            }
                        }

                        return ans.toString();
                    }
                }
            }
        }

        return "";
    }
}