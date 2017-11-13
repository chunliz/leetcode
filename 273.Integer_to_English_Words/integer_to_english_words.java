class Solution {
    private final String[] digits={"One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
    private final String[] tens={"Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
    private final String[] thousand={"","Thousand","Million","Billion"};
    public String numberToWords(int num) {
        if(num==0) return "Zero";
        String result="";
        int i=0;
        while(num!=0){
            if(num%1000!=0) result=helper(num%1000)+" "+thousand[i]+" "+result;
            num/=1000;
            i++;
        }
        return result.trim();
    }
    private String helper(int num){
        if(num==0) return "";
        else if(num<20) return digits[num-1];
        else if(num<100) return (tens[num/10-2]+" "+helper(num%10)).trim();
        else return (digits[num/100-1]+" Hundred "+helper(num%100)).trim();
    }
}