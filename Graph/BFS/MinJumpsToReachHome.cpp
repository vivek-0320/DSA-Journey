#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    struct State
    {
        int pos;
        int step;
        int jump; // 0 - forward, 1 - backward, -1 - start
    };

    int minimumJumps(vector<int> &forbidden, int a, int b, int x)
    {
        unordered_set<int> forbid(forbidden.begin(), forbidden.end());
        queue<State> q;

        // 2D Visited Array
        // vis[position][0] -> Visited by jumping forward
        // vis[position][1] -> Visited by jumping backward
        vector<vector<bool>> vis(6000, vector<bool>(2, false));

        q.push({0, 0, -1});
        vis[0][0] = true;
        vis[0][1] = true;

        while (!q.empty())
        {
            auto [current_pos, current_step, lastJump] = q.front();
            q.pop();

            if (current_pos == x)
            {
                return current_step;
            }

            int nextForward = current_pos + a;
            int nextBackward = current_pos - b;

            if (nextForward < 6000 && forbid.count(nextForward) == 0 && !vis[nextForward][0])
            {
                q.push({nextForward, current_step + 1, 0});
                vis[nextForward][0] = true;
            }

            if (nextBackward >= 0 && forbid.count(nextBackward) == 0 && !vis[nextBackward][1] && lastJump != 1)
            {
                q.push({nextBackward, current_step + 1, 1});
                vis[nextBackward][1] = true;
            }
        }
        return -1;
    }
};

int main()
{
    return 0;
}