class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]==i+1) continue;
            while(nums[nums[i]-1]!=nums[i]){
                int temp=nums[i];
                nums[i]=nums[temp-1];
                nums[temp-1]=temp;
            }
            if(nums[i]!=i+1) set.add(nums[i]);
        }
        return new ArrayList<Integer>(set);
    }
}