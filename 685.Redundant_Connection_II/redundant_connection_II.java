/*
 Keywords: union find
 */
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        Map<Integer,Integer> map=new HashMap<>();
        List<List<Integer>> result=new ArrayList<>();

        for(int[] e:edges){
            if(map.containsKey(e[1])){
                result.add(new ArrayList<>(Arrays.asList(map.get(e[1]),e[1])));
                result.add(new ArrayList<>(Arrays.asList(e[0],e[1])));
            }
            else map.put(e[1],e[0]);
        }
        if(result.isEmpty()){
            int[] ans=new int[2];
            findCircle(edges,ans); 
            return ans;
        }
        else{
            int[][] resultArray=new int[2][2];
            for(int i=0;i<2;i++)
                for(int j=0;j<2;j++)
                    resultArray[i][j]=result.get(i).get(j);
            if(isValid(edges,resultArray[1])) return resultArray[1];
            else return resultArray[0];
        }
    }
    
    public boolean findCircle(int[][] edges,int[] result){
        Map<Integer,Integer> map=new HashMap<>();
        for(int[] e:edges){
            if(map.containsKey(e[0]) && map.containsKey(e[1]) && find(map,e[0])==e[1] && find(map,e[1])==e[1]){
                result[0]=e[0];
                result[1]=e[1];
                return true;
            } 
            else{
                int root=map.containsKey(e[0])?map.get(e[0]):e[0];
                map.put(e[0],root);
                map.put(e[1],root);
            }
        }
        
        return false;
    }
    public boolean isValid(int[][] edges,int[] remove){
        int[][] edgesL=new int[edges.length-1][2];
        int i=0;
        for(int[] e:edges){
            if(e[0]==remove[0] && e[1]==remove[1]) continue;
            edgesL[i][0]=e[0];
            edgesL[i][1]=e[1];
            i++;
        }
        if(findCircle(edgesL,new int[2])) return false;
        else return true;   
    }
    public int find(Map<Integer,Integer> map, int node){
        if(map.get(node)==node) return node;
        else return find(map,map.get(node));
    }
}