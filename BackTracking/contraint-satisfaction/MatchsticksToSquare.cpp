#include <bits/stdc++.h>
using namespace std;

bool backtrack(vector<int> &matchsticks, vector<int> &sides, int sideLength, int index)
{
    if (index == matchsticks.size())
    {
        return true;
    }
    for (int i = 0; i < 4; i++)
    {
        if (sides[i] + matchsticks[index] <= sideLength)
        {
            sides[i] += matchsticks[index];
            bool ans = backtrack(matchsticks, sides, sideLength, index + 1);
            if (ans)
                return ans;
            sides[i] -= matchsticks[index];
        }
    }
    return false;
}

bool makesquare(vector<int> &matchsticks)
{
    int sum = accumulate(matchsticks.begin(), matchsticks.end(), 0);
    if (sum % 4 != 0)
        return false;
    sort(matchsticks.begin(), matchsticks.end(), [](int a, int b)
         { return a > b; });
    vector<int> sides(4, 0);
    return backtrack(matchsticks, sides, sum / 4, 0);
}

int main()
{
    int n;
    cin >> n;
    vector<int> v(n);
    for (auto &num : v)
        cin >> num;
    return 0;
}