def solve():
    n = int(input())
    a = list(map(int, input().split(" ")))

    odd = set()
    even = set()

    for i, num in enumerate(a):
        if i % 2 == 0:
            even.add(num)
        else:
            odd.add(num)

    flag = True

    for i in range(n, 1, -2):
        x1 = i
        x2 = i - 1

        if not ((x1 in even and x2 in odd) or (x1 in odd and x2 in even)):
            flag = False
            break

    if flag:
        print("YES")
    else:
        print("NO")


t = int(input())
while t != 0:
    solve()
    t -= 1
