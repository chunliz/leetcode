/*
 1. days[i]=y: the (i+1)th flower blooms at yth day;
 2. at some certain day, ith and (i+k+1)th flowers are blooming, but flowers between them are not blooming, means that days[j] with j~(i,i+k+1) are larger than days[i] and days[i+k+1].
 3. The goal is to find i that fulfils the above condition.
 
 References:
 https://discuss.leetcode.com/topic/104771/java-c-simple-o-n-solution
*/

class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        int[] days=new int[flowers.length];
        for(int i=0;i<flowers.length;i++) days[flowers[i]-1]=i+1;
        int left=0,right=k+1;
        int result=Integer.MAX_VALUE;
        for(int i=1;right<flowers.length;i++){
            if(days[i]<=days[left] || days[i]<=days[right]){
                if(i==right) result=Math.min(result,Math.max(days[left],days[right]));
                left=i;
                right=i+k+1;
            }
        }
        return result==Integer.MAX_VALUE?-1:result;
    }
}
