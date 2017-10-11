/*
At first, I tried recursion, which gave me "TLE". The code is still given in the following.

Method 2: stack (References:https://discuss.leetcode.com/topic/35973/java-intuitive-22ms-solution-with-stack)
 */

// Recursion:
class Solution {
    // recursion
    public boolean isValidSerialization(String preorder) {
        String[] pre=preorder.split(",");
        return helper(pre);
    }
    public boolean helper(String[] pre){
        if(pre.length==1 && pre[0].equals("#")) return true;
        if(pre.length==2) return false;
        if(pre[0].equals("#") || (!pre[pre.length-1].equals("#"))) return false;
        for(int i=1;i<=pre.length-2;i++){
            String[] left=Arrays.copyOfRange(pre,1,1+i);
            String[] right=Arrays.copyOfRange(pre,1+i,pre.length);
            if(helper(left) && helper(right)) return true;
        }
        return false;        
    }
}

// Stack:
class Solution {
    public boolean isValidSerialization(String preorder) {
        if(preorder==null) return false;
        Stack<String> st=new Stack<>();
        String[] str=preorder.split(",");
        for(int i=0;i<str.length;i++){
            while(str[i].equals("#") && !st.empty() && st.peek().equals("#")){
                st.pop();
                if(st.empty()) return false;
                st.pop();
            }
            st.push(str[i]);
        }
        return st.size()==1 && st.peek().equals("#");
    }
}