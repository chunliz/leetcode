class RandomizedSet {

    /** Initialize your data structure here. */
    List<Integer> list;
    Map<Integer, Integer> map;
    Random random = new Random();
    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        int pos = map.size();
        list.add(pos, val);
        map.put(val, pos);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int pos = map.get(val);
        map.remove(val);
        if (map.isEmpty() || pos == map.size()) return true;
        int tail = list.get(map.size());
        list.set(pos, tail);
        map.put(tail, pos);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        if (map.size() == 0) return -1;
        int index = random.nextInt(map.size());
        return list.get(index);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */