package programmers.lv2;

import java.util.Arrays;

/*
k진수에서 소수 개수 구하기 - lv2
소요시간 - 1시간 초과

풀이 설명:
- k진수로 바꾸기
- 조건부 찾기
    - 쪼개기 ("0"을 기준으로 쪼개도 문제 X) -> 0을 포함한 소수는 필요없기 때문.

1차시도 - fail(11, 12, 14~16)
- 쪼개고 소수판별을 안함.

2차시도 - fail(1, 11)
- 에라토스테네스의 체를 구현하여 소수를 판별.
    -> 하지만 100만 이하의 n중에 10자리의 1을 갖는 이진수가 존재함. -> 너무 큰 범위
    -> 에라토스테네스의 체 구현 X -> 그때 그때 소수판별 O

3차시도 - pass

p.s
- 원소 체크 잘하자. - 에라토스테네스의 체 구현 중에 방문 체크 헛짓거리함.
- 소수를 일일히 판별하는게 더 빠를 수 있다. (범위에 따라서.)
*/
public class Program_92335 {
    public static int solution(int n, int k) {
        int result = 0;

        StringBuilder num = new StringBuilder();
        while (n >= k) {
            num.append(n % k);
            n /= k;
        }
        num.append(n).reverse();

        String[] arr = num.toString().split("0");
        Arrays.sort(arr);
        for (String str : arr) {
            if (str.isEmpty()) continue;
            // 소수 체크. 필수
            long now = Long.parseLong(str);
            if (isPrime(now)) result++;
        }

        return result;
    }

    public static boolean isPrime(long n) {
        if (n <= 1) return false;
        if (n == 2) return true;              // 2는 소수
        if (n % 2 == 0) return false;         // 2 제외한 짝수는 소수 아님

        long sqrt = (long) Math.sqrt(n);
        for (long i = 3; i <= sqrt; i += 2) { // 3부터 √n까지 홀수만 검사
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution(437674, 3));
        System.out.println(solution(110011, 10));
    }
}
