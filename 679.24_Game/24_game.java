/*
 brute force:
 take each pair of elements, replace them with all the possible operation result; repeat until there is only one element left.
 
 references:
 https://discuss.leetcode.com/topic/103962/java-easy-to-understand-backtracking/3
*/

class Solution {
    boolean res=false;
    final double eps=0.001;
    // brute force
    public boolean judgePoint24(int[] nums) {
        List<Double> l=new ArrayList<>();
        for(int i:nums) l.add((double) i);
        helper(l);
        return res;
    }
    
    public void helper(List<Double> l){
        if(res==true) return;
        if(l.size()==1){
            if(Math.abs(l.get(0)-24.0)<eps) res=true;
            return;
        }
        for(int i=0;i<l.size();i++){
            for(int j=0;j<i;j++){
                List<Double> l1=new ArrayList<>();
                Double d1=l.get(i),d2=l.get(j);
                l1.addAll(Arrays.asList(d1+d2,d1-d2,d2-d1,d1*d2));
                if(Math.abs(d1)>eps) l1.add(d2/d1);
                if(Math.abs(d2)>eps) l1.add(d1/d2);
                l.remove(i);
                l.remove(j);
                for(Double d:l1){
                    l.add(d);
                    helper(l);
                    l.remove(l.size()-1);
                }
                l.add(j,d2);
                l.add(i,d1);
            }
        }
        return;
    }
}
