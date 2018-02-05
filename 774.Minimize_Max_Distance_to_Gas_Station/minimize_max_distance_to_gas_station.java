/**
 Keywords: Binary Search
 */

class Solution {
    public double minmaxGasDist(int[] stations, int K) {
        double left = 0.0, right = (double) stations[stations.length-1]-stations[0];
        
        while (left + 1e-6 < right) {
            double mid = left + (right - left) / 2.0;
            int count = 0;
            for (int i = 1; i < stations.length; i++) {
                count += Math.ceil((stations[i] - stations[i-1])/mid) - 1;
            }
            if (count > K) left = mid;
            else right = mid;
        }
        return left;
    }
}