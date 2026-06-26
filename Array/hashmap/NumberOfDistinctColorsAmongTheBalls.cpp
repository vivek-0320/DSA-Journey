#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    vector<int> queryResults(int limit, vector<vector<int>> &queries)
    {
        unordered_map<int, int> umap; // index -> color
        unordered_map<int,int> colorCount; // color -> no. of occurences
        vector<int> ans(queries.size());
        for (int i = 0; i < queries.size(); i++)
        {
            int index = queries[i][0], newColor = queries[i][1];
            if(umap.find(index) != umap.end() )
            {
                int prevColor = umap[index];
                if( --colorCount[prevColor]  == 0  )
                {
                    colorCount.erase(prevColor);
                }
            }
            umap[index] = newColor;
            colorCount[newColor]++;

            ans[i]=colorCount.size();
            
        }
        return ans;
    }
};

int main()
{

    return 0;
}