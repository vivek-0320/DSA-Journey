#include<bits/stdc++.h>
using namespace std;

void solve()
{
    int n;
    int *p = NULL;
    cin >> n;
    string s;
    cin >> s;
    stack<char> st;
    for(int i=0;i<n;i++)
    {
        char ch = s.at(i);
        if(!st.empty() && st.top() == ch)
        {
            st.pop();
        }
        else
        {
            st.push(ch);
        }
    }
    if(st.empty())
        cout << "YES\n";
    else
        cout << "NO\n";
}

int main()
{
    int t;
    cin >> t;
    while(t--)
    {
        solve();
    }
    return 0;
}