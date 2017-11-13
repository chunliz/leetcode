/*
 Keywords: HashSet
 */
class Solution {
    public int firstMissingPositive(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for(int num:nums) set.add(num);
        int result=1;
        while(set.contains(result)) result++;
        return result;
    }
}