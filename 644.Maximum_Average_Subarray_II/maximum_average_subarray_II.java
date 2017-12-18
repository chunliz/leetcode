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

/*
Method 2:
References: https://discuss.leetcode.com/topic/96131/python-advanced-o-n-solution-convex-hull-window/6
 */

/*
 When we take index j as the end of the subarray, (j-k+1, j-k+2, ..., j) must be included. Now we consider whether we should include A=(0,1,2,...,j-k). Selected from A, we maintain a list {i_1,i_2,i_3...} which fulfils the following condition:
 1. aver(i_1,i_2-1) > aver(i_1,j);
 2. aver(i_n,j-k) > aver(i_(n-1),j-k) for any n.
 
 In each step, we update the list to make it fulfil the above condition, and update the result if needed.
*/
class Solution {
    int[] presum;
    public double findMaxAverage(int[] nums, int k) {
        double result=-1e5;
        presum=new int[nums.length];
        presum[0]=nums[0];
        for(int i=1;i<nums.length;i++) presum[i]=presum[i-1]+nums[i];
        LinkedList<Integer> ll=new LinkedList<>(); //nums[ll.get(ll.size()-1)]
        for(int i=k-1;i<nums.length;i++){
            while(ll.size()>=2 && aver(ll.get(ll.size()-1),i-k)<=aver(ll.get(ll.size()-2),i-k)) ll.pollLast();
            ll.add(i-k+1);
            while(ll.size()>=2 && aver(ll.get(0),ll.get(1)-1)<=aver(ll.get(0),i)) ll.pollFirst();
            result=Math.max(result,aver(ll.get(0),i));
        }
        return result;
    }
    public double aver(int left, int right){
        double result=0.0;
        if(left==0) return (double)presum[right]/(right-left+1.0);
        else return (double)(presum[right]-presum[left-1])/(right-left+1.0);
    }
}