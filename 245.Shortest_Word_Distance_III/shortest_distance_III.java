/*
 See problem244.
 When list1[i]==list2[j], i++ and skip.
*/

class Solution {
    private Map<String,List<Integer>> map;
    public int shortestWordDistance(String[] words, String word1, String word2) {
        map = new HashMap<String, List<Integer>>();
        for(int i=0;i<words.length; i++){
            String s=words[i];
            if(map.containsKey(s)) map.get(s).add(i);
            else{
                List<Integer> ls=new ArrayList<Integer>();
                ls.add(i);
                map.put(s,ls);
            }
        }
        List<Integer> ls1=new ArrayList<Integer>();
        List<Integer> ls2=new ArrayList<Integer>();
        ls1=map.get(word1);
        ls2=map.get(word2);
        int result=Integer.MAX_VALUE;
        for(int i=0,j=0;i<ls1.size() && j<ls2.size();){
            if(ls1.get(i)<ls2.get(j)){
                result=Math.min(result,ls2.get(j)-ls1.get(i));
                i++;
            }
            else if(ls1.get(i)>ls2.get(j)){
                result=Math.min(result,ls1.get(i)-ls2.get(j));
                j++;
            }
            else i++;
        }
        return result;
    }
}
