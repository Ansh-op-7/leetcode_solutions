class Solution {
    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        int[] ansh = new int[26];

        for (int i = 0; i < s.length(); i++) {
            ansh[s.charAt(i) - 'a']++;
            ansh[t.charAt(i) - 'a']--;
        }

        for (int count : ansh) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }
}