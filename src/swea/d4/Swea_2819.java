package swea.d4;

import java.io.*;
import java.util.*;
/*
격자판의 숫자 이어 붙이기 - D4
소요 시간: 32분
1) Fail
1. hashSet과 Queue를 테스트 케이스 시작 시 초기화하지 않음
2. 테스트 케이스가 하나라 못봤는데 띄어쓰기를 하지않음
소요 시간: +8분
 */
public class Swea_2819 {
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static HashSet<String> hashSet;
    static Queue<Pair> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            map = new int[4][4];
            StringTokenizer st;
            hashSet = new LinkedHashSet<>();
            q = new LinkedList<>();
            // 4 * 4 격자 입력
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    q.offer(new Pair(i, j, 0, new StringBuilder(map[i][j])));
                }
            }

            bfs();

            bw.write("#" + test_case + " " + hashSet.size());
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static void bfs() {

        while (!q.isEmpty()) {
            Pair now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                StringBuilder sb2 = new StringBuilder();
                sb2.append(now.sb);

                if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) {
                    continue;
                }

                if (now.dist < 7) {
                    q.offer(new Pair(nx, ny, now.dist + 1, sb2.append(map[nx][ny])));
                } else if (now.dist == 7) {
                    hashSet.add(now.sb.toString());
                }
            }
        }
    }
}

class Pair {
    int x, y, dist;
    StringBuilder sb;

    public Pair(int x, int y, int dist, StringBuilder sb) {
        this.x = x;
        this.y = y;
        this.dist = dist;
        this.sb = sb;
    }
}
