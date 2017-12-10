/*
 Keywords: graph, BFS, queue
 Explanation: record the time arriving each node and update it when we find it can be reached earlier.
 */
import javafx.util.Pair;
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer,List<Pair<Integer,Integer>>> map=new HashMap<>();
        int[] tm=new int[110];
        Arrays.fill(tm,(int)1e9);
        
        for(int[] a:times){
            if(map.containsKey(a[0])) map.get(a[0]).add(new Pair(a[1],a[2]));
            else{
                List<Pair<Integer,Integer>> ls=new ArrayList<>();
                ls.add(new Pair(a[1],a[2]));
                map.put(a[0],ls);
            }
        }
        tm[K]=0;
        Queue<Integer> q=new LinkedList<>();
        boolean[] iq=new boolean[110];
        Arrays.fill(iq,false);
        
        q.offer(K);
        iq[K]=true;
        
        while(!q.isEmpty()){
            int top=q.poll();
            //System.out.println(top);
            iq[top]=false;
            if(!map.containsKey(top)) continue;
            for(Pair<Integer,Integer> p:map.get(top)){
                int d=p.getKey();
                int t=p.getValue();
                int nt=tm[top]+t;
                if(nt<tm[d]){
                    tm[d]=nt;
                    if(!iq[d]){
                        iq[d]=true;
                        q.offer(d);
                    }
                }
            }
        }
        int result=0;
        for(int i=1;i<=N;i++){
            if(tm[i]>=1e9) return -1;
            result=Math.max(result,tm[i]);
        }
        return result;
    }
}