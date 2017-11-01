class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int s1=nums1.length,s2=nums2.length;
        if((s1+s2)%2==1) return find_kth(nums1,nums2,(s1+s2)/2+1);
        else return (find_kth(nums1,nums2,(s1+s2)/2)+find_kth(nums1,nums2,(s1+s2)/2+1))/2.0;
    }
    public int find_kth(int[] nums1,int[] nums2,int k){
        if(nums1.length>nums2.length) return find_kth(nums2,nums1,k);
        if(nums1.length==0) return nums2[k-1];
        if(k==1) return Math.min(nums1[0],nums2[0]);
        
        int i1=Math.min(k/2,nums1.length);
        int i2=k-i1;
        if(nums1[i1-1]<nums2[i2-1]) return find_kth(Arrays.copyOfRange(nums1,i1,nums1.length),nums2,k-i1);
        else if(nums1[i1-1]>nums2[i2-1]) return find_kth(nums1,Arrays.copyOfRange(nums2,i2,nums2.length),k-i2);
        else return nums1[i1-1];
        
    }
}