/*
 Keywords: HashMap, pair
 Use pair as keys to represent the slope. Map the key to the number of points on the line with this slope.
 */

class Solution {
    class Pair{
        public int first;
        public int second;
        public Pair(int a,int b){
            first=a;
            second=b;
        }
        @Override
	    public int hashCode(){ 
            return this.first*31+this.second;
        }
        @Override
	    public boolean equals(Object p){
            if(p instanceof Pair){
                Pair pp=(Pair) p;
                return (this.first==pp.first) && (this.second==pp.second);
            }
            else return false;
        }
    }
    public int maxPoints(Point[] points) {
        if(points.length<=2) return points.length;
        int res=0;
        HashMap<Pair,Integer> hm=new HashMap<Pair,Integer>();
        for(int i=0;i<points.length-1;i++){
            hm.clear();
            int max_points=1;
            int repeat=0;
            for(int j=i+1;j<points.length;j++){
                Pair p;
                if(points[j].x==points[i].x){
                    p=new Pair(0,1);
                    if(points[j].y==points[i].y){
                        repeat++;
                        continue;
                    }
                }
                else{
                    int gcd=GCD(points[j].x-points[i].x,points[j].y-points[i].y);
                    p=new Pair((points[j].x-points[i].x)/gcd,(points[j].y-points[i].y)/gcd);
                }
                if(!hm.containsKey(p)){
                    hm.put(p,2); 
                } 
                else{
                    hm.put(p,hm.get(p)+1);
                }
                max_points=Math.max(max_points,hm.get(p));
            }
            res=Math.max(res,max_points+repeat);
        }
        return res;
    }
    public int GCD(int a,int b){
        if(b==0) return a;
        else return GCD(b,a%b);
    }
}