/*
 The natural way to solve this problem is:
 1. generate all the abbreviation with bit-manipulation or DFS;
 2. sort in the length of abbreviations
 3. match the abbreviation with each word in the dictionary
 
 However, Memory Limit Exceeded!
*/

class Solution {
public:
    string minAbbreviation(string target, vector<string>& dictionary) {
        priority_queue<pair<int,string>,vector<pair<int,string>>,greater<pair<int,string>>> res;
        dfs_generate(target,res,"",0,false);
        while(!res.empty()){
            string abbr=res.top().second;res.pop();
            int cnt=0;
            for(auto& d:dictionary){
                if(d.size()!=target.size() || !check(abbr,d)) cnt++;
                else break;
            }
            if(cnt==dictionary.size()) return abbr;
        }
    }
    
    void dfs_generate(string target,priority_queue<pair<int,string>,vector<pair<int,string>>,greater<pair<int,string>>>& res,string abbr,int i,bool num){
        if(i==target.size()){
            res.push({abbr.size(),abbr});
            return;
        }
        dfs_generate(target,res,abbr+target[i],i+1,false);
        if(!num){
            for(int len=1;len+i<=target.size();len++)
                dfs_generate(target,res,abbr+to_string(len),i+len,true);
        }
    }
    bool check(string abbr,string word){
        int cur=0;
        for(int i=0;i<abbr.size();){
            if(abbr[i]>='a' && abbr[i]<='z'){
                if(abbr[i++]!=word[cur++]) return false;
                continue;
            }
            else{
                int num=0;
                while(i<abbr.size() && isdigit(abbr[i])){
                    num=num*10+abbr[i++]-'0';
                }
                cur+=num;
                if(cur>word.size()) return false;
            }
        }
        return cur==word.size()?true:false;
    }
};


/*
 References:
 https://discuss.leetcode.com/topic/61346/trie-bruteforce/2
 
 Keywords: fixed length abbreviation, Trie
 1. generate abbreviation with fixed length (increasing order) (reduce time as it is not necessary to generate all the abbreviations)
 2. check abbreviation and determine if it is in the Trie
*/

class Solution {
    class Trie{
        Trie[] next=new Trie[26];
        boolean isEnd=false;
    }
    Trie root=new Trie();
    List<String> abbrs;
    public String minAbbreviation(String target, String[] dictionary) {
        for(String s:dictionary) addTrie(s);
        for(int i=1;i<=target.length();i++){
            abbrs=new ArrayList<>();
            lenFixedAbbrGenerator(target,i,"",0,0);
            for(String s:abbrs){
                if(search(s,root,0,0)==false) return s;
            }
        }
        return "";
    }
    // generate abbreviation with the length "len"
    public void lenFixedAbbrGenerator(String target, int len, String str, int num, int i){
        if(i==target.length()){
            if(len==0 && num==0) abbrs.add(str);
            if(len==1 && num!=0) abbrs.add(str+num);
            return;
        }
        char ch=target.charAt(i);
        // ch remains as a char
        lenFixedAbbrGenerator(target,num==0?len-1:len-2,num==0?str+ch:str+num+ch,0,i+1);
        // ch is abbreviated
        lenFixedAbbrGenerator(target,len,str,num+1,i+1);
    }
    public void addTrie(String s){
        Trie cur=root;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(cur.next[c-'a']==null){
                cur.next[c-'a']=new Trie();
            }
            cur=cur.next[c-'a'];
        }
        cur.isEnd=true;
    }
    public boolean search(String target, Trie root, int i, int num){
        if(root==null) return false;
        if(num!=0){
            // for numbers in the target string, all nodes in corresponding levels in the Trie will be searched
            for(int j=0;j<26;j++){
                if(search(target,root.next[j],i,num-1)) return true;
            }
            return false;
        }
        if(i==target.length()){
            if(root.isEnd) return true;
            return false;
        }
        if(Character.isDigit(target.charAt(i))){
            int tmp=0;
            while(i<target.length() && Character.isDigit(target.charAt(i))){
                tmp=tmp*10+target.charAt(i)-'0';
                i++;
            }
            return search(target,root,i,tmp);
        }
        else{
            return search(target,root.next[target.charAt(i)-'a'],i+1,0);
        }
    }
}
