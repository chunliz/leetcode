/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode gummy = new ListNode(-1);
        gummy.next = head;
        ListNode node = gummy;
        while(node.next != null) {
            ListNode next = node.next;
            if (next.next == null) break;
            else if (next.next.val != next.val) {
                node = node.next;
            } else {
                while(next.next != null && next.next.val == next.val) {
                    next = next.next;
                }
                node.next = next.next;
            }
        }
        return gummy.next;
    }
}