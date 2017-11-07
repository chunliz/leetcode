class Solution {
public:
  int maxPoints(vector<Point>& points) {
    if(points.size()<3) return points.size();
    int result=0;
        
    map<pair<int,int>,int> slope_count;
    for(int i=0; i<points.size()-1; i++){
      slope_count.clear();
      int same_point=0;
      int point_max=1;
      for(int j=i+1; j<points.size(); j++){
	pair<int,int> slope;
	if(points[i].x==points[j].x){
	  slope=make_pair(0,1);
	  if(points[i].y==points[j].y){
	    ++same_point; 
	    continue;
	  }
	}
	else{
	  int gcd=GCD(points[j].x-points[i].x,points[j].y-points[i].y);
	  slope=make_pair((points[j].x-points[i].x)/gcd,(points[j].y-points[i].y)/gcd);
	}
	int count=0;
	if(slope_count.find(slope)!=slope_count.end()){
	  count=++slope_count[slope];}
	else{
	  slope_count[slope]=2;
	  count=2;
	}
	if(point_max<count) point_max=count;
      }
      result=max(result,point_max+same_point);
    }
    return result;
  }
private:
  int GCD(int a, int b){
    if(b==0) return a;
    return GCD(b,a%b);
  }
};
