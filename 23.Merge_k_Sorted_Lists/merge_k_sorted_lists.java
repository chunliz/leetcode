/*
 References: 
 https://discuss.leetcode.com/topic/2780/a-java-solution-based-on-priority-queue?page=1
  
 Keyword: PriorityQueue
 Testcase: [], [[]]
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null||lists.length==0) return null;
        PriorityQueue<ListNode> pq=new PriorityQueue<>(lists.length,(a,b)->a.val-b.val);
        for(ListNode l:lists){
            if(l!=null) pq.add(l);
        } 
        
        ListNode dummy=new ListNode(-1);
        ListNode cur=dummy;
        while(!pq.isEmpty()){
            cur.next=pq.poll();
            cur=cur.next;
            if(cur.next!=null) pq.add(cur.next);
        }
        return dummy.next;
    }
}