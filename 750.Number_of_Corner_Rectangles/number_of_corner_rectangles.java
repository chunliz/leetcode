class Solution {
    public int countCornerRectangles(int[][] grid) {
        int row=grid.length;
        int col=grid[0].length;
        int result=0;
        for(int i=0;i<row;i++){
            for(int j=i+1;j<row;j++){
                int count=0;
                for(int k=0;k<col;k++){
                    if(grid[i][k]==1 && grid[j][k]==1) count++;
                }
                result+=count*(count-1)/2;
            }
        }
        return result;
    }
}