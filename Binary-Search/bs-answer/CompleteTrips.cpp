#include <bits/stdc++.h>
using namespace std;

bool isPossible(vector<int> &time, int totalTrips, int val)
{
    int trips = 0;
    for (int i = 0; i < time.size(); i++)
    {
        trips += val / time[i];
        if (trips == totalTrips)
            return true;
    }
    return false;
}

long long minimumTime(vector<int> &time, int totalTrips)
{
    sort(time.begin(), time.end());
    long long low = 0;
    long long high = time[0] * totalTrips;
    while (low + 1 < high)
    {
        long long mid = low + (high - low) / 2;
        if (isPossible(time, totalTrips, mid))
            high = mid;
        else
            low = mid;
    }
    return high;
}

int main()
{
    int n, m;
    cin >> n >> m;
    vector<int> v(n);
    for (auto &num : v)
        cin >> num;
    cout << minimumTime(v, m) << endl;
    return 0;
}