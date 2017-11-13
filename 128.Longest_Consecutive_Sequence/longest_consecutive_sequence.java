/*
 Keywords: HashSet
 */
class Solution {
    public int longestConsecutive(int[] nums) {
        int result=0;
        Set<Integer> set=new HashSet<>();
        for(int num:nums) set.add(num);
        for(int num:nums){
            if(set.contains(num)){
                set.remove(num);
                int left=num-1,right=num+1;
                while(set.contains(left)){
                    set.remove(left);
                    left--;
                } 
                while(set.contains(right)){
                    set.remove(right);
                    right++;
                }
                result=Math.max(result,right-left-1);
            }
        }
        return result;
    }
}