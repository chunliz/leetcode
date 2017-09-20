/*
 Keywords: recursion, order
 use start point 'i=pre' and 'i*i<=n' to control the order
 */

class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result=new ArrayList<List<Integer>>();
        List<Integer> ls=new ArrayList<Integer>();
        helper(result,ls,2,n);
        return result;
    }
    public void helper(List<List<Integer>> result,List<Integer> ls,int pre,int n){
        if(n<4) return;
        for(int i=pre;i*i<=n;i++){
            if(n%i==0){
                ls.add(i);
                ls.add(n/i);
                result.add(new ArrayList<Integer>(ls));
                ls.remove(ls.size()-1);
                helper(result,ls,i,n/i);
                ls.remove(ls.size()-1);
            }
        }
        return;
    }
}
