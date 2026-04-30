#include <bits/stdc++.h>
using namespace std;

// 580C

int main()
{
    int n, d;
    cin >> n >> d;
    vector<vector<int>> ar(n, vector<int>(2));
    for (int i = 0; i < n; i++)
    {
        cin >> ar[i][0] >> ar[i][1];
    }
    sort(ar.begin(), ar.end(), [&](vector<int> &a, vector<int> &b)
         {
        if(a[0] != b[0])
            return a[0] < b[0];
        return a[1] > b[1]; });
    int left = 0;
    long long running_ff = 0;
    long long max_ff = 0;

    for (int right = 0; right < n; right++)
    {
        while (ar[right][0] - ar[left][0] >= d)
        {
            running_ff -= ar[left][1];
            left++;
        }
        running_ff += ar[right][1];
        max_ff = max(max_ff, running_ff);
    }
    cout << max_ff << endl;

    return 0;
}