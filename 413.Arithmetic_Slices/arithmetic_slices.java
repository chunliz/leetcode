/*
 Keywords: DP
 */
class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3) return 0;
        int result = 0;
        int[] dp = new int[A.length];
        for (int i = 2; i < A.length; i += 1) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = 1 + dp[i-1];
                result += dp[i];
            }
        }
        return result;
    }
}