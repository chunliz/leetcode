class Solution {
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> pq = new PriorityQueue<>();     
        pq.add(1L);
        long result = 0L;
        for (int i = 1; i <= n; i += 1) {
            while(pq.peek() <= result) pq.poll();
            result = pq.poll();
            pq.add(result*2);
            pq.add(result*3);
            pq.add(result*5);
        }
        return (int) result;
    }
}