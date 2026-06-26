#include <bits/stdc++.h>
using namespace std;

// Updated mid and lineLength to long long to prevent truncation and overflow
bool isPossible(const vector<int> &nums, long long mid, int maxLines)
{
    int currLines = 1;
    long long lineLength = nums[0]; 
    
    for(size_t i = 1; i < nums.size(); i++)
    {
        int word = nums[i];

        if(lineLength + word + 1 <= mid)
            lineLength += word + 1;
        else
        {
            currLines++;
            lineLength = word;
        }
    }
    return currLines <= maxLines;
}

int main() {
    // Optimize standard I/O operations for competitive programming
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    if (!(cin >> n >> m)) return 0;
    
    vector<int> v(n);
    for(int &num : v)
        cin >> num;

    long long low = *max_element(v.begin(), v.end()) - 1;
    
    // CRITICAL FIX: 0LL forces the accumulator to use 64-bit integers
    long long high = accumulate(v.begin(), v.end(), 0LL) + (n - 1);
    
    while(low + 1 < high)
    {
        long long mid = low + (high - low) / 2;
        if(isPossible(v, mid, m))
            high = mid; // mid is valid, try to find a smaller one
        else
            low = mid;  // mid is invalid, we need a larger width
    }
    
    cout << high << "\n";
    return 0;
}