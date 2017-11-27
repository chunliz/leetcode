class Solution {
    public int findMin(int[] nums) {
        int i=0,j=nums.length-1;
        for(;i<j;){
            if(nums[i]==nums[j]) j--;
            else if(nums[i]<nums[j]) return nums[i];
            else i++;
        }
        return nums[i];
    }
}