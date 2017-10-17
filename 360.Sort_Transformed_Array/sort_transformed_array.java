/*
 References:
 https://discuss.leetcode.com/topic/48424/java-o-n-incredibly-short-yet-easy-to-understand-ac-solution
 
 If a>0, one of the end element in the original array gives the largest result;
 If a<=0, one of the end element in the original array gives the smallest result.
 */

class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n=nums.length;
        int[] result=new int[n];
        int index=a>0?n-1:0;
        int left=0,right=n-1;
        while(left<=right){
            if(a>0) result[index--]=f(nums[left],a,b,c)>f(nums[right],a,b,c)?f(nums[left++],a,b,c):f(nums[right--],a,b,c);
            else result[index++]=f(nums[left],a,b,c)<f(nums[right],a,b,c)?f(nums[left++],a,b,c):f(nums[right--],a,b,c);
        }
        return result;
    }
    int f(int x,int a, int b, int c){
        return a*x*x+b*x+c;
    }
}