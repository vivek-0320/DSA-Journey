#include <bits/stdc++.h>
using namespace std;

vector<int> successfulPairs(vector<int> &spells, vector<int> &potions, long long success)
{
    vector<int> ans(spells.size());
    sort(potions.begin(), potions.end());
    int n = potions.size();
    for (int i = 0; i < spells.size(); i++)
    {
        long long low = -1;
        long long high = n - 1;
        if (success <= high*spells[i])
        {
            while (low + 1 < high)
            {
                long long mid = low + (high - low) / 2;
                if(potions[mid]*spells[i] >= success)
                    high = mid;
                else
                    low=mid;
            }
            ans[i] = (n - high);
        }
        else
        {
            ans[i] = 0;
        }
    }
    return ans;
}

int main()
{
    return 0;
}