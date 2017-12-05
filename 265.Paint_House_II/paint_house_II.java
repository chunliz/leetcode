/*
 Keywords: dynamic programming
 */
class Solution {
    public int minCostII(int[][] costs) {
        if(costs.length==0 || costs[0].length==0) return 0;
        int result=Integer.MAX_VALUE;
        int[][] dp=new int[costs.length][costs[0].length];
        for(int i=0;i<costs[0].length;i++){
            dp[0][i]=costs[0][i];
            result=Math.min(result,dp[0][i]);
        } 
        
        for(int i=1;i<costs.length;i++){
            result=Integer.MAX_VALUE;
            for(int j=0;j<costs[0].length;j++){
                dp[i][j]=min(dp,i-1,j)+costs[i][j];
                result=Math.min(result,dp[i][j]);
            }
        }
        return result;
    }
    private int min(int[][] dp, int row,int col){
        int result=Integer.MAX_VALUE;
        for(int i=0;i<dp[0].length;i++){
            if(i!=col) result=Math.min(result,dp[row][i]);
        }
        return result;
    }
}