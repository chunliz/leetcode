/*
 1. Dynamic programming: memery limit exceeded
 2. math
 */

// Dynamic programming, just for reference
class Solution {
    /** Dynamic programming */
    public int countNumbersWithUniqueDigits(int n) {
        int nn=(int)Math.pow(10,n);
        int[] count=new int[nn];
        count[0]=1;
        for(int i=1;i<nn;i++){
            if(check(i)) count[i]=count[i-1]+1;
            else count[i]=count[i-1];
        }
        return count[nn-1];
    }
    boolean check(int n){
        String str=String.valueOf(n);
        boolean[] visited=new boolean[10];
        for(int i=0;i<str.length();i++){
            if(visited[str.charAt(i)-'0']) return false;
            visited[str.charAt(i)-'0']=true;
        }
        return true;
    }
}

// math. The first digit has 9 choices, the second has 9 choices, the third has 8, the fourth has 7, the fifth has 6...
class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if(n==0) return 1;
        int result=10;
        int start=9;
        for(int i=2;i<=n && i<=10;i++){
            start*=(9-i+2);
            result+=start;
        }       
        return result;
    }           
}