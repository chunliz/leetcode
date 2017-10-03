/*
 Hash table
*/

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result=new ArrayList<List<String>>();
        Map<String,List<String>> m=new HashMap<String,List<String>>();
        for(String str:strings){
            int dis=str.charAt(0)-'a';
            String key="";
            for(int i=0;i<str.length();i++){
                char tmp=(char) (str.charAt(i)-dis);
                if(tmp<'a') tmp+=26;
                key+=tmp;
            }
            if(m.containsKey(key)) m.get(key).add(str);
            else{
                List<String> ls=new ArrayList<>();
                ls.add(str);
                m.put(key,ls);
            }
        }
        for(String k:m.keySet()){
            result.add(m.get(k));
        }
        return result;
    }
}
