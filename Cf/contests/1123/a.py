def solve():
    n,c = input().split(" ")
    s = input()
    
    n = int(n)
    l = 0
    r = n-1
    
    count  = 0
    while l < r:
        if s[l] != s[r]:
            if s[l] == c or s[r] == c:
                count += 1
            else:
                count += 2
        l+=1
        r-=1
    print(count)
    
    


t = int(input())
while t != 0:
    solve()
    t -= 1
