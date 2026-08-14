class Solution {
    public int finalValueAfterOperations(String[] operations) {
        int x = 0;
        int i = 0;
        while (i < operations.length) {
            if (operations[i].equals("++X") || operations[i].equals("X++")) {
                x++;
            } else {
                x--;
            }
            i++;
        }
        return x;
    }
}