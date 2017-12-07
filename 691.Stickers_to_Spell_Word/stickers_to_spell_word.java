/*
 Very interesting!
 Keywords: backtracking. 
 References: https://discuss.leetcode.com/topic/106273/c-java-python-dp-memoization-with-optimization-29-ms-c/16
 Explain: 
 Take the example: ["with", "example", "science"], "thehat"
 Suppose the result is denoted as f("thehat"). Take "thehat" and match with three stickers respectively, there are three possibilites, and f("thehat") should be the smallest one.
 1. 1+f("ehat") (cut 1 sticker "with", it contributes "th", then "ehat" is left for evaluation); 
 2. 1+f("thht"); 
 3. 1+f("thhat").
 Then evaluate the substrings recursively. In this process, we use a HashMap to record the number of stickers needed for each substring.
*/
class Solution {
    public int minStickers(String[] stickers, String target) {
        int[][] mp=new int[stickers.length][26];
        Map<String, Integer> dp=new HashMap<>();
        for(int i=0;i<stickers.length;i++){
            for(char c:stickers[i].toCharArray()) mp[i][c-'a']++;
        }
        dp.put("",0);
        return helper(dp,mp,target);
    }
    private int helper(Map<String,Integer> dp, int[][] mp, String target){
        if(dp.containsKey(target)) return dp.get(target);
        int ans=Integer.MAX_VALUE,n=mp.length;
        int[] tar=new int[26];
        for(char c:target.toCharArray()) tar[c-'a']++;
        for(int i=0;i<n;i++){
            if(mp[i][target.charAt(0)-'a']==0) continue;
            StringBuilder sb=new StringBuilder();
            for(int j=0;j<26;j++){
                if(tar[j]-mp[i][j]>0)
                    for(int k=0;k<tar[j]-mp[i][j];k++)
                        sb.append((char)('a'+j));
            }
            String s=sb.toString();
            int tmp=helper(dp,mp,s);
            ans=(tmp==-1)?ans:Math.min(ans,1+tmp);
        }
        dp.put(target,ans==Integer.MAX_VALUE?-1:ans);
        return dp.get(target);
    }
}


/*
 Keywords: dp, backtracking
 The second method is actually similar to the first one. The differences are:
 1. first method: long string to short, start from target; this method: short to long (build-up), start from empty string
 2. first method: use Map<String, Integer> to store match result; this method: use array (int[index] dp) to store match result, and index is the bit representation of substrings. For example, target="with", then ""->0000, "w"->0001, "wt"->0101, "with"->1111. Then the solution is dp[1111]. (Note, the representation is reversed.)
 
 References:
 https://discuss.leetcode.com/topic/106427/explaining-stefanpochmann-s-rewrite-of-contest-winner-s-solution-java
*/

class Solution {
    public int minStickers(String[] stickers, String target) {
        int n=target.length(), N=1<<n;
        int[] dp=new int[N];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=0;i<N;i++){
            if(dp[i]==Integer.MAX_VALUE) continue;
            for(String s:stickers){
                int extend=i;
                for(char c:s.toCharArray()){
                    // find out the match and set the corresponding bit to 1 if it is 0, then go to the next char
                    for(int j=0;j<n;j++){
                        if(target.charAt(j)==c && ((extend>>j) & 1)==0){
                            extend |= 1<<j;
                            break;
                        }
                    }
                }
                dp[extend]=Math.min(dp[extend],1+dp[i]);
            }
        }
        return dp[N-1]==Integer.MAX_VALUE?-1:dp[N-1];
    }
}