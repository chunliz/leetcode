/*
 Keywords: bit manipulation
 Special case: n=3, n=Integer.MAX_VALUE
 */

class Solution {
    public int integerReplacement(int n) {
        int res=0;
	//        System.out.print(n+1);
        while(n!=1){
            res+=1;
            if((n&1)==0) n>>>=1;
            else{
                if(Integer.bitCount(n-1)<Integer.bitCount(n+1) || n==3) n-=1;
                else n+=1;
            }
        }
        return res;
    }
}