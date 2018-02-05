class Solution {
    public boolean canTransform(String start, String end) {
        char[] s = start.toCharArray();
        char[] e = end.toCharArray();
        if (s.length != e.length) return false;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == e[i]) continue;
            if (s[i] != 'X' && e[i] != 'X') return false;
            if (s[i] == 'L' && e[i] == 'X') return false;
            if (s[i] == 'X' && e[i] == 'R') return false;
            if (s[i] == 'R' && e[i] == 'X') {
                int nextR = findNext(s, i+1, 'X', 'R');
                if (nextR == -1) return false;
                else swap(s, i, nextR);
            } else if (s[i] == 'X' && e[i] == 'L') {
                int nextL = findNext(s, i+1, 'L', 'X');
                if (nextL == -1) return false;
                else swap(s, i, nextL);
            }
            
        }
        return true;
    }
    private int findNext(char[] s, int startIdx, char target, char skip) {
        for (int i = startIdx; i < s.length; i++) {
            if (s[i] == target) return i;
            else if (s[i] == skip) continue;
            else return -1;
        }
        return -1;
    }
    private void swap(char[] s, int i, int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }
}