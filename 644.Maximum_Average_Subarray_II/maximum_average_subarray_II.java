/*
 Keywords: binary search
 References: https://discuss.leetcode.com/topic/96123/java-solution-o-nlogm-binary-search-the-answer/2
 */

class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double min=Integer.MAX_VALUE, max=Integer.MIN_VALUE;
        for(int i:nums){
            min=Math.min(min,i);
            max=Math.max(max,i);
        }
        while(min+0.00001<max){
            double mid=min+(max-min)/2;
            if(smaller(nums,k,mid)) max=mid;
            else min=mid;
        }
        return max;
    }
    
    public boolean smaller(int[] nums, int k, double mid){
        double sum=0,presum=0;
        for(int i=0;i<k;i++) sum+=(nums[i]-mid);
        if(sum>0) return false;
        for(int i=k;i<nums.length;i++){
            sum+=(nums[i]-mid);
            presum+=(nums[i-k]-mid);
            if(presum<0){
                sum-=presum;
                presum=0;
            } 
            if(sum>0) return false;
        }
        return true;
    }
}