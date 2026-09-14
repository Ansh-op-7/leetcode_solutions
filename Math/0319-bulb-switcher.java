class Solution {
    public int bulbSwitch(int n) {
        int ansh = 0;

        while ((ansh + 1) * (ansh + 1) <= n) {
            ansh++;
        }

        return ansh;
    }
}