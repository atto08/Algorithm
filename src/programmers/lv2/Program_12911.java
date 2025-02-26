package programmers.lv2;
/*
다음 큰 숫자 - lv2
소요 시간 - 20분

문제 설명:
- n은 자연수 아래 조건을 만족하는 n의 다음 큰 숫자를 구하기
- 조건 1. n의 다음 큰 숫자는 n보다 큰 자연수
- 조건 2. n의 다음 큰 숫자와 n은 2진수로 변환했을 때 1의 갯수가 동일
- 조건 3. n의 다음 큰 숫자는 조건 1, 2를 만족하는 수 중 가장 작은 수

풀이 설명:
- Integer 클래스에 toBinaryString(int n) 메서드 사용 (배웠다)
    - 이걸로 시간 단축
- 10진수 n을 2진법 문자열로 변환 후 '1' 개수 파악
- n을 증가시키며 1의 개수가 같은 n보다 큰 수 리턴
 */
public class Program_12911 {

    public int solution(int n) {
        char[] arr = Integer.toBinaryString(n).toCharArray();
        int cnt = 0;
        for (char b : arr) {
            if (b == '1') cnt++;
        }

        return findNextBigNumber(n + 1, cnt);
    }

    private static int findNextBigNumber(int n, int cnt) {

        while (true) {
            char[] arr = Integer.toBinaryString(n).toCharArray();
            int cnt1 = 0;
            for (char b : arr) {
                if (b == '1') cnt1++;
            }

            if (cnt1 == cnt) break;

            n++;
        }

        return n;
    }

    public static void main(String[] args) {
        Program_12911 pg = new Program_12911();
        System.out.println(pg.solution(78));
        System.out.println(pg.solution(15));
    }
}
