class Solution {
    public String longestWord(String[] words) {
        Map<Integer,List<String>> m=new HashMap<>();
        for(String str:words){
            if(m.containsKey(str.length())) m.get(str.length()).add(str);
            else{
                m.put(str.length(),new ArrayList<String>());
                m.get(str.length()).add(str);
            }    
        }
        String res="";
        if(!m.containsKey(1)) return res;
        int len=2;
        while(m.containsKey(len)){
            List<String> ls=new ArrayList<String>();
            for(String str:m.get(len)){
                if(m.get(len-1).contains(str.substring(0,str.length()-1))) ls.add(str);
            }
            if(ls.isEmpty()) break; 
            m.put(len,ls);
            len++;
        }
        m.get(len-1).sort(String::compareToIgnoreCase);
        return m.get(len-1).get(0);
    }
}