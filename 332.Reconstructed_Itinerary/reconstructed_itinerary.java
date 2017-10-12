/*
references:
https://discuss.leetcode.com/topic/36383/share-my-solution

Keywords: HashMap, LinkedList, Eulerian Path 
Make sure all the edges are visited.
 */

class Solution {
    Map<String, PriorityQueue<String>> iter;
    LinkedList<String> path;
    public List<String> findItinerary(String[][] tickets) {
        iter=new HashMap<>();
        path=new LinkedList<>();
        for(int i=0;i<tickets.length;i++){
            if(iter.containsKey(tickets[i][0])) iter.get(tickets[i][0]).add(tickets[i][1]);
            else{
                iter.put(tickets[i][0], new PriorityQueue<>());
                iter.get(tickets[i][0]).add(tickets[i][1]);
            } 
        }
        findPath("JFK");
        return path;
    }
    public void findPath(String departure){
        PriorityQueue<String> arrival=iter.get(departure);
        while(arrival!=null && !arrival.isEmpty()){
            findPath(arrival.poll());
        }
        path.addFirst(departure);
    }
}