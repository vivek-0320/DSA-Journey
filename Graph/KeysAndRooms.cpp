#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    bool canVisitAllRooms(vector<vector<int>> &rooms)
    {
        int n = rooms.size();
        vector<bool> keys(n, false);
        keys[0] = true;
        stack<int> st;
        st.push(0);
        while(!st.empty())
        {
            int curr = st.top(); st.pop();
            for(auto key : rooms[curr])
            {
                keys[key] = true;
                st.push(key);
            }
        }
        for(int i=0;i<n;i++)
            if(!keys[i])
                return false;

        return true;
    }
};

int main()
{
    return 0;
}