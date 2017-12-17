/*
 Keywords: dfs
 References:
 */
class Solution {
    private static int[][] dirs={{1,0},{-1,0},{0,1},{0,-1}};
    public int containVirus(int[][] grid) {
        int row=grid.length,col=grid[0].length;
        int res=0;
        while(true){
            int cnt=0;
            Set<Integer> virus=null;
            boolean[][] visited=new boolean[row][col];
            // find out the region
            for(int i=0;i<row;i++){
                for(int j=0;j<col;j++){
                    if(grid[i][j]!=1 || visited[i][j]) continue;
                    Set<Integer> zeros=new HashSet<>();
                    Set<Integer> ones=new HashSet<>();
                    dfs(grid,visited,i,j,zeros,ones);
                    if(cnt<zeros.size()){
                        cnt=zeros.size();
                        virus=ones;
                    }
                }
            }
            if(cnt==0 || virus==null) break;
            // build the wall
            for(int key:virus){
                int i=key/col, j=key%col;
                grid[i][j]=-1;
                for(int[] d:dirs){
                    int ni=i+d[0],nj=j+d[1];
                    if(ni>=0 && ni<row && nj>=0 && nj<col && grid[ni][nj]==0) res++;
                }
            }
            // infect
            visited=new boolean[row][col];
            for(int i=0;i<row;i++){
                for(int j=0;j<col;j++){
                    if(grid[i][j]!=1 || visited[i][j]) continue;
                    infect(grid,visited,i,j);
                }
            }
        }
        return res;
    }
    
    public void dfs(int[][] grid, boolean[][] visited, int r, int c, Set<Integer> zeros, Set<Integer> ones){
        int row=grid.length, col=grid[0].length, key=r*col+c;
        if(r<0 || r>=row || c<0 || c>=col || grid[r][c]!=1 || visited[r][c]) return;
        visited[r][c]=true;
        ones.add(key);
        for(int[] d:dirs){
            int nr=r+d[0], nc=c+d[1], nkey=nr*col+nc;
            if(nr>=0 && nr<row && nc>=0 && nc<col && grid[nr][nc]==0) zeros.add(nkey);
            dfs(grid,visited,nr,nc,zeros,ones);
        }
    }
    
    public void infect(int[][] grid, boolean[][] visited, int r, int c){
        int row=grid.length, col=grid[0].length, key=r*col+c;
        if(r<0 || r>=row || c<0 || c>=col || grid[r][c]!=1 || visited[r][c]) return;
        visited[r][c]=true;
        for(int[] d:dirs){
            int nr=r+d[0], nc=c+d[1];
            if(nr>=0 && nr<row && nc>=0 && nc<col && grid[nr][nc]==0 && !visited[nr][nc]){
                grid[nr][nc]=1;
                visited[nr][nc]=true;
                continue;
            }
            infect(grid,visited,nr,nc);
        }
    }
}