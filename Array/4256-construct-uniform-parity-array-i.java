class Solution {
    public boolean uniformArray(int[] nums1) {

        int i = 0;

        while (i < nums1.length) {

            if (nums1[i] % 2 == 0) {
                return true;
            }

            i++;
        }

        return true;
    }
}