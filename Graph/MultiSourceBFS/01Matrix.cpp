#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> updateMatrix(vector<vector<int>> &mat)
{
    int r = mat.size();
    int c = mat[0].size();
    vector<int> dx = {1, -1, 0, 0};
    vector<int> dy = {0, 0, 1, -1};
    vector<vector<int>> res(r, vector<int>(c, -1));
    queue<pair<int, int>> que;
    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            if (mat[i][j] == 0) {
                que.push({i, j});
                res[i][j] = 0; // Distance to itself is 0
            }
        }
    }

    while (!que.empty())
    {
        auto [x , y] = que.front();
        que.pop();
        for (int k = 0; k < 4; k++)
        {
            int nx = dx[k] + x;
            int ny = dy[k] + y;
            if (nx > -1 && nx < r && ny > -1 && ny < c && res[nx][ny] == -1)
            {
                res[nx][ny] == res[x][y] + 1;
                que.push({nx, ny});
            }
        }
    }
    return res;
}

int main()
{
    return 0;
}