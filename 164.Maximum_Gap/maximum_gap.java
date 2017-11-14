/*
 Keywords: Sort, Bucket Sort, Radix Sort
 */
class Solution {
    public int maximumGap(int[] nums) {
        if(nums.length<2) return 0;
        Arrays.sort(nums);
        int result=0;
        for(int i=1;i<nums.length;i++){
            result=Math.max(result,nums[i]-nums[i-1]);
        }
        return result;
    }
}

/*
 Bucket Sort.
 References: https://discuss.leetcode.com/topic/5999/bucket-sort-java-solution-with-explanation-o-n-time-and-space
 */

class Solution {
    /** bucket sort*/
    public int maximumGap(int[] nums) {
        if(nums.length<2) return 0;
        // Search for the min and max.
        int min=nums[0],max=nums[0];
        for(int num:nums){
            min=Math.min(min,num);
            max=Math.max(max,num);
        }
        
        int gap=(int)Math.ceil((double)(max-min)/(nums.length-1));
        // Create (nums.length-1) buckets, the range of each bucket is [min+(i-1)*gap,min+i*gap).
        int[] minBucket=new int[nums.length-1];
        int[] maxBucket=new int[nums.length-1];
        Arrays.fill(minBucket,Integer.MAX_VALUE);
        Arrays.fill(maxBucket,Integer.MIN_VALUE);
        
        // Distribute all numbers in the corresponding buckets.
        for(int num:nums){
            if(num==min || num==max) continue;
            int idx=(num-min)/gap;
            minBucket[idx]=Math.min(minBucket[idx],num);
            maxBucket[idx]=Math.max(maxBucket[idx],num);
        }
        
        // The result must be no smaller than the gap. Therefore we only need to consider the difference between two buckets (maximum value in one and minimum in another).
        int result=Integer.MIN_VALUE;
        int previous=min;
        for(int i=0;i<nums.length-1;i++){
            if(minBucket[i]==Integer.MAX_VALUE && maxBucket[i]==Integer.MIN_VALUE) continue; // empty
            result=Math.max(result,minBucket[i]-previous);
            previous=maxBucket[i];
        }
        result=Math.max(result,max-previous);
        return result;
    }
}