/*
 References: https://discuss.leetcode.com/topic/87287/java-solution-with-full-explaination
 1. copy the reverse of the first half substring to the second half substring;
 2. based on the 1st step, find the palindromes that are larger and smaller than the original string;
 3. For the palindrome larger than the original string, special consideration is needed for strings like "9", "99", "999"...
 4. For the palindrome smaller than the original string, special consideration is needed for strings like "10", "100", "1000"...
*/

class Solution {
    public String nearestPalindromic(String n) {
        Long higher=Long.parseLong(higherPalindrome(n));
        Long lower=Long.parseLong(lowerPalindrome(n));
        Long num=Long.parseLong(n);
        return higher-num<num-lower?String.valueOf(higher):String.valueOf(lower);
    }
    public String higherPalindrome(String n){
        char[] pal=n.toCharArray();
        int size=n.length();
        for(int i=size/2;i<size;i++) pal[i]=pal[size-1-i];
        if(Long.parseLong(String.valueOf(pal))>Long.parseLong(n)) return String.valueOf(pal);
        else{
            for(int i=(size-1)/2; i>=0;i--){
                if(++pal[i]>'9') pal[i]='0';
                else break;
            }
            for(int i=size/2;i<size;i++) pal[i]=pal[size-1-i];
            if(Long.parseLong(String.valueOf(pal))==0){
                pal[size-1]='1';
                pal=("1"+String.valueOf(pal)).toCharArray();
            }
            return String.valueOf(pal);
        }
    }
    public String lowerPalindrome(String n){
        char[] pal=n.toCharArray();
        int size=n.length();
        for(int i=size/2;i<size;i++) pal[i]=pal[size-1-i];
        if(Long.parseLong(String.valueOf(pal))<Long.parseLong(n)) return String.valueOf(pal);
        else{
            for(int i=(size-1)/2; i>=0;i--){
                if(--pal[i]<'0') pal[i]='9';
                else break;
            }
            for(int i=size/2;i<size;i++) pal[i]=pal[size-1-i];
            if(size>1 && pal[0]=='0'){
                pal[size-1]='9';
                pal=String.valueOf(pal).substring(1).toCharArray();
            }
            return String.valueOf(pal);
        }
    }
}
