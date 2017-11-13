class Solution {
public int strongPasswordChecker(String s) {
    int res = 0, a = 1, A = 1, d = 1;
    char[] carr = s.toCharArray();
    int[] arr = new int[carr.length];
        
    for (int i = 0; i < arr.length;) {
        if (Character.isLowerCase(carr[i])) a = 0;
        if (Character.isUpperCase(carr[i])) A = 0;
        if (Character.isDigit(carr[i])) d = 0;
            
        int j = i;
        while (i < carr.length && carr[i] == carr[j]) i++;
        arr[j] = i - j;
    } 
    int total_missing = (a + A + d);
    if (arr.length < 6) {
        res += total_missing + Math.max(0, 6 - (arr.length + total_missing));       
    } else {
        int over_len = Math.max(arr.length - 20, 0), left_over = 0;
        res += over_len;
        // 所有重复的序列，解决时存在优先级，比如，3连续，4连续，如果只能删一个，一定是删3当中的。剩余的问题交给替换。
        // 解决的优先级为3m，比如3连续，6连续；其次3m+1;
        for (int k = 1; k < 3; k++) {
            for (int i = 0; i < arr.length && over_len > 0; i++) {
                if (arr[i] < 3 || arr[i] % 3 != (k - 1)) continue;
                arr[i] -= Math.min(over_len, k);
                over_len -= k;
            }
        }
        // 最后解决3m+2。所有的3m+2序列的优先级是一样的。不管是一个序列中删除3m，还是两个序列分别删除3(m/2)，是等价的。
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 3 && over_len > 0) {
                int need = arr[i] - 2;
                arr[i] -= over_len;
                over_len -= need;
            }
         // 对于没有删掉的，用替换解决，替换可以cover部分或者全部的大写小些数字的缺失问题。       
            if (arr[i] >= 3) left_over += arr[i] / 3;
        }
        res += Math.max(total_missing, left_over);
    }
    return res;
}
}
