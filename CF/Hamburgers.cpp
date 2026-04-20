#include<bits/stdc++.h>
using namespace std;

typedef long long ll;

bool isGood(ll mid, ll b,ll s,ll c,ll nb,ll ns,ll nc,ll pb,ll ps,ll pc,ll r)
{
    ll reqB = mid * b;
    ll reqS = mid * s;
    ll reqC = mid * c;
    ll buyB = max(0LL,reqB - nb);
    ll buyS = max(0LL, reqS - ns);
    ll buyC = max(0LL,reqC - nc);
    ll moneyExtra = (buyB*pb) + (buyC*pc) + (buyS*ps);
    return moneyExtra <= r;
}

int main()
{
    string str;
    getline(cin,str);
    ll nb,ns,nc,pb,ps,pc,r;
    cin >> nb >> ns >> nc >> pb >> ps >> pc >> r;

    int b = 0, s = 0 , c = 0;
    for(char ch:str)
    {
        if(ch=='B')
            b++;
        else if(ch=='S')
            s++;
        else
            c++;
    }
    ll low = 0;
    ll high = 1e15;
    while(low+1 < high)
    {
        ll mid = low + (high-low)/2;
        if(isGood(mid,b,s,c,nb,ns,nc,pb,ps,pc,r))
            low = mid;
        else
            high = mid;
    }
    cout << low << endl;

    return 0;
}