/*
 Keywords: DP
 dp[i]: the length of the largest subset with nums[i] as the largest number in the set
 parent[i]: the index of last number of nums[i] in the possible largest subset
 */

class Solution {
    /** DP */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> result=new ArrayList<Integer>();
        if(nums.length==0) return result;
        
        Arrays.sort(nums);
        int[] parent=new int[nums.length];
        int[] dp=new int[nums.length];
        Arrays.fill(parent,-1);
        dp[0]=1;
        int maxlength=1;
        
        for(int i=1;i<nums.length;i++){
            dp[i]=1;
            for(int j=i-1;j>=0;j--){
                if(nums[i]%nums[j]==0 && dp[j]+1>dp[i]){
                    parent[i]=j;
                    dp[i]=dp[j]+1;
                }
            }
            maxlength=Math.max(maxlength,dp[i]);
        }
        
        for(int i=nums.length-1;i>=0;i--){
            if(dp[i]==maxlength){
                int j=i;
                while(j!=-1){
                    result.add(nums[j]);
                    j=parent[j];
                }
                break;
            }
        }
        return result;
    }
}