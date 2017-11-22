/*
 Keywords: backtracking
 References: https://discuss.leetcode.com/topic/28020/short-backtracking-solution-in-java-3-ms/2
*/
class Solution {
    private List<String> list=new ArrayList<>();
    public List<String> generatePalindromes(String s) {
        int numOdds=0;
        int[] map=new int[256];
        
        // determine whether palindrome permutation can be formed
        for(char c:s.toCharArray()){
            map[c]++;
            numOdds=(map[c]&1)==1?numOdds+1:numOdds-1;
        }
        if(numOdds>1) return list;
        
        // take half of the original string, and find all the permultations
        String mid="";
        int length=0;
        for(int i=0;i<256;i++){
            if(map[i]>0){
                if((map[i]&1)==1){
                    mid=""+(char)i;
                    map[i]--;
                }
                map[i]/=2;
                length+=map[i];
            }
        }
        helper(map,length,"",mid);
        return list;
    }
    private void helper(int[] map, int length, String s, String mid){
        if(s.length()==length){
            StringBuilder r=new StringBuilder(s).reverse();
            list.add(s+mid+r);
            return;
        }
        for(int i=0;i<256;i++){
            if(map[i]>0){
                map[i]--;
                helper(map,length,s+(char)i,mid);
                map[i]++;
            }
        }
    }
}