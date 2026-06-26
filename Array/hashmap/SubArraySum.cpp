#include<bits/stdc++.h>
using namespace std;


int main()
{
    vector<int> v = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

    int sum=0;
    int maxSum = INT_MIN;
    int i=-1,j=-1;
    for(int x=0;x<v.size();x++)
    {
        if(sum == 0)    i=x;
        sum+=v[x];
        if(sum>maxSum)
        {
            maxSum = sum;
            j=x;
        }
        if(sum < 0)
        {
            sum=0;
        }
    }

    cout << maxSum << endl;

    for(int x=i; x <= j ; x++ )
        cout << v[x] << " "; 
    cout << endl;

    return 0;
}