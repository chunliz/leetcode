/*
 Maintain a histogram at each element with L[i],R[i],H[i], and its area is (R[i]-L[i])*H[i]. Update them row by row.
 e.g.,
 1 0 1 0 0
 1 0 1 1 1
 
 L:
 0 0 2 0 0
 0 0 2 2 2 
 R:
 1 5 3 5 5
 1 5 3 5 5
 H:
 1 0 1 0 0
 2 0 2 1 1
 */
class Solution {
    // dynamic programming
    public int maximalRectangle(char[][] matrix) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0) return 0;
        int row=matrix.length,col=matrix[0].length;
        int[] L=new int[col];
        int[] R=new int[col];
        int[] H=new int[col];
        Arrays.fill(R,col);
        
        int res=0;
        for(int i=0;i<row;i++){
            int left=0,right=col;
            for(int j=0;j<col;j++){
                if(matrix[i][j]=='1'){
                    L[j]=Math.max(L[j],left);
                    H[j]++;
                }
                else{
                    left=j+1;
                    L[j]=0;
                    R[j]=col;
                    H[j]=0;
                }
            }
            for(int j=col-1;j>=0;j--){
                if(matrix[i][j]=='1'){
                    R[j]=Math.min(R[j],right);
                    res=Math.max(res,(R[j]-L[j])*H[j]);
                }
                else{
                    right=j;
                }                
            }
        }
        return res;
    }
}