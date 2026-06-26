#include<bits/stdc++.h>
using namespace std;

vector<int> getRow(int rowIndex) 
{
    vector<int> ans(rowIndex+1,1);
    for(int i=1;i<rowIndex;i++)
    {
        for(int x=i;x>0;x--)
        {
            ans[x]+=ans[x-1];
        }
    }
    return ans;
}

int main()
{
    return 0;
}