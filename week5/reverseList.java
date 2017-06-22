//Pointing to the last element of the original list
public class Solution {
    public ListNode reverseList(ListNode head) {
        /* iterative solution */
        ListNode newHead = null;
        ListNode next;
        while (head != null) {
            next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        if(newHead != null)
        System.out.println(newHead.val);
        return newHead;
    }
}