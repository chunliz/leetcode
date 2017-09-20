/*
 The idea is:
 1. to map each string to a list of positions, and these positions should be in order. 
 
 2. Each time we consider the distance of two words, we take their corresponding lists and compare the positions (list1[i] and list2[j]) in these two lists.
        When list1[i]<=list2[j], i++;
        when list1[i]>list2[j],j++.
*/
