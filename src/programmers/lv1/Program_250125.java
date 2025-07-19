package programmers.lv1;

/*
이웃한 칸 - lv1
소요시간 - 13분
2차원 배열 중 고른 칸(h, w)에 이웃한 칸 중 같은 색으로 칠해져있는 칸의 갯수 구하기

- bfs로 (h,w)의 방문여부 체크하면서 색이 같은 경우만 ++

p.s
- 좌표(h,w) 기준으로 정말 상하좌우만 탐색하는거였다.
-> 나는 bfs로 붙어있는 모든 같은 색상을 구했었는데, 이것보다 단순한 문제였다.
*/

public class Program_250125 {
    public static int solution(String[][] board, int h, int w) {
        int result = 0;
        int N = board.length, M = board[0].length;

        int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
        for (int i = 0; i < 4; i++) {
            int nx = h + dx[i];
            int ny = w + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

            if (board[h][w].equals(board[nx][ny])) {
                result++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[][]{{"blue", "red", "orange", "red"},
                {"red", "red", "blue", "orange"},
                {"blue", "orange", "red", "red"},
                {"orange", "orange", "red", "blue"}}, 1, 1));
        System.out.println(solution(new String[][]{{"yellow", "green", "blue"},
                {"blue", "green", "yellow"},
                {"yellow", "blue", "blue"}}, 0, 1));
    }
}
