/*
 Keywords: backtracking
 */

class Solution {
    int ans;
    public int countArrangement(int N) {
        Set<Integer> set=new HashSet<>();
        ans=0;
        helper(N, 1, set);
        return ans;
    }
    
    void helper(int N, int pos, Set<Integer> set){
        if(pos == N+1){
            ans++;
            return;
        }
        for(int i=1;i<=N;i++){
            if((i % pos==0 || pos % i ==0) && !set.contains(i)){
                set.add(i);
                helper(N, pos+1, set);
                set.remove(i);
            }
        }
    }
}