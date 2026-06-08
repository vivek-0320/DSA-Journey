#include <bits/stdc++.h>
using namespace std;

int minimumTotal(vector<vector<int>> &triangle)
{
    int n = triangle.size();
    int limit = 100001;
    for (int i = 1; i < n; i++)
    {
        for (int j = 0; j <= i; j++)
        {
            int curr = triangle[i][j];
            int up = j < i ? triangle[i - 1][j] : limit;
            int behind = j - 1 >= 0 ? triangle[i - 1][j - 1] : limit;
            triangle[i][j] = min(up + curr, behind + curr);
        }
    }
    return *min_element(triangle[n - 1].begin(), triangle[n - 1].end());
}

int main()
{
    return 0;
}