/*
 Keyword: union find, DFS
 References: https://discuss.leetcode.com/topic/109642/java-c-union-find
 */
// union find
class Solution {
    /* union find */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String,String> owner=new HashMap<>();
        Map<String,String> parent=new HashMap<>();
        Map<String,TreeSet<String>> union=new HashMap<>();
        
        // map each email to its owner, and set parent of each email to itself temporarily 
        for(List<String> ls:accounts){
            for(int i=1;i<ls.size();i++){
                owner.put(ls.get(i),ls.get(0));
                parent.put(ls.get(i),ls.get(i));
            }
        }
        // connect emails that belongs to the same owner
        for(List<String> ls:accounts){
            String p=find(ls.get(1),parent);
            for(int i=2;i<ls.size();i++){
                parent.put(find(ls.get(i),parent),p);
            }
        }
        // unite emails with the same parent
        for(List<String> ls:accounts){
            for(int i=1;i<ls.size();i++){
                String p=find(ls.get(i),parent);
                if(!union.containsKey(p)) union.put(p,new TreeSet<String>());
                union.get(p).add(ls.get(i));
            }
        }
        
        List<List<String>> result=new ArrayList<>();
        for(String s:union.keySet()){
            List<String> ls=new ArrayList<>(union.get(s));
            ls.add(0,owner.get(s));
            result.add(ls);
        }
        return result;
    }
    public String find(String s,Map<String,String> p){
        return p.get(s)==s?s:find(p.get(s),p);
    }
}

// DFS
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // build the graph
        Map<String,Set<String>> graph=new HashMap<>();
        for(List<String> ls:accounts){
            for(int i=1;i<ls.size();i++){
                if(!graph.containsKey(ls.get(i))) graph.put(ls.get(i),new HashSet<String>());
                graph.get(ls.get(i)).add(ls.get(1));
                graph.get(ls.get(1)).add(ls.get(i));
            }
        }
        // traverse the graph, find out all the connected subgraph
        Set<String> visited=new HashSet<>();
        List<List<String>> result=new ArrayList<>();
        for(List<String> ls:accounts){
	    if(!visited.contains(ls.get(1))){
		List<String> ans=new ArrayList<>();
		visited.add(ls.get(1));
		ans.add(ls.get(1));
		dfs(graph,visited,ls.get(1),ans);
		Collections.sort(ans);
		ans.add(0,ls.get(0));
		result.add(ans);
	    }
        }
        return result;
    }
    public void dfs(Map<String,Set<String>> graph, Set<String> visited, String s,List<String> ans){
        for(String str:graph.get(s)){
            if(!visited.contains(str)){
                visited.add(str);
                ans.add(str);
                dfs(graph,visited,str,ans);
            }
        }
    }
}