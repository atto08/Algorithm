package programmers.lv1;

/*
소수 찾기 - lv1
소요 시간 - 7분

풀이 설명:
- 에라토스테네스의 체 사용해봄

p.s
- 확실히 지정된 범위가 존재하고 N이하의 모든 소수를 세는 경우는 에라토스테네스의 체가 빠르다.
 */
public class Program_12921 {
    static boolean[] isPrime, visited;

    public static int solution(int n) {
        int result = 0;

        isPrime = new boolean[n + 1];
        visited = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            if (!visited[i]) {
                visit(i, n);
                result++;
            }
        }

        return result;
    }

    private static void visit(int p, int n) {

        isPrime[p] = true;
        for (int i = p * 2; i <= n; i += p) {
            visited[i] = true;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(10));
        System.out.println(solution(5));
    }
}
