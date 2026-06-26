#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    static void rotate(vector<int>& nums, int k) 
    {
        k %= nums.size();        
        reverse(nums.begin(),nums.end());
        reverse(nums.begin(),(nums.begin()+k));
        reverse((nums.begin()+k),nums.end());
    }
};

int main()
{
    vector<int> v = {1,2,3,4,5,6,7};
    Solution::rotate(v,3);
    for(auto &num:v)
    {
        cout << num << " ";
    }
    cout << endl;
}