class Solution {
    public int trap(int[] height) {
        int res=0;
        int left=0,accu=0;
        for(int i=1;i<height.length;i++){
            if(height[i]>=height[left]){
                res+=accu;
                left=i;
                accu=0;
            }else{
                accu+=height[left]-height[i];
            }
        }
        int right=height.length-1;
        accu=0;
        for(int i=height.length-2;i>=0;i--){
            if(height[i]>height[right]){
                res+=accu;
                right=i;
                accu=0;
            }else{
                accu+=height[right]-height[i];
            }
        }
        return res;
    }
}