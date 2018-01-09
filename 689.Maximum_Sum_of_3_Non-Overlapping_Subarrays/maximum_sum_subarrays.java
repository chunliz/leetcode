/*
 Keywords: DP
 Fix the start index of the second array, find out the start index of the first and third arrays with the maximum sum
 */
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int size = nums.length;
        int[] leftIdx = new int[size];
        int[] rightIdx = new int[size];
        Arrays.fill(leftIdx, -1);
        Arrays.fill(rightIdx, -1);
        
        int[] sum = new int[size];
        sum[0] = nums[0];
        for (int i = 1; i < size; i++) sum[i] = sum[i-1] + nums[i];
        
        for (int i = k; i+k+k <= size; i++) {
            // determine the value of leftIdx[i]
            int leftmax = 0;
            for (int j = 0; j+k <= i; j++) {
                if (sum[j + k - 1] - (j>0?sum[j-1]:0) > leftmax) {
                    leftmax = sum[j + k - 1] - (j>0?sum[j-1]:0);
                    leftIdx[i] = j;
                }
            }

            // determine the value of rightIdx[i]
            int rightmax = 0;
            for (int l = i + k; l+k <= size; l++) {
                if (sum[l + k - 1] - sum[l-1] > rightmax) {
                    rightmax = sum[l + k - 1] - sum[l-1];
                    rightIdx[i] = l;
                }
            }            
        }
        
        int[] result = new int[3];
        int totalMax = 0;
        for (int i = k; i+2*k <= size; i++) { 
            int total = sum[leftIdx[i]+k-1] - (leftIdx[i]>0?sum[leftIdx[i]-1]:0) + sum[rightIdx[i]+k-1] - sum[rightIdx[i]-1] + sum[i+k-1] - sum[i-1];
            if(total > totalMax) {
                totalMax = total;
                result[0] = leftIdx[i];
                result[1] = i;
                result[2] = rightIdx[i];
            }
        }
        
        return result;
    }
}