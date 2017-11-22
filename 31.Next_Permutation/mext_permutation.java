class Solution {
    public void nextPermutation(int[] nums) {
        if(nums.length<2) return;
        for(int i=nums.length-2;i>=0;i--){
            if(nums[i]<nums[i+1]){
                int right=nums.length-1;
                while(i<right){
                    if(nums[i]<nums[right]){
                        int temp=nums[i];
                        nums[i]=nums[right];
                        nums[right]=temp;
                        // reverse subarray in the range (i+1, end)
                        int start=i+1,end=nums.length-1;
                        while(start<end){
                            int tmp1=nums[start];
                            nums[start]=nums[end];
                            nums[end]=tmp1;
                            start++;
                            end--;
                        }
                        return;
                    }
                    right--;
                }
            }
        }
        // reverse the whole array
        int start=0,end=nums.length-1;
        while(start<end){
            int tmp1=nums[start];
            nums[start]=nums[end];
            nums[end]=tmp1;
            start++;
            end--;
        }
        return;
    }
}