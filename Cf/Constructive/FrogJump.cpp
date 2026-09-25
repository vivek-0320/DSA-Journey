#include <bits/stdc++.h>
using namespace std;

void solve() {
    string s;
    cin >> s;
    vector<int> v;
    v.push_back(0);
    for(int i=0;i<s.size();i++)
    {
        if(s[i] == 'R')
            v.push_back(i+1);
    }
    v.push_back(s.size()+1);
    int minJump = 0;
    for(int i=1;i<v.size();i++)
        minJump = max(minJump,v[i]-v[i-1]);
    cout << minJump << endl;
}
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int t;
    cin >> t;
    while(t--)
    {
        solve();
    }

    return 0;
}