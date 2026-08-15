class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] ans = new int[n];
        int i = 0;
        while (i < n) {
            int count = 0;
            int j = 0;
            while (j <= i) {
                int k = 0;
                while (k <= i) {
                    if (A[j] == B[k]) {
                        count++;
                        break;
                    }
                    k++;
                }
                j++;
            }
            ans[i] = count;
            i++;
        }
        return ans;
    }
}