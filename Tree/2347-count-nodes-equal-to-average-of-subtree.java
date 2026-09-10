class Solution {
    int ans = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }

    // returns {sum, count}
    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        // Get sum and count from left subtree
        int[] left = dfs(root.left);

        // Get sum and count from right subtree
        int[] right = dfs(root.right);

        // Current subtree
        int sum = left[0] + right[0] + root.val;
        int count = left[1] + right[1] + 1;

        // Check average
        if (sum / count == root.val) {
            ans++;
        }

        return new int[]{sum, count};
    }
}