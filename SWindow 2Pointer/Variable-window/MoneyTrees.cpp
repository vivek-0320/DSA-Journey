#include<bits/stdc++.h>
using namespace std;

typedef long long ll;

int main()
{
    int t;
    cin >> t;
    while(t--)
    {
        ll n,k;
        cin >> n >> k;
        vector<int> fruits(n);
        for(int &f:fruits)
            cin >> f;
        vector<ll> heights(n);
        for(ll &h:heights)
            cin >> h;

        int l=0;
        int maxL = 0;
        ll sumFruits = 0LL;
        for(int r=0;r<n;r++)
        {
            if(r>0)
            {
                if(heights[r-1]%heights[r] != 0)
                {
                    l=r;
                    sumFruits = 0LL;
                }
            }

            sumFruits += fruits[r];
            while(sumFruits > k)
            {
                sumFruits -= fruits[l];
                l++;
            }
            if(l<=r)
                maxL = max(maxL, r - l + 1);
        }
        cout << maxL << endl;
        
    }
    return 0;
}