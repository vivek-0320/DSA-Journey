#include <bits/stdc++.h>
using namespace std;

int maxCapacity(vector<int> &costs, vector<int> &capacity, int budget)
{
    int n = (int)costs.size();
    vector<pair<int,int>> a; // (cost, cap)
    a.reserve(n);
    for (int i = 0; i < n; i++) a.push_back({costs[i], capacity[i]});
    sort(a.begin(), a.end());
    vector<int> best1(n), best2(n), best1Idx(n), best2Idx(n);

    for (int i = 0; i < n; i++) {
        int cap = a[i].second;

        if (i == 0) {
            best1[i] = cap; best1Idx[i] = i;
            best2[i] = 0;   best2Idx[i] = -1;
            continue;
        }
        best1[i] = best1[i-1]; best1Idx[i] = best1Idx[i-1];
        best2[i] = best2[i-1]; best2Idx[i] = best2Idx[i-1];

        if (cap >= best1[i]) {
            best2[i] = best1[i]; best2Idx[i] = best1Idx[i];
            best1[i] = cap;       best1Idx[i] = i;
        } else if (cap > best2[i]) {
            best2[i] = cap; best2Idx[i] = i;
        }
    }

    int ans = 0;

    for (int i = 0; i < n; i++) {
        int c = a[i].first;
        int cap = a[i].second;
        if (c < budget) ans = max(ans, cap);
        int rem = budget - 1 - c;
        if (rem < 0) continue;

        int j = upper_bound(a.begin(), a.end(), make_pair(rem, INT_MAX)) - a.begin() - 1;
        if (j < 0) continue;
        int partner = 0;
        if (best1Idx[j] != i) partner = best1[j];
        else partner = best2[j];

        if (partner > 0) ans = max(ans, cap + partner);
    }

    return ans;
}

int main()
{
    return 0;
}