class Solution {
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int lmax = 0;
        int rmax = 0;
        int ansh = 0;

        while (left < right) {
            if (height[left] <= height[right]) {
                if (height[left] >= lmax) {
                    lmax = height[left];
                } else {
                    ansh += lmax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rmax) {
                    rmax = height[right];
                } else {
                    ansh += rmax - height[right];
                }
                right--;
            }
        }

        return ansh;
    }
}