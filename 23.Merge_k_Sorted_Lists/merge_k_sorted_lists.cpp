/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
  ListNode* mergeKLists(vector<ListNode*>& lists) {
    if(lists.size()==0) return nullptr;
    if(lists.size()==1) return lists[0];
    vector<ListNode*> l1(lists.begin(),lists.begin()+lists.size()/2);
    vector<ListNode*> l2(lists.begin()+lists.size()/2,lists.end());
    return mergeTwoLists(mergeKLists(l1),mergeKLists(l2));
  }
    
private:
  ListNode* mergeTwoLists(ListNode* p1, ListNode* p2){
    if(p1==nullptr) return p2;
    if(p2==nullptr) return p1;
    ListNode dummy(-1);
    ListNode *prev=&dummy;
        
    while(p1 && p2){
      if(p1->val <= p2->val){
	prev->next=p1;
	p1=p1->next;
	prev=prev->next;
      }
      else{
	prev->next=p2;
	p2=p2->next;
	prev=prev->next;
      }
    }
    if(p1) prev->next=p1;
    if(p2) prev->next=p2;
    return dummy.next;
  }
};
