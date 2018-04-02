/* list + hash table*/
class LogSystem {
    class Log {
        int id;
        String timestamp;
        Log(int i, String s) {
            id = i;
            timestamp = s;
        }
    }
    List<Log> list;
    public LogSystem() {
        list = new ArrayList<>();
        map = new HashMap<>();
        map.put("Year", 4);
        map.put("Month", 7);
        map.put("Day", 10);
        map.put("Hour", 13);
        map.put("Minute", 16);
        map.put("Second", 19);
    }
    
    public void put(int id, String timestamp) {
        list.add(new Log(id, timestamp));
    }
    
    Map<String, Integer> map;
    public List<Integer> retrieve(String s, String e, String gra) {
        Collections.sort(list, (a,b)->a.timestamp.compareTo(b.timestamp));
        String ns = s.substring(0, map.get(gra));
        String ne = e.substring(0, map.get(gra));
        int leftIdx = Collections.binarySearch(list, new Log(-1, ns), (a,b)->a.timestamp.compareTo(b.timestamp));
        if (leftIdx < 0) leftIdx = -(leftIdx + 1);
        List<Integer> res = new ArrayList<>();
        while (leftIdx < list.size() && (list.get(leftIdx).timestamp.compareTo(ne) < 0 || list.get(leftIdx).timestamp.substring(0, map.get(gra)).equals(ne))) {
            res.add(list.get(leftIdx).id);
            leftIdx++;
        }
        return res;
    }
}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */