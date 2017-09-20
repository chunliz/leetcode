/*
 The idea is:
 1. to map each string to a list of positions, and these positions should be in order. 
 
 2. Each time we consider the distance of two words, we take their corresponding lists and compare the positions (list1[i] and list2[j]) in these two lists.
        When list1[i]<=list2[j], i++;
        when list1[i]>list2[j],j++.
*/

class WordDistance {
    private Map<String,List<Integer>> map;
    public WordDistance(String[] words) {
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
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> ls1=new ArrayList<Integer>();
        List<Integer> ls2=new ArrayList<Integer>();
        ls1=map.get(word1);
        ls2=map.get(word2);
        int result=Integer.MAX_VALUE;
        for(int i=0,j=0;i<ls1.size() && j<ls2.size();){
            if(ls1.get(i)<=ls2.get(j)){
                result=Math.min(result,ls2.get(j)-ls1.get(i));
                i++;
            }else{
                result=Math.min(result,ls1.get(i)-ls2.get(j));
                j++;
            }
        }
        return result;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */
