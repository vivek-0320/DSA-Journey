#include <bits/stdc++.h>
using namespace std;

int maxElementInRow(vector<vector<int>> &mat, int n, int mid)
{
    int max = mat[0][mid];
    int index = 0;
    for (int i = 1; i < n; i++)
    {
        if (mat[i][mid] > max)
        {
            max = mat[i][mid];
            index = i;
        }
    }
    return index;
}
vector<int> findPeakGrid(vector<vector<int>> &mat)
{
    vector<int> ans(2,-1);
    int n = mat.size();
    int m = mat[0].size();
    int l = 0;
    int h = m - 1;
    while (l <= h)
    {
        int mid = l + (h - l) / 2;
        int row = maxElementInRow(mat,n,mid);
        int left = mid > 0 ? mat[row][mid - 1] : -1;
        int right = mid < m - 1 ? mat[row][mid + 1] : -1;
        if (mat[row][mid] > left && mat[row][mid] > right)
        {
            ans[0] = row;
            ans[1] = mid;
            break;
        }
        else if (left < mat[row][mid])
            l = mid + 1;
        else
            h = mid - 1;
    }
    return ans;
}

int main()
{
    int n, m;
    cin >> n >> m;
    vector<vector<int>> mat(n, vector<int>(m));
    for (auto &row : mat)
        for (auto &num : row)
            cin >> num;
    vector<int> ans = findPeakGrid(mat);
    cout << ans[0] << " , " << ans[1] << endl;
    return 0;
}