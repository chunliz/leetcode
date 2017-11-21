class Solution {
    /** This problem can also be described as: find out the longest palindromic substring in S which starts from the first character. (The center of the new palindrome can only be given by the left part of the original string)
    Therefore, we apply the "build-the-table" step of KMP algorithm to the string S+#+S.reverse (we call the new String T in the following). 
    For example: S="abbade", then T="abbade#edabba". We take the substrings to match the prefix. Then the match table is {-1,-1,-1,0,-1,-1,-1,-1,0,1,2,3}, then the last value gives the largest suffix of the reversed string that matches the prefix of the original string. Then the result is "the first (s.length()-3) characters of the reversed string, i.e. ed" + S.
    Another example: S="abcabcabcd", then T="abcabcabcd#bcbacbacba". The match table is {-1,-1,-1,0,1,2,3,4,5...}, then at 'd', it does not match with s.charAt(6), we shift the substring "abcabcd" left by 3 characters (5-match[5]), compare "abcd" with S. character 'c' matches again. check 'd' and s.charAt(match[5]+1), they do not match, shift left by (2-match[2]) and compare 'd' with s.charAt(match[2]+1)...
    */
    public String shortestPalindrome(String s) {
        String r=new StringBuilder(s).reverse().toString();
        String t=s+"#"+r;
        int[] match=new int[t.length()];
        Arrays.fill(match,-1);
        int pre=-1;
        for(int i=1;i<t.length();i++){
            while(pre>-1 && t.charAt(i)!=t.charAt(pre+1)) pre=match[pre]; // when mismatch accurs, shift the substring left
            if(t.charAt(i)==t.charAt(pre+1)) pre++;
            match[i]=pre;
        }
        return r.substring(0,s.length()-match[t.length()-1]-1)+s;
    }
}