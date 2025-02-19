package programmers.lv3;

/*
등굣길 - lv3
소요시간 - 1시간 초과
분류 - DP

문제 설명:
- m * n 크기의 경로 존재
- 출발지(1, 1), 도착지(m, n)인 학교
- 폭우로 일부지역이 잠겨서 지나갈 수 X & 우측과 아래방향으로만 이동 가능
- 잠긴 지역(puddles)을 피해 학교로 갈 수 있는 최단경로의 개수를 구하기
- 정답 % 1,000,000,007 리턴

풀이 방법:
1차 시도 - 시간초과 발생 (46분 소요)
- BFS로 최단경로 탐색을 시도
- 이동 가능한 1칸당 우&하 2가지 경우가 계속해서 늘어나기 때문에 시간복잡도 = O(2^mn)인 경우에는 시간초과 발생

2차 시도 - 통과 -> 질문하기 참고했음
- 시작점부터 이동하는 칸 기준으로 올 수 있는 경우를 구해야한다고 생각함
-> ? 어떻게 구현할지 감을 못잡았음 --> 질문하기 예제 하나 잡고 읽고 이해
    - 시작점 (1, 1) == 1로 시작
    - (1, 1) ~ (m, n)까지 1 ~ m & 1 ~ n을 증가시키며 모든 이동 칸 체크
    - 현재 기준 좌&상 칸에서 해당 칸으로 올 수 있는지 체크
        - 현재 칸이 잠긴 지역이 아니고
        - 올 수 있다면 해당칸 += 좌/상 혹은 둘 다 +
        - 없다면 패스
* 특이사항 -> 예시 그림이 m과 n이 반대로 나와있음. 이거땜에 시간 잡아먹음 >> 첨에 런타임 에러 발생함.. IDE 안써서 왜뜨는지 몰랐네
*/
public class Program_42898 {
    static int[][] map;

    public int solution(int m, int n, int[][] puddles) {
        map = new int[m + 1][n + 1];
        for (int[] puddle : puddles) {
            int x = puddle[0];
            int y = puddle[1];
            map[x][y] = -1;
        }

        map[1][1] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] != -1) {
                    if (j >= 1 && map[i][j - 1] != -1) {
                        map[i][j] += map[i][j - 1];
                    }

                    if (i >= 1 && map[i - 1][j] != -1) {
                        map[i][j] += map[i - 1][j];
                    }

                    if (map[i][j] > 1000000007) {
                        map[i][j] %= 1000000007;
                    }
                }
            }
        }

        return map[m][n];
    }

    public static void main(String[] args) {
        Program_42898 pg = new Program_42898();
        System.out.println(pg.solution(4, 3, new int[][]{{2, 2}}));
    }
}