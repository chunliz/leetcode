class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length<3) return nums.length;
        int[] A=new int[nums.length];
        A[0]=nums[0];
        A[1]=nums[1];
        int idx=2;
        for(int j=2;j<nums.length;){
            if(nums[j]==nums[j-2]){
                j++;
                continue;
            }
            A[idx++]=nums[j++];
        }
        System.arraycopy(A,0,nums,0,idx);
        return idx;
    }
}