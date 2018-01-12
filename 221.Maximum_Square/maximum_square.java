/*
 Keywords: DP
 */
class Solution {
    public int maximalSquare(char[][] matrix) {
        
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int row = matrix.length, col = matrix[0].length;
        int result = 0;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i += 1) {
            dp[i][0] = (matrix[i][0] == '0'? 0 : 1);
            result = Math.max(result, dp[i][0]);
        }
        for (int j = 0; j < col; j += 1) {
            dp[0][j] = (matrix[0][j] == '0'? 0 : 1);
            result = Math.max(result, dp[0][j]);
        }
        for (int i = 1; i < row; i += 1) {
            for (int j = 1; j < col; j += 1) {
                if (matrix[i][j] == '0') continue;
                int side = dp[i-1][j-1];
                int cnt = 0;
                for (int m = 1; m <= side; m += 1) {
                    if (matrix[i][j-m] == '0' || matrix[i-m][j] == '0') break;
                    cnt += 1;
                }
                dp[i][j] = 1 + cnt;
                result = Math.max(result, dp[i][j]);
            }
        }
        return result * result;
    }
}