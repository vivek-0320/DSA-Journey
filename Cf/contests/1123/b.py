def solve():
    n = int(input())
    a = list(map(int, input().split(" ")))
    freq = [0]*101
    for num in a:
        freq[num]+=1
        
    for i in range(100,0,-1):
        for j in range(100,0,-1):
            if freq[j] != 0:
                print(j,end=" ")
                freq[j]-=1
    
    print()
    
   



t = int(input())
while t != 0:
    solve()
    t -= 1
