package programmers;
/*
N개의 최소 공배수 - level2
소요 시간: x

최대 공약수와 최소 공배수를 구하는 계산식을 몰라서 찾아봄
 */
public class Program_12953 {
    public int solution(int[] arr) {
        int result = arr[0];

        for (int i = 1; i < arr.length; i++) result = lcm(result, arr[i]);

        return result;
    }

    // 최대 공약수(GCD)를 구하는 메소드
    private static int gcd(int a, int b) {
        if (b == 0) return a;

        return gcd(b, a % b);
    }

    // 최소 공배수(LCM)를 구하는 메소드
    private static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
}
