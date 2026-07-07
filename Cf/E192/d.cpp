#include <bits/stdc++.h>
using namespace std;

vector<string> getNeighbours(string a)
{
    vector<string> res;
    for (int i = 0; i < a.length() - 1; i++)
    {
        int d1 = a[i] - '0';
        int d2 = a[i + 1] - '0';
        int x = d1 + d2 % 10;
        string n = a.substr(0, i) + (char)(x + '0') + a.substr(i + 1);
        res.push_back(n);
    }
    return res;
}

void solve()
{
    string a, b;
    cin >> a;
    cin >> b;
    queue<string> que;
    unordered_set<string> vis;
    vis.insert(a);
    que.push(a);
    while (!que.empty())
    {
        string curr = que.front();
        que.pop();
        vector<string> processed = getNeighbours(curr);
        vis.insert(processed.begin(), processed.end());
        if (processed[0].length() > 1)
        {
            for (string s : processed)
                que.push(s);
        }
    }
    que.push(b);
    while (!que.empty())
    {
        string curr = que.front();
        que.pop();
        if (vis.count(curr) != 0)
        {
            cout << curr.length() << endl;
            return;
        }
        vector<string> processed = getNeighbours(curr);
        if (processed[0].length() > 1)
        {
            for (string s : processed)
                que.push(s);
        }
    }
    cout << -1 << endl;
    return;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    cin >> t;
    while (t--)
    {
        solve();
    }
    return 0;
}