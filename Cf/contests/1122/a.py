def solve():
    n = int(input())
    a1, a2, a3 = map(int, input().split(" "))
    print(n - min(a1, a2, a3))


t = int(input())
while t != 0:
    solve()
    t -= 1
