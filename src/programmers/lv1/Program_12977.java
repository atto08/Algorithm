package programmers.lv1;

/*
소수 만들기 - lv1
소요시간 - 30분

풀이설명:
- 완전탐색으로 만들수 있는 모든 경우의 수를 구해야됨
- 모든 경우의 수에 대해 소수판별 시행

p.s
- 소수판별하는 메서드의 규칙 정리한 노트 다시 봄
- 완탐 중에 조합이 되는 경우만 고르는 부분 좀 생각함
*/
public class Program_12977 {
    static boolean[] visited;
    static int result, L;

    public static int solution(int[] nums) {

        L = nums.length;
        for (int i = 0; i <= L - 3; i++) {
            visited = new boolean[L];
            visited[i] = true;
            dfs(1, i, nums[i], nums);
        }

        return result;
    }

    private static void dfs(int depth, int node, int sum, int[] nums) {
        if (depth == 3) {
            if (isPrime(sum)) result++;
            return;
        }

        for (int i = node + 1; i < L; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, i, sum + nums[i], nums);
                visited[i] = false;
            }
        }
    }

    private static boolean isPrime(long num) {
        if (num <= 1) return false;
        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 2, 3, 4}));
        System.out.println(solution(new int[]{1, 2, 7, 6, 4}));
    }
}
