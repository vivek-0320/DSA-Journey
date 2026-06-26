from typing import List
def candy(ratings: List[int]) -> int:
    n = len(ratings)
    left = [1] * n
    for i in range(n):
        if(i > 0 and ratings[i] > ratings[i-1]):
            left[i] = left[i-1]+1
    candies = 0
    right_count = 1
    for i in range(n-1,-1,-1):
        if(i<n-1 and ratings[i] > ratings[i+1]):
            right_count += 1
        else:
           right_count = 1
        candies += max(left[i],right_count)
    return candies

def candy_optimal_1(ratings: List[int]) -> int:
    up = down = peak = 0
    total = 1
    for i in range(1,len(ratings)):
        if ratings[i] > ratings[i-1]:
        # up slope
            up += 1
            peak = up
            down = 0
            total += up + 1
            
        elif ratings[i] < ratings[i-1]:
        # down slope
            down += 1
            up = 0
            total += down
        else:
        #flat slope
            up = down = peak = 0
            total += 1
        
        if down > peak:
            total += 1
        
    return total
def candy_optimal_2(ratings: List[int]) -> int:
    total = 1
    i = 1
    n = len(ratings)
    while i < n:
        if(ratings[i] == ratings[i-1]):
            total += 1
            i += 1
            continue
        peak = 1
        while(i < n and ratings[i] > ratings[i-1]):
            peak += 1
            total += peak
            i += 1
        down = 1
        while(i < n and ratings[i] < ratings[i-1]):
            total += down
            down += 1
            i += 1
        if(down > peak):
            total += (down - peak)
            
    return total
        


assert candy_optimal_2([1,0,2]) == 5  # 2,1,2
assert candy_optimal_2([1,2,2]) == 4  # 1,2,1
# edge cases
assert candy_optimal_2([5]) == 1
assert candy_optimal_2([1,2,3,4,5,3,1,1,4,3,2,1]) == 29
assert candy_optimal_2([5,4,3,2,1]) == 15  # symmetric
assert candy_optimal_2([1,3,4,5,2]) == 11