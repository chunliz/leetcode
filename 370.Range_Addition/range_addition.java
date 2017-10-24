class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] result=new int[length];
        for(int[] a:updates){
            int start=a[0],end=a[1],cre=a[2];
            for(int i=start;i<=end;i++) result[i]+=cre;
        }
        return result;
    }
}