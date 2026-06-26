#include<bits/stdc++.h>
using namespace std;

vector<vector<int>> generate(int numRows) {
    vector<vector<int>> ans;
    for(int i=1;i<=numRows;i++)
    {
        vector<int> row(i);
        for(int x=0;x<i;x++)
        {
            if(x==0 || x==i-1)
            {
                row[x]=1;
            }
            else
            {
                row[x]=(ans[i-2][x-1] + ans[i-2][x]);
            }
        }
        ans.push_back(row);
    }
    return ans;
}

int main()
{

    return 0;
}