class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int min = Integer.MAX_VALUE;
        int max = 0;

        int first = -1;
        int last = -1;

        int index = 1;

        ListNode prev = head;
        ListNode curr = head.next;

        while (curr.next != null) {

            // Check if curr is a critical point
            if ((curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val)) {

                // First critical point
                if (first == -1) {
                    first = index;
                    last = index;
                } 
                else {
                    // Distance from previous critical point
                    min = Math.min(min, index - last);

                    // Distance from first critical point
                    max = index - first;

                    last = index;
                }
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        // Fewer than 2 critical points
        if (first == last) {
            return new int[]{-1, -1};
        }

        return new int[]{min, max};
    }
}