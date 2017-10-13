/*
 1. find the possible symmetric line with the minimum and maximum x.
 2. for each point, calculate its reflection point with respect to the symmetric line, them check whether it is included in the given set.
 */

class Solution {
    public boolean isReflected(int[][] points) {
        int size=points.length;
        if(size==0) return true;
        
        HashSet<String> hs = new HashSet<>();
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        
        for(int[] p:points){
            min=Math.min(min,p[0]);
            max=Math.max(max,p[0]);
            String s1=p[0]+"_"+p[1];
            hs.add(s1);
        }
        
        double spl=(min+max)/2.0;
        for(int i=0;i<size;i++){
            int x=(int)(2*spl-points[i][0]);
            String s2=x+"_"+points[i][1];
            if(!hs.contains(s2)) return false;
        }
        return true;
    }
}