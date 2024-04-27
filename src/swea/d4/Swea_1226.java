package swea.d4;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
/*
미로1 - D4
소요 시간: 43분
<소요 시간이 더 걸린 이유>
1. 단순 미로 찾기(bfs) 문제로 인식하고 너무 편하게 접근함
>> 방문하지 않은 map[x][y] == 0 인 곳만 넣음
>> 도착지에 절대 도착할 수 없게됨

point 방심하지 말자.
 */
public class Swea_1226 {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int test_case = 1; test_case <= 10; test_case++) {
            int T = Integer.parseInt(br.readLine());

            map = new int[16][16];
            for (int i = 0; i < 16; i++) {
                String[] strArr = br.readLine().split("");
                for (int j = 0; j < 16; j++) {
                    map[i][j] = Integer.parseInt(strArr[j]);
                }
            }

            int result = 0;
            visited = new boolean[16][16];
            if (bfs()) {
                result = 1;
            }
            bw.write("#" + T + " " + result + "\n");

        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static boolean bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{1, 1});
        visited[1][1] = true;

        boolean canArrive = false;
        while (!q.isEmpty()) {
            int[] now = q.poll();

            int x = now[0], y = now[1];
            if (map[x][y] == 3) {
                canArrive = true;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!visited[nx][ny] && map[nx][ny] != 1) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        return canArrive;
    }
}
