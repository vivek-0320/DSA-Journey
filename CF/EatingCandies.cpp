#include<bits/stdc++.h>
using namespace std;

// 1669F CF

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int t;
    cin >> t;
    while(t--)
    {
        int n;
        cin >> n;
        vector<int> v(n);
        for(auto &num:v)
            cin >> num;
        
        int left = 0;
        int right = n-1;
        int toffee = 0;
        while(left < right)
        {
            int sumAlice = v[left];
            int sumBob = v[right];
            int toffeeA = 1;
            int toffeeB = 1;
            while (left < right && sumAlice != sumBob)
            {
               if(sumAlice < sumBob)
               {
                    left++;
                    sumAlice += v[left];
                    toffeeA++;
               }
               else {
                    right--;
                    sumBob += v[right];
                    toffeeB++;
               }
            }
            
            if(sumAlice == sumBob)
            {
                left++;
                right--;
                toffee += (toffeeA+toffeeB);
            }
        }

        cout << toffee << endl;
    }
    return 0;
}