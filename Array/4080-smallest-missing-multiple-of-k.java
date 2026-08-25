class Solution {
    public int missingMultiple(int[] nums, int k) {
        
        HashSet<Integer> set = new HashSet<>();
        
        int i = 0;
        while (i < nums.length) {
            set.add(nums[i]);
            i++;
        }
        
        int multiple = k;
        
        while (set.contains(multiple)) {
            multiple += k;
        }
        
        return multiple;
    }
}