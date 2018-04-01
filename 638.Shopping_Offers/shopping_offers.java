class Solution {
    // dfs
    int minPrice = Integer.MAX_VALUE;
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        if (needs.size() == 0) return 0;
        dfs(price, special, needs, 0, 0);
        return minPrice;
    }
    
    private void dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int index, int curPrice) {
        if (index == special.size()) {
            for (int i = 0; i < needs.size(); i++) curPrice += needs.get(i) * price.get(i);
            minPrice = Math.min(minPrice, curPrice);
            return;
        }
        int res = 0;
        
        List<Integer> list = special.get(index);
        int count = Integer.MAX_VALUE;
        for (int i = 0; i < needs.size(); i++) {
            if (list.get(i) == 0) continue;
            count = Math.min(count, needs.get(i) / list.get(i));
        }
        for (int i = 0; i <= count; i++) {
            List<Integer> next = new ArrayList<>();
            for (int j = 0; j < needs.size(); j++) {
                next.add(needs.get(j) - i * list.get(j));
            }
            dfs(price, special, next, index+1, curPrice + list.get(list.size()-1) * i);
        }
    }
}