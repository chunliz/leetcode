/*
 extend from each character and record the longest palindrome
 */

class Solution {
    private int low,maxLen;
    public String longestPalindrome(String s) {
        if(s.length()<2) return s;
        for(int i=0;i<s.length()-1;i++){
            extend(s,i,i);
            extend(s,i,i+1);
        }
        return s.substring(low,low+maxLen);
    }
    private void extend(String s, int left,int right){
        while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }
        if(right-left-1>maxLen){
            low=left+1;
            maxLen=right-left-1;
        }
    }
}