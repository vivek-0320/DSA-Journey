#include <bits/stdc++.h>
using namespace std;

bool isPossible(vector<int> &position, int m, int space)
{
    int can = position[0];
    for (int i = 1; i < position.size(); i++)
    {
        if (position[i] - can >= space)
        {
            m--;
            can = position[i];
        }
        if (m == 0)
            return true;
    }
    return false;
}

int maxDistance(vector<int> &position, int m)
{
    sort(position.begin(),position.end());
    int low = 0;
    int high = position[position.size()-1];
    while (low + 1 < high)
    {
        int mid = low + (high - low) / 2;
        if (isPossible(position, m-1, mid))
            low = mid;
        else
            high = mid;
    }
    return low;
}

int main()
{
    int n,m;
    cin >> n >> m;
    vector<int> v(n);
    for(auto &num:v)
        cin >> num;
    cout  << maxDistance(v,m) << endl;
    return 0;
}