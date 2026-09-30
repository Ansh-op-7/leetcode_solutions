class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int[] ansh = new int[seq.length()];
        int depth = 0;

        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) == '(') {
                depth++;
                ansh[i] = depth % 2;
            } else {
                ansh[i] = depth % 2;
                depth--;
            }
        }

        return ansh;
    }
}