/*
 LinkedList
 */

class HitCounter {
    LinkedList<Integer> ll;
    /** Initialize your data structure here. */
    public HitCounter() {
        ll = new LinkedList<>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        ll.addLast(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while(!ll.isEmpty() && ll.getFirst()<=timestamp-300){
            ll.pollFirst();
        }
        return ll.size();
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */