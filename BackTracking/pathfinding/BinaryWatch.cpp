#include <bits/stdc++.h>
using namespace std;

vector<int> generateHours(int bitsOn)
{
    vector<int> validHours;
    for (int i = 0; i < 12; i++)
    {
        if (__builtin_popcount(i) == bitsOn)
            validHours.push_back(i);
    }
    return validHours;
}

vector<int> generateMins(int bitsOn)
{
    vector<int> validMins;
    for (int i = 0; i < 60; i++)
    {
        if (__builtin_popcount(i) == bitsOn)
            validMins.push_back(i);
    }
    return validMins;
}

vector<string> readBinaryWatch(int turnedOn)
{
    vector<string> result;
    for (int i = 0; i <= turnedOn ; i++)
    {
        vector<int> mins = generateMins(turnedOn - i);
        vector<int> hours = generateHours(i);

        for (int h : hours)
        {
            for (int m : mins)
            {
                string time = to_string(h)+":"+ ( m < 10 ? "0" : "" ) + to_string(m);
                result.push_back(time);
            }
        }
    }
    return result;
}

int main()
{
    int n;
    cin >> n;
    vector<string> ans = readBinaryWatch(n);
    for (auto str : ans)
        cout << str << endl;
    return 0;
}

/*  Recursive Approach

#include <iostream>
#include <vector>
#include <string>

using namespace std;

vector<string> result;

void backtrack(int index, int turnedOn, vector<int>& onLEDs) {
    // Base case: when we've turned on the required number of LEDs
    if (onLEDs.size() == turnedOn) {
        int hour = 0, minute = 0;

        for (int bit : onLEDs) {
            if (bit < 4)
                hour += (1 << bit);       // bits 0–3 for hour
            else
                minute += (1 << (bit - 4)); // bits 4–9 for minute
        }

        if (hour < 12 && minute < 60) {
            string time = to_string(hour) + ":" + (minute < 10 ? "0" : "") + to_string(minute);
            result.push_back(time);
        }
        return;
    }

    // Try all remaining LEDs from current index
    for (int i = index; i < 10; i++) {
        onLEDs.push_back(i);
        backtrack(i + 1, turnedOn, onLEDs);
        onLEDs.pop_back(); // backtrack
    }
}

vector<string> readBinaryWatch(int turnedOn) {
    result.clear();
    vector<int> onLEDs;
    backtrack(0, turnedOn, onLEDs);
    return result;
}

// Optional: For testing
int main() {
    int turnedOn = 2;
    vector<string> times = readBinaryWatch(turnedOn);

    for (const string& time : times) {
        cout << time << endl;
    }

    return 0;
}



*/