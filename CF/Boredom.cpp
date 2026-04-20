#include<bits/stdc++.h>
using namespace std;

int main()
{	
	ios_base::sync_with_stdio(false);
    cin.tie(NULL);
	int n;
	cin >> n;

	const int MAX_VAL = 100005;
    
    // Use long long because the score can easily exceed 2 billion
    vector<long long> count(MAX_VAL, 0);
    
    // Read the input and immediately build the frequency map
    long long max_element_in_input = 0;
    for (int i = 0; i < n; i++)
    {
        long long num;
        cin >> num;
        count[num]++;
        max_element_in_input = max(max_element_in_input, num);
    }

	vector<long long> dp(MAX_VAL, 0);
	dp[0] = 0;
    dp[1] = 1 * count[1];

	for (long long i = 2; i <= max_element_in_input; i++)
    {
        // The core recurrence relation
        long long pick_i = dp[i - 2] + (i * count[i]);
        long long skip_i = dp[i - 1];
        
        dp[i] = max(skip_i, pick_i);
    }

    // The answer is the max score considering all numbers up to the largest one
    cout << dp[max_element_in_input] << "\n";
}