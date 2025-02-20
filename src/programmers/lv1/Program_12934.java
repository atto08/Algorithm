package programmers.lv1;

/*
정수 제곱근 판별
소요시간 - 15분
분류 - 수학

문제 설명:
- 양의 정수 n에 대해 n이 어떤 양의 정수의 제곱인지 아닌지 판단하기
- 1 <= n <= 50000000000000

풀이 방법:
- Math.pow(n, m) = n의 m제곱 구하는 메서드 - double 타입
- Math.sqrt(n) = n의 제곱근 구하는 메서드 - double 타입
*/
public class Program_12934 {
    public long solution(long n) {
        return isPower(n);
    }

    private static long isPower(long n) {
        double num = Math.sqrt(n);

        if (num % 1 == 0) {
            return (long) Math.pow(num + 1, 2);
        }
        return -1;
    }

    public static void main(String[] args) {
        Program_12934 pg = new Program_12934();
        System.out.println(pg.solution(121));
        System.out.println(pg.solution(3));
    }
}
