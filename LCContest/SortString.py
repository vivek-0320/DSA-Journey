class Solution:
    def minimumOperations(self, s: str) -> int:
        n = len(s)
        
        # 1. If the string is already sorted, 0 operations are needed.
        if all(s[i] <= s[i+1] for i in range(n - 1)):
            return 0
            
        # 2. If length is 2 and not sorted, it's impossible to fix.
        if n <= 2:
            return -1
            
        # Get the target sorted version of the string
        a = sorted(s)
        
        # Find the boundaries of the unsorted portion
        left = 0
        while left < n and s[left] == a[left]:
            left += 1
            
        right = n - 1
        while right >= 0 and s[right] == a[right]:
            right -= 1
            
        # 3. Can we do it in 1 operation?
        # If the mismatched portion is strictly smaller than the whole string,
        # we can just sort that specific substring.
        if right - left + 1 < n:
            return 1
            
        # 4. Do we need 3 operations?
        # This only happens when the absolute maximum element is uniquely at the start,
        # AND the absolute minimum element is uniquely at the end.
        max_char = a[-1]
        min_char = a[0]
        
        if (s[0] == max_char and s.count(max_char) == 1 and 
            s[-1] == min_char and s.count(min_char) == 1):
            return 3
            
        # 5. Otherwise, 2 operations are definitively sufficient.
        return 2