package programmers.lv2;

/*
타겟넘버 - lv2
소요시간 - 6분

p.s
- 연습차 새로운 아이디로 문제들을 다시 푸는중임
- 이전에 문제풀이는 12분 소요했고. 풀이방식은 동일하나 매개변수를 전역으로 사용했냐 차이임.
- 문제를 기억한 느낌은 아님.
- 다만 조건을 읽고 재귀방식의 더하기뺄셈으로 깊이우선탐색이 가장 기본적인 풀이방법이라고 생각함.
 */
public class Program2_43165 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.println(solution(new int[]{4, 1, 2, 1}, 4));
    }

    static int result, T;
    static int[] arr;

    public static int solution(int[] numbers, int target) {
        result = 0;
        T = target;
        arr = numbers;

        dfs(0, 0);

        return result;
    }

    private static void dfs(int depth, int sum) {
        if (depth == arr.length) {
            if (sum == T) result++;
            return;
        }

        dfs(depth + 1, sum + arr[depth]);
        dfs(depth + 1, sum - arr[depth]);
    }
}
