#include<bits/stdc++.h>
using namespace std;


int main()
{
    vector<int> v = {10, 22, 12, 3, 0, 6};
    int n = v.size();
    int max=0;
    vector<int> ans;
    for(int i = n-1;i>=0;i--)
    {
        if(v[i]>max)
        {
            ans.push_back(v[i]);
            max = v[i];
        }
    }
    reverse(ans.begin(),ans.end());
    for(auto &num:ans)
        cout << num << " ";


    return 0;
}