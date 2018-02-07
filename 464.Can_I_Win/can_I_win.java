class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= maxChoosableInteger) return true;
        if (desiredTotal > maxChoosableInteger * (maxChoosableInteger + 1) / 2) return false;
        
        Map<Integer, Boolean> map = new HashMap<>();
        int used = 0; // when the ith bit of used is 1, it means that i is already used. Used should be updated.
        return helper(desiredTotal, maxChoosableInteger, used, map); // Each status of used is mapped to an answer (YES or NO).
    }
    
    private boolean helper(int desiredTotal, int maxChoosableInteger, int used, Map<Integer, Boolean> map) {
        if (map.containsKey(used)) return map.get(used);
        
        // consider all of non-used integer. Determine whether a player will win if he/she encounters the situation with current used.
        for (int i = 0; i < maxChoosableInteger; i++) {
            int cur = 1 << i;
            if ((cur&used) == 0) {
                if(desiredTotal <= i + 1 || !helper(desiredTotal - i - 1, maxChoosableInteger, used|cur, map)) {
                    map.put(used, true);
                    return true;
                }
            }
        }
        map.put(used, false);
        return false;
    }
}