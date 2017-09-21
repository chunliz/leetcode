/*
 References:
 https://discuss.leetcode.com/topic/49041/no-depth-variable-no-multiplication
 
 From integer to integer nested in lists, weight is decreasing. 
 1. use a unweighted sum, and keep extending it from top level to bottom.
 2. At each step, add the unweighted sum to weighted sum. Thus the earlier the integer is added to unweighted sum, the more times it will be added to the weighted sum.
 3. return the weighted sum.
 */
