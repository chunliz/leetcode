/*
Explanation:
Every 10(i.e. div) numbers coutribute 1(i.e. contr) digit1;
Every 100(i.e. div) numbers coutribute 10(i.e. contr) digit1;
Every 1000(i.e. div) numbers coutribute 100(i.e. contr) digit1;
etc.

e.g. 31500
Step 1:contr=1, div=10, count how many 10 numbers, n/div=3150, contribute 3150*1 digit1, left=n-n/10*10=0, contribute 0;
Step 2:contr=10,div=100, count how many 100 numbers, n/100=315, contribute 315*10 digit1, left=n-n/100*100=0, contribute 0;
Step 3:contr=100,div=1000, count how many 1000 numbers, n/1000=31, contribute 31*100 digit1, left=n-n/1000*1000=500, 500 includes (100-199), therefore the left 500 also constribute 100 digit1;
Step 4:contr=1000,div=10000, count how many 10000 numbers, n/10000=3, contribute 3*1000 digit1, left=n-n/10000*10000=1500, 1500 only includes part of (1000-1999), it constributes 501 digit1;
Step 4:contr=10000,div=100000, count how many 100000 numbers, n/10000=0, contribute 0*1000 digit1, left=n-n/100000*100000=31500, 31500 includes (10000-19999), it constributes 10000 digit1;
Done!
*/
class Solution {
    public int countDigitOne(int n) {
        if(n<1) return 0;
        long contr=1,res=0;
        while(n>=contr){
            long div=contr*10,left=n-n/div*div;
            res+=n/div*contr+(left>=2*contr?contr:(left>=contr?left%contr+1:0));
            contr=div;
        }
        return (int)res;
    }
}