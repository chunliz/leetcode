/*
 Keywords: Trie, backtracking
 */

class WordDictionary {
    class TrieNode{
        String word;
        TrieNode[] children;
        TrieNode(){
            children=new TrieNode[26];
            word=null;
        }
    }
    TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root=new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node=root;
        char[] wordc=word.toCharArray();
        for(int i=0;i<wordc.length;i++){
            int c=wordc[i]-'a';
            if(node.children[c]==null) node.children[c]=new TrieNode();
            node=node.children[c];
        }
        node.word=word;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        TrieNode node=root;
        char[] wordc=word.toCharArray();
        return helpSearch(node, wordc, 0);
    }
    private boolean helpSearch(TrieNode node, char[] wordc, int i){
        if(i==(wordc.length-1)){
            if(wordc[i]=='.'){
                for(int j=0;j<26;j++)
                    if(node.children[j]!=null && node.children[j].word!=null) return true;
            }
            else{
                int c=wordc[i]-'a';
                if(node.children[c]!=null && node.children[c].word!=null) return true;
            }
            return false;
        }
        
        if(wordc[i]=='.'){
            for(int k=0;k<26;k++){
                if(node.children[k]!=null) 
                    if(helpSearch(node.children[k], wordc, i+1)) return true;
            }
        }
        else{
            int c=wordc[i]-'a';
            if(node.children[c]!=null)
                if(helpSearch(node.children[c], wordc, i+1)) return true;
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */