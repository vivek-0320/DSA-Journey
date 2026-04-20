#include <bits/stdc++.h>
using namespace std;
int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        int n;
        cin >> n;
        vector<int> v(n);
        for (auto &num : v)
            cin >> num;
        int mx = 0;
        for (int num : v)
            mx = max(mx, num);
        int c = 0;
        for (int num : v)
        {
            if(num == mx)
            c++;
        }
        cout << c << "\n";
    }
    return 0;
}