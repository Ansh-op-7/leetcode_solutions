class Solution {
    public int largestAltitude(int[] gain) {
        int i = 2;
        int n = gain.length;
        int[] ans = new int[n + 1];
        ans[0] = 0;
        ans[1] = gain[0];
        while(i <= n){
                ans[i] = ans[i - 1] + gain[i - 1];
            i++;
        }
        int max = 0;
        for(int k = 1; k <= n; k++){
            if(ans[k] > max){
                max = ans[k];
            }
        }
        return max;
    }
}