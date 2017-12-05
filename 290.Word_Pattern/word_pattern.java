/*
 Keywords: HashMap, HashSet
 */
class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] strArray=str.split(" ");
        Map<Character, String> map=new HashMap<>();
        Set<String> set=new HashSet<>();
        if(pattern.length()!=strArray.length) return false;
        
        for(int i=0;i<pattern.length();i++){
            Character c=pattern.charAt(i);
            if(map.containsKey(c)){
                if(!strArray[i].equals(map.get(c))) return false;
                continue;
            }
            else{
                String s=strArray[i];
                if(set.contains(s)) return false;
                map.put(c,s);
                set.add(s);
            }
        }
            
        return true;
    }
}