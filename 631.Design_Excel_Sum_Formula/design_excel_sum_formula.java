/* two hash tables. One is to access the cells that the current cell relies on, another is to access the cells that the current cell has impact on. */
class Excel {
    int row, col;
    int[][] matrix;
    Map<Integer, List<Integer>> map;
    Map<Integer, List<Integer>> map2; 
    public Excel(int H, char W) {
        row = H;
        col = W - 'A' + 1;
        matrix = new int[row][col];
        map = new HashMap<>();
        map2 = new HashMap<>();
    }
    
    public void set(int r, char c, int v) {
        int pos = (r-1) * col + (c-'A');
        if (map2.containsKey(pos)) {
            breakConnection(r-1, c-'A');
            map2.remove(pos);
        }
        int pre = matrix[r-1][c-'A'];
        matrix[r-1][c-'A'] = v;
        
        
        if (!map.containsKey(pos)) return;
        List<Integer> list = map.get(pos);
        while(!list.isEmpty()){
            List<Integer> nList = new ArrayList<>();
            for (int i : list) {
                int x = i / col, y = i % col;
                matrix[x][y] += (v - pre);
                if (map.containsKey(x*col+y)) nList.addAll(map.get(x*col+y));
            }
            list = nList;
        }
        
    }
    
    public int get(int r, char c) {
        return matrix[r-1][c-'A'];
    }
    
    public int sum(int r, char c, String[] strs) {
        int res = 0;
        int pos = (r - 1) * col + c - 'A';
        if (map2.containsKey(pos)) {
            breakConnection(r-1, c-'A');
            map2.remove(pos);
        }
        List<Integer> ls = new ArrayList<>();
        for (String s : strs) {
            int rs, re, cs, ce;
            int index = s.indexOf(":");
            if (index == -1) {
                cs = s.charAt(0) - 'A';
                ce = cs;
                rs = Integer.valueOf(s.substring(1)) - 1;
                re = rs;
            } else {
                cs = s.charAt(0) - 'A';
                rs = Integer.valueOf(s.substring(1, index)) - 1;
                ce = s.charAt(index + 1) - 'A';
                re = Integer.valueOf(s.substring(index + 2)) - 1;
            }
            
            for (int i = rs; i <= re; i++) {
                for (int j = cs; j <= ce; j++) {
                    res += matrix[i][j];
                    if (!map.containsKey(i * col + j)) map.put(i * col + j, new ArrayList<Integer>());
                    map.get(i * col + j).add(pos);
                    ls.add(i*col+j);
                }
            }
        }
        map2.put(pos, ls);
        matrix[r-1][c-'A'] = res;
        return res;
    }
    private void breakConnection(int r, int c) {
        List<Integer> ls = map2.get(r*col+c);
        for (int i : ls) {
            map.get(i).remove((Integer) (r*col+c));
            if(map.get(i).isEmpty()) map.remove(i);
        }
    }
}