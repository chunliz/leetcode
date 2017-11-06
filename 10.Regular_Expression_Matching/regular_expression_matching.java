/*
 Keywords: dp, recursion
 */

// dp
class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] dp=new boolean[s.length()+1][p.length()+1];
        dp[0][0]=true;
        for(int i=1;i<=s.length();i++) dp[i][0]=false;
        for(int j=1;j<=p.length();j++){
            if(p.charAt(j-1)=='*') dp[0][j]=dp[0][j-2];
            else dp[0][j]=false;
        }
        for(int i=1;i<=s.length();i++){
            for(int j=1;j<=p.length();j++){
                if(p.charAt(j-1)!='*') dp[i][j]=dp[i-1][j-1] && (s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='.');
                else dp[i][j]=(dp[i][j-2] || dp[i][j-1] || (dp[i-1][j] && (s.charAt(i-1)==p.charAt(j-2)||p.charAt(j-2)=='.')));
            }
        }
        return dp[s.length()][p.length()];
    }
}


// recursion
class Solution {
    public boolean isMatch(String s, String p) {
        if(p.isEmpty()) return s.isEmpty();
        boolean match = (!s.isEmpty() && (s.charAt(0)==p.charAt(0) || p.charAt(0)=='.'));
        if(p.length()>=2 && p.charAt(1)=='*'){
            return isMatch(s,p.substring(2)) || (match && isMatch(s.substring(1),p));
        } 
        else{
            return match && isMatch(s.substring(1),p.substring(1));
        } 
    }
}