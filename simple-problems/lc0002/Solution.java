/**
 * @author: orc-dev
 * @update: Jan.7 2024
 * 
 * Thoughts
 *   - This is a typical problem that looks simple at first, but finding 
 *     the most concise solution is not easy.
 */
class Solution {
    public ListNode addTwoNumbers(ListNode a, ListNode b) {
        return add(a, b, 0);
    }

    // Returns a new ListNode by adding two Linked Lists with carry 'c'
    private ListNode add(ListNode a, ListNode b, int c) {
        if (a == null && b == null && c == 0)
            return null;

        if (a != null) {
            c += a.val;
            a = a.next;
        }

        if (b != null) {
            c += b.val;
            b = b.next;
        }
        return new ListNode(c % 10, add(a, b, c / 10));
    }
}

/**
 * Provided ListNode class
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
