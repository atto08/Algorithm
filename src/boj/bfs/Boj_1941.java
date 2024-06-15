package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
소문난 칠공주 - 골3
소요 시간: 20분
1) 틀렸습니다 (11%)

소요 시간: 1시간
2) 시간 초과(40분 넘김)
원인:
1. 같은 경우를 세고 있음
2. 중간에 빠져나가는 경우는 체크하지 못하는 중
>> dfs가 아니라 bfs 접근이 맞는거 같음.

예제의
.....
SYSYS
.Y...
.S...
.....
이 경우를 전에 풀때도, 다시 풀때도 찾지 못함. >> 이 부분에서 bfs가 필요한 것만 캐치

3) 통과
원인: 연결시켜놓은 상태에서 되는 경우를 구했음
해결: 만들 수 있는 경우의 수를 먼저 구함(조합) & 해당 경우의 수에 맞는 자리들이 연결되어있는지 확인해야하는 문제임
>> gpt한테 위 예제를 찾지 못하는 문제를 해결할 방법을 물어봄
 */
public class Boj_1941 {
    static int result;
    static char[][] classRoom = new char[5][5];
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            classRoom[i] = br.readLine().toCharArray();
        }

        combination(new boolean[25], 0, 0, 7);

        System.out.println(result);
    }

    private static void combination(boolean[] selected, int start, int depth, int r) {
        if (depth == r) {
            int[][] positions = new int[7][2];
            int index = 0;
            for (int i = 0; i < 25; i++) { // 1차원 배열을 2차원 배열로 만드는 작업
                if (selected[i]) {
                    positions[index][0] = i / 5;
                    positions[index][1] = i % 5;
                    index++;
                }
            }
            if (isConnected(positions)) {
                int sCount = 0;
                for (int[] pos : positions) {
                    if (classRoom[pos[0]][pos[1]] == 'S') {
                        sCount++;
                    }
                }
                if (sCount >= 4) {
                    result++;
                }
            }
            return;
        }

        for (int i = start; i < 25; i++) { // 1~25까지 중복되지 않는 조합 구하기
            selected[i] = true;
            combination(selected, i + 1, depth + 1, r); // start를 i+1로 함으로써 중복되는 경우 제거 (start = i 이면 중복 허용)
            selected[i] = false;
        }
    }

    private static boolean isConnected(int[][] positions) {
        boolean[][] visited = new boolean[5][5];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(positions[0]);
        visited[positions[0][0]][positions[0][1]] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;

                for (int[] pos : positions) { // 7개를 뽑은 조합에 연결되어있는지 확인
                    if (pos[0] == nx && pos[1] == ny && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                        count++;
                    }
                }
            }
        }

        return count == 7; // 7개 연결돼있으면 true 아니면 false
    }
}