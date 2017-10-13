class Solution {
    boolean[][] visited;
    int row,col;
    int island_size;
    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length==0) return 0;
        row=grid.length;
        col=grid[0].length;
        visited=new boolean[row][col];
        int result=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]==1 && !visited[i][j]){
                    visited[i][j]=true;
                    island_size=1;
                    extend(grid,i,j);
                    result=Math.max(result,island_size);
                }    
            }
        }
        return result;
    }
    public void extend(int[][] grid, int i, int j){
        int[][] dirs={{-1,0},{1,0},{0,-1},{0,1}};
        for(int idx=0;idx<4;idx++){
            int x=i+dirs[idx][0],y=j+dirs[idx][1];
            if(x>=0 && x<row && y>=0 && y<col && grid[x][y]==1 && !visited[x][y]){
                visited[x][y]=true;
                island_size++;
                extend(grid,x,y);
            }
        }
    }
}