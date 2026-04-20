#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string s;
    if (!getline(cin, s)) return 0;

    // Fix 1: Removed the stray semicolon
    if (s.size() < 4)
    {
        cout << "NO\n";
        return 0;
    }

    // Scenario 1: Look for "AB" first, then "BA"
    size_t ab_first = s.find("AB");
    if (ab_first != string::npos) 
    {
        // Search for "BA" starting 2 positions AFTER the found "AB"
        size_t ba_after = s.find("BA", ab_first + 2);
        if (ba_after != string::npos)
        {
            cout << "YES\n";
            return 0;
        }
    }

    // Scenario 2: Look for "BA" first, then "AB"
    // This catches edge cases like "BABAB"
    size_t ba_first = s.find("BA");
    if (ba_first != string::npos)
    {
        // Search for "AB" starting 2 positions AFTER the found "BA"
        size_t ab_after = s.find("AB", ba_first + 2);
        if (ab_after != string::npos)
        {
            cout << "YES\n";
            return 0;
        }
    }

    // If neither scenario worked
    cout << "NO\n";
    return 0;
}