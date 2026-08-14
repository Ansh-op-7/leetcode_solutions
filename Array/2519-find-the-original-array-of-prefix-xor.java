class Solution {
    public int[] findArray(int[] pref) {
        int[] ans = new int[pref.length];
        ans[0] = pref[0];
        int i = 1;
        while (i < pref.length) {
            ans[i] = pref[i] ^ pref[i - 1];
            i++;
        }
        return ans;
    }
}