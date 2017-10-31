class Solution {
    /*
    e.g. num = "1432219", k = 3
    step 1: consider "1432"(length=k+1). Take '1' as the leading character, remove no digit, k=3;
    step 2: consider "4322". Take the first '2', remove "43", k=1;
    step 3: consider "21". Take '1', remove '2', k=0;
    step 4: the resulting string is "1219", stop.
    */
    public String removeKdigits(String num, int k) {
        if(num.length()==k) return "0";
        StringBuilder sb=new StringBuilder();
        int start=0;
        while(k>0){
            if(num.length()-start==k){
                start=num.length();
                break;
            }
            int idx=index(num.substring(start,start+k+1));
            sb.append(num.charAt(start+idx));
            k-=idx;
            start+=idx+1;
        }
        if(start<num.length()) sb.append(num.substring(start));
        while(sb.charAt(0)=='0' && sb.length()>1) sb.deleteCharAt(0); // Remove leading zeroes
        return sb.toString();
    }
    public int index(String str){
        int res=0;
        for(int i=1;i<str.length();i++){
            if(str.charAt(i)<str.charAt(res)) res=i;
        }
        return res;
    }
}