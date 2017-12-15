/*
 Keywords: queue
 */
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int result=Integer.MAX_VALUE;
        Queue<Integer> q=new LinkedList<>();
        int sum=0;
        for(int i=0;i<nums.length;i++){
            if(sum+nums[i]<s){
                q.add(nums[i]);
                sum+=nums[i];
            } 
            else{
                q.add(nums[i]);
                sum+=nums[i];
                while(sum-q.peek()>=s){
                    int top=q.poll();
                    sum-=top;
                }
                result=Math.min(result,q.size());
            }
        }
        return result==Integer.MAX_VALUE?0:result;
    }
}
