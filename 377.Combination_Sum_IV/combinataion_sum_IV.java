/*
 follow up: 
 what if negative numbers are allowed?
 A: then the line: if(nums[i] > target) continue; will fail
 How does it change the problem? 
 A: may get infinite loop
 What limitation we need to add to the question to allow negative numbers?
 A: each number can only be used at most once 
 */
class Solution {
    public int combinationSum4(int[] nums, int target) {
        return dfs(nums, target, new HashMap<Integer, Integer>());
    }
    
    private int dfs(int[] nums, int target, Map<Integer, Integer> map) {
        if (target == 0) return 1;
        if (map.containsKey(target)) return map.get(target);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > target) continue;
            res += dfs(nums, target-nums[i], map);
        }
        map.put(target, res);
        return res;
    }
}