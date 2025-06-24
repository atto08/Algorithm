package boj.bfs;

import java.util.*;
import java.io.*;

/*
빙산 - 골4
소요시간 - 2시간 초과

문제설명:
- 빙산이 두덩어리 이상으로 분리되는 최소의 시간을 구하시오
- 빙산은 0이 아닌 칸(0이 아닌 수는 빙산의 높이) & 테두리는 무조건 바다(0)
- 다 녹을때까지 분리X -> 0 출력

풀이설명:
1) 빙산찾기 - map[x][y] > 0 인 곳.
2) 2덩어리 이상인지 - cnt >= 2
3) 동서남북 갯수만큼 높이 줄어듦. - ex) 0 ~ -4

1차시도 - 3% 실패(틀림)
- 예시보니까 한번에 녹여야할 듯.
-> 연결된 빙산간에 바다와 인접한 갯수의 따라서 높이의 줄어듦이 달라지기 때문에

2차시도 - 3% 실패(틀림)
** 빙산 좌표가 담겨있는 배열리스트에서 0이된 좌표를 제거할때 밀려서(앞에 인덱스로 당겨짐) 문제 발생했음. **
-> 스택에 좌표 인덱스 넣어놓고 인덱스 내림차순 제거 -> 당겨짐 방지

3차시도 - 성공
p.s
- 풀었다. 오늘도 스스로. 근데 시간이 너무 오래걸렸다.
- 자료구조 특성을 제대로 인지하지 못하고 있던 문제다.
- 이렇게 오래걸릴 문제가 아니였는데.. 어쩌겠나! 아쉽지만, 다음번에는 바보같은 실수 안하겠지.
- 못했다. 잘했다. 둘다 했다.!
*/
public class Boj_2573 {
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        List<Ice> points = new ArrayList<>();   // 빙산 좌표 리스트
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) {
                    points.add(new Ice(i, j));
                }
            }
        }

        boolean[][] visited;
        Stack<Integer> stack;
        int cnt, day = 0;
        while (!points.isEmpty()) { // 빙산이 2덩어리 이상되면 종료

            cnt = 0;
            stack = new Stack<>();
            visited = new boolean[N][M];
            for (Ice ice : points) {    // 남아있는 빙산들 중에
                int x = ice.x, y = ice.y;
                if (visited[x][y]) continue;    // 방문했다면 == 연결됨(한 덩어리 취급)
                // 방문 X
                cnt++;                          // 덩어리 증가
                bfs(map, visited, x, y, N, M);
            }

            if (cnt >= 2) break;    // 2덩어리 이상이면 종료

            // 빙산들 중에 다 녹은 빙산의 좌표 찾기 - ArrayList에서 인덱스 제거하면 인덱스가 채워짐
            for (int i = 0; i < points.size(); i++) {
                int x = points.get(i).x, y = points.get(i).y;
                if (map[x][y] == 0) stack.push(i);      // 그래서 스택에 인덱스 오름차순으로 넣기
            }

            while (!stack.isEmpty()) {  // 스택에서 내림차순으로 인덱스 제거 ==> 밀림(채워짐)방지
                int idx = stack.pop();
                points.remove(idx);
            }
            day++;
        }

        if (points.isEmpty()) day = 0; // 빙산이 다 녹았다면
        System.out.println(day);
    }

    // 빙산간 연결여부 확인 & 녹는 양 확인
    private static void bfs(int[][] map, boolean[][] visited, int x, int y, int N, int M) {
        Queue<Ice> q = new LinkedList<>();
        q.offer(new Ice(x, y));
        visited[x][y] = true;

        List<Ice> dw = new ArrayList<>();   // 줄어드는 빙산 정보
        while (!q.isEmpty()) {
            Ice now = q.poll();

            int water = 0;
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if (map[nx][ny] == 0) water++;

                if (!visited[nx][ny] && map[nx][ny] > 0) {
                    visited[nx][ny] = true;
                    q.offer(new Ice(nx, ny));
                }
            }

            dw.add(new Ice(now.x, now.y, water));
        }

        for (Ice now : dw) {    // 빙산 줄어듦
            if (map[now.x][now.y] - now.w <= 0) {
                map[now.x][now.y] = 0;
            } else {
                map[now.x][now.y] -= now.w;
            }
        }
    }

    static class Ice {
        int x, y, w;

        public Ice(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Ice(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }
}
