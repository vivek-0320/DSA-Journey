import sys

def solve():
    input_data = sys.stdin.read().split()
    if not input_data:
        return
        
    t = int(input_data[0])
    out = []
    idx = 1
    
    for _ in range(t):
        n = int(input_data[idx])
        x = int(input_data[idx+1])
        idx += 2
        
        a = [int(val) for val in input_data[idx : idx+n]]
        idx += n
        
        primes = []
        temp = x
        d = 2
        while d * d <= temp:
            if temp % d == 0:
                primes.append(d)
                while temp % d == 0:
                    temp //= d
            d += 1
        if temp > 1:
            primes.append(temp)
            
        max_stolen = 0
        for p in primes:
            current_sum = sum(coin for coin in a if coin % p == 0)
            if current_sum > max_stolen:
                max_stolen = current_sum
                
        out.append(str(max_stolen))
        
    sys.stdout.write('\n'.join(out) + '\n')

if __name__ == '__main__':
    solve()