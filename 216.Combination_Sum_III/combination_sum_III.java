/*
 Keywords: backtracking
 */

class Solution {
    List<List<Integer>> result;
    public List<List<Integer>> combinationSum3(int k, int n) {
        result=new ArrayList<>();
        helper(new ArrayList<>(),n,k,1);
        return result;
    }
    
    public void helper(List<Integer> ls, int n, int k, int start){
        if(ls.size()==k && sum(ls)==n){
            result.add(new ArrayList<>(ls));
            return;
        } 
        for(int i=start;i<=9;i++){
            ls.add(i);
            helper(ls,n,k,i+1);
            ls.remove((Integer)i);
        }
    }
    
    public int sum(List<Integer> ls){
        int ans=0;
        if(ls.isEmpty()) return ans;
        for(int i:ls) ans+=i;
        return ans;
    }
}
