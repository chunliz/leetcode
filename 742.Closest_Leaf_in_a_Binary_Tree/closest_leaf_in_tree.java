/*
 Explanation:
 Build the relation between each node and its neighbors with HashMap, then
 we need to record the distance between target node and other nodes, at last we find out the smallest distance in the leaves.
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private Set<Integer> leaves;
    private Map<Integer, List<Integer>> map;
    private Map<Integer, Integer> dist;
    private void add(int v1, int v2){
        if(map.containsKey(v1)) map.get(v1).add(v2);
        else map.put(v1,new ArrayList<>(Arrays.asList(v2)));
        
        if(map.containsKey(v2)) map.get(v2).add(v1);
        else map.put(v2,new ArrayList<>(Arrays.asList(v1)));
    }

    // map each node to its neighboring nodes
    private void dfs(TreeNode root){
        if(root.left==null && root.right==null) leaves.add(root.val);
        if(root.left!=null){
            add(root.val,root.left.val);
            dfs(root.left);
        } 
        if(root.right!=null){
            add(root.val,root.right.val);
            dfs(root.right);
        }
    }
    
    // find out the distance between target node and other nodes
    private void dfs2(int par, int cur, int d){
        dist.put(cur,d);
        if(map.containsKey(cur))
            for(int i:map.get(cur)) 
                if(i!=par)
                    dfs2(cur,i,d+1);
    }
    
    public int findClosestLeaf(TreeNode root, int k) {
        leaves=new HashSet<>();
        map=new HashMap<Integer, List<Integer>>();
        dist=new HashMap<Integer, Integer>();
        dfs(root);
        dfs2(-1,k,0);
        
        int minDist=Integer.MAX_VALUE;
        int result=Integer.MAX_VALUE;

        for(int i:leaves){
            if(dist.get(i)<minDist){
                minDist=dist.get(i);
                result=i;
            } 
        }
        return result;
    }
}