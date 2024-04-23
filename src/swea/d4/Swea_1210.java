package swea.d4;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
Ladder - D4
소요 시간: 2시간 15분
1) 2시간이 넘운 이유
1. 방문 체크를 제대로 하지않았음
2. y값의 인덱스가 0 또는 99를 초과했을 때의 조건문에서 y값을 변수로 매번 지정하지 않고 위에 += 연산을 사용해 값이 변경되는 것을 놓침
>> 매번 변동하는 y값을 변수로 지정함 >> 통과
 */
public class Swea_1210 {
    static int[][] ladder;
    static boolean[][] visited;
    static int[] lr = {-1, 1};
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        for (int test_case = 1; test_case <= 10; test_case++) {
            ladder = new int[100][100];
            visited = new boolean[100][100];

            int t = Integer.parseInt(br.readLine());

            int[] start = new int[2];
            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    ladder[i][j] = Integer.parseInt(st.nextToken());
                    if (ladder[i][j] == 2) {
                        start[0] = i;
                        start[1] = j;
                    }
                }
            }

            findStartX(start, t);
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static void findStartX(int[] start, int T) throws IOException {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start[0], start[1]});
        visited[start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            //좌우 갈 수 있나?
            int x = now[0];
            int y = now[1];

            if (x == 0) {
                bw.write("#" + T + " " + y + "\n");
                break;
            }
            boolean goLeftRight = false; // 좌우로 갈수 있는 트리거
            int dir = 2; // 방향

            for (int i = 0; i < 2; i++) { // 좌우로 갈수 있는지 판별
                int ny = y + lr[i];
                // 넘어가면 무시하고
                if (ny < 0 || ny >= 100) {
                    continue;
                }
                // 갈 수 있으면
                if (!visited[x][ny] && ladder[x][ny] == 1) {
                    y = ny; // 이동 가능한 곳으로 초기화
                    visited[x][y] = true;
                    dir = i; // 좌우 이동 가능한 방향
                    goLeftRight = true; // 이동가능한 걸로
                    break;
                }
            }

            if (goLeftRight) { // 좌우로 갈수 있을때
                // 틀린이유 1 >> 변수로 지정해 매번 바뀌었을 때의 수의 값이 제대로 수정되도록 하기
                int nextY = y + lr[dir];
                // 부족한 부분1 >> while 문안에 조건문에 사용하던 조건식을 while문의 조건식으로 바꿈
                while (nextY >= 0 && nextY < 100 && ladder[x][nextY] == 1 && !visited[x][nextY]) {
                    visited[x][nextY] = true;
                    y = nextY;
                    nextY = y + lr[dir];
                }
                q.offer(new int[]{x, y});
            }

            //없으면 위로
            else {
                q.offer(new int[]{x - 1, y});
                visited[x - 1][y] = true;
            }
        }
    }
}
