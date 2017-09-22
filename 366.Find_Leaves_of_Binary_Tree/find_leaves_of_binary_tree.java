/*
 This problem is helpful for understanding "pass-by-value" in java.
*/

// Wrong solution
class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res=new ArrayList<List<Integer>>();
        while(root!=null){
            List<Integer> list=new ArrayList<Integer>();
            helper(root,list);
            res.add(list);
        }
        return res;
    }
    
    // return and remove leaves
    public void helper(TreeNode root, List<Integer> list){
        if(root.left==null && root.right==null){
            list.add(root.val);
            root=null; // does not change root in the main function!
            return;
        }
        if(root.left!=null){
            helper(root.left,list);
        }
        if(root.right!=null){
            helper(root.right,list);
        }
    }
}

// Correct solution
class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res=new ArrayList<List<Integer>>();
        while(root!=null){
            List<Integer> list=new ArrayList<Integer>();
            if(helper(root,list)) root=null;
            res.add(list);
        }
        return res;
    }
    
    // return and remove leaves
    public boolean helper(TreeNode root, List<Integer> list){
        if(root.left==null && root.right==null){
            list.add(root.val);
            return true;
        }
        if(root.left!=null){
            if(helper(root.left,list)) root.left=null;
        }
        if(root.right!=null){
            if(helper(root.right,list)) root.right=null;
        }
        return false;
    }
}
