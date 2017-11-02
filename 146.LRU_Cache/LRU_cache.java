/*
 Keywords: HashMap, Double Linked List(with sentinel)
 */

class LRUCache {
    private class cacheNode{
        public int key,val;
        public cacheNode pre,next;
        public cacheNode(int k,int v){
            key=k;
            val=v;
        }
    }
    private void addNodeHead(cacheNode node){
        node.next=sentinel.next;
        sentinel.next.pre=node;
        sentinel.next=node;
        node.pre=sentinel;
    }
    private int popNode(){
        cacheNode last=sentinel.pre;
        sentinel.pre=last.pre;
        last.pre.next=sentinel;
        return last.key;
    }
    private void remove(cacheNode node){
        cacheNode p=node.pre;
        cacheNode n=node.next;
        p.next=n;
        n.pre=p;
    }
    private Map<Integer,cacheNode> cache;
    private int capacity,count;
    private cacheNode sentinel;
    public LRUCache(int capacity) {
        this.capacity=capacity;
        this.count=0;
        cache=new HashMap<Integer,cacheNode>();
        sentinel=new cacheNode(-1,-1);
        sentinel.pre=sentinel;
        sentinel.next=sentinel;
    }
    
    public int get(int key) {
        cacheNode node=cache.get(key);
        if(node==null) return -1;
        else{
            remove(node);
            addNodeHead(node);
            return node.val;
        }
    }
    
    public void put(int key, int value) {
        cacheNode node=cache.get(key);
        if(node==null && count==capacity){
            int last=popNode();
            cache.remove(last);
            count--;
        }
        
        cacheNode nnode=new cacheNode(key,value);
        if(node!=null){
            remove(node);
            count--;
        }
        cache.put(key,nnode);
        addNodeHead(nnode);
        count++;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */