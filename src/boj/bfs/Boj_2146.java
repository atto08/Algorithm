package boj.bfs;

import java.util.*;
import java.io.*;

/*
다리 만들기 - 골3
소요시간 - 1시간 초과

풀이 설명:
- N * N(N <= 100 자연수) 크기의 2차원 평면이 나라(2차원 배열)
- 여러개(2개 이상의)의 섬이 주어짐.
- 가장 짧은 다리 하나를 놓아 2대륙을 연결하는 방법을 찾기.

p.s
- 시간, 메모리 생각하느라 좀 머리가 아팠다.
- 근데 결과적으로는 구현하고 실패한담에 하는게 맞지.
- 좋은 고민습관이 생긴거 같은데 좀 더 좋게 활용해보자.
- 구현순서 정하기, 구현 방법 생각(시간, 메모리)
*/
public class Boj_2146 {
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visited = new boolean[N][N]; // 섬 구분하기 위한
        int islandNum = 1;
        ArrayList<ArrayList<int[]>> xyList = new ArrayList<>();
        xyList.add(new ArrayList<>());
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    labeling(i, j, N, map, visited, islandNum++, xyList);
                }
            }
        }

        min = Integer.MAX_VALUE;
        for (int i = 1; i < xyList.size(); i++) {
            visitClear(visited, N);
            bfs(xyList.get(i), map, visited, N, i);
        }

        System.out.println(min);
    }

    private static void labeling(int x, int y, int N, int[][] map, boolean[][] visited, int islandNum, ArrayList<ArrayList<int[]>> list) {
        Queue<int[]> q = new LinkedList<>();

        list.add(new ArrayList<>()); // islandNum번째 섬 외곽좌표 저장용
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        map[x][y] = islandNum;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int cx = now[0], cy = now[1];

            boolean isEdge = false;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;

                if (map[nx][ny] == 0) {
                    isEdge = true; // 바다와 인접한 경우 → 외곽
                }
                if (!visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    map[nx][ny] = islandNum;
                    q.offer(new int[]{nx, ny});
                }
            }

            if (isEdge) {
                list.get(islandNum).add(new int[]{cx, cy});
            }
        }
    }

    private static void bfs(ArrayList<int[]> arr, int[][] map, boolean[][] visited, int N, int islandNum) {

        Queue<Bridge> q = new LinkedList<>();

        // 섬 외곽 좌표 Q에 담기
        for (int[] xy : arr) {
            int x = xy[0], y = xy[1];
            q.offer(new Bridge(x, y, 0));
        }

        // 외곽 좌표에서 다른 섬 찾을 때 까지
        while (!q.isEmpty()) {
            Bridge now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    // 바다면 다른 섬 찾을때 까지 거리증가
                    if (map[nx][ny] == 0) {
                        q.offer(new Bridge(nx, ny, now.d + 1));
                    }
                    // 다른 섬이면
                    else if (map[nx][ny] > 0 && map[nx][ny] != islandNum) {
                        if (now.d < min){
                            min = now.d;
                            return;
                        }
                    }
                }
            }
        }
    }

    private static void visitClear(boolean[][] visited, int N) {
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
    }

    static class Bridge {
        int x, y, d;

        public Bridge(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}