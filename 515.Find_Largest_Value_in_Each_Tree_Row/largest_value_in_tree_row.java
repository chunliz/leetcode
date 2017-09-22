/*
 Keyword: Queue
 Add elements to queue and evaluate them level by level.
*/

class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result=new ArrayList<Integer>();
        Queue<TreeNode> queue=new LinkedList<TreeNode>();
        queue.add(root);
        int size=queue.size();
        size=root==null?0:1;
        while(size>0){
            int largest=Integer.MIN_VALUE;
            for(int i=0;i<size;i++){
                TreeNode temp=queue.poll();
                largest=Math.max(temp.val,largest);
                if(temp.left!=null) queue.add(temp.left);
                if(temp.right!=null) queue.add(temp.right);
            }
            size=queue.size();
            result.add(largest);
        }
        return result;
    }
}
