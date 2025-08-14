package programmers.lv3;

/*
파괴되지 않은 건물 - lv3
소요시간 - 1시간 초과

풀이설명:
1차시도 - 구현
- 250,000(skill최대) * 10^6 -> 너무 큰 범위임
** 효율성 시간초과 예상 **

- skill.length 동안
- r1~r2와 c1~c2를 돌면서 type에 따라 degree만큼 증감

2차시도 - 누적합

p.s
- 어렵다. 이해하는데 1시간 넘게 소요했다.
- 꼭짓점[(r1,c1), (r1,c2+1), (r2+1,c1), (r2+1,c2+1)]에 대해 +-degree 하는 것 까지는 풀이설명을 보고 이해함.
- 하지만, 행과 열에 대해서 누적합을 더하는 것을 이해하지 못했었음.
- 예제1에 대해서 예시를 눈으로 보면서 이해할 수 있었음.
- 내일은 누적합이다..
*/
public class Program_92344 {
    public static int solution(int[][] board, int[][] skill) {

        int N = board.length, M = board[0].length;
        int[][] add = new int[N + 1][M + 1];

        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1], c1 = s[2], r2 = s[3], c2 = s[4];

            int degree = (type == 1) ? -s[5] : s[5];
            add[r1][c1] += degree;
            add[r1][c2 + 1] -= degree;
            add[r2 + 1][c1] -= degree;
            add[r2 + 1][c2 + 1] += degree;
        }

        // 행 누적합
        for (int r = 0; r <= N; r++) {
            int sum = 0;
            for (int c = 0; c <= M; c++) {
                sum += add[r][c];
                add[r][c] = sum;
            }
        }

        // 열 누적합
        for (int c = 0; c <= M; c++) {
            int sum = 0;
            for (int r = 0; r <= N; r++) {
                sum += add[r][c];
                add[r][c] = sum;
            }
        }

        int result = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                board[r][c] += add[r][c];
                if (board[r][c] > 0) result++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, new int[][]{{1, 1, 1, 2, 2, 4}, {1, 0, 0, 1, 1, 2}, {2, 2, 0, 2, 0, 100}}));
        System.out.println(solution(new int[][]{{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}}, new int[][]{{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}}));
    }
}