/*
1. Each new ugly number can be represented as a previous ugly number multiply one of the prime
2. It is not necessary to check all the possible results of {previous number * prime}. We only need to check primes.length of them, and keep the smallest one. Therefore we need to record how far primes[i] has come with an array idx[primes.length].

Taking primes={2,7,13,19},
1. idx[i]=0,i=0,1,2,3;
2. compare and keep the smallest number in result[idx[i]]*idx[i]. For the corresponding i=k, idx[k]++.(several k may give the same results, idx[k]++ applies to all of them); 
3. repeat 2 to get the next ugly number.
 */

class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] result=new int[n];
        int[] idx=new int[primes.length];
        Arrays.fill(idx,0);
        result[0]=1;
        Set<Integer> ls=new HashSet<>();
        for(int i=1;i<n;i++){
            int next_ugly=Integer.MAX_VALUE;
            for(int j=0;j<primes.length;j++){
                if(primes[j]*result[idx[j]]==next_ugly) ls.add(j);
                else if(primes[j]*result[idx[j]]<next_ugly){
                    next_ugly=primes[j]*result[idx[j]];
                    ls.clear();
                    ls.add(j);
                }
            }
            result[i]=next_ugly;
            for(int k:ls) idx[k]++;
        }
        return result[n-1];    
    }
}