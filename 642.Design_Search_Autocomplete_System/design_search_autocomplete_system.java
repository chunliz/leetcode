class AutocompleteSystem {
    /* 
     Trie: each node includes Map<Character, TrieNode> children, Map<String, Integer> count; save strings with the same prefix
     input: find out the node with the current input string. Use PriorityQueue to obtain top 3 hot sentences. when finished, add to trie
    */
    class TrieNode {
        Map<Character, TrieNode> children;
        Map<String, Integer> count;
        public TrieNode() {
            children = new HashMap<>();
            count = new HashMap<>();
        }
    }
    
    class Pair {
        String s;
        int t;
        Pair(String s, int t) {
            this.s = s;
            this.t = t;
        }
    }
    
    TrieNode root;
    void addNode(String s, int t) {
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            if (!node.children.containsKey(c)) node.children.put(c, new TrieNode());
            node = node.children.get(c);
            node.count.put(s, node.count.getOrDefault(s, 0) + t);
        }
    }

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        for (int i = 0; i < sentences.length; i++) {
            addNode(sentences[i], times[i]);
        }
    }
    
    String prefix = "";
    public List<String> input(char c) {
        if (c=='#') {
            addNode(prefix, 1);
            prefix = "";
            return new ArrayList<>();
        }
        prefix = prefix + c;
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            if (!node.children.containsKey(ch)) return new ArrayList<>();
            node = node.children.get(ch);
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->a.t==b.t?a.s.compareTo(b.s) : b.t-a.t);
        for (String s : node.count.keySet()) {
            pq.add(new Pair(s, node.count.get(s)));
        }
        
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
            res.add(pq.peek().s);
            pq.poll();
        }
        return res;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */