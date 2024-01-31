package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_2206 {
    static int N, M;
    static boolean[][][] visited;
    static int[][] depth;
    static ArrayList<ArrayList<Integer>> map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new ArrayList<>();
        visited = new boolean[N][M][2];
        depth = new int[N][M];


        for (int i = 0; i < N; i++) {
            map.add(new ArrayList<>());
            Arrays.fill(depth[i], -1);

            char[] cArr = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (cArr[j] == '0') {
                    map.get(i).add(0);
                } else {
                    map.get(i).add(1);
                }
            }
        }

        bfs();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(depth[i][j] + " | ");
            }
            System.out.print("\n");
        }

        System.out.println(depth[N - 1][M - 1]);
        br.close();
    }

    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 0});
        visited[0][0][0] = visited[0][0][1] = true;
        depth[0][0] = 1;

        while (!q.isEmpty()) {
            int[] xy = q.poll();
            int x = xy[0];
            int y = xy[1];
            int wall = xy[2];

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;
                }

                if (map.get(nextX).get(nextY) == 1 && wall == 0 && !visited[nextX][nextY][wall + 1]) {
                    visited[nextX][nextY][wall + 1] = true;
                    depth[nextX][nextY] = depth[x][y] + 1;
                    q.offer(new int[] { nextX,nextY,wall+1});
                }

                else if (map.get(nextX).get(nextY) != 1 && visited[nextX][nextY][wall]){
                    visited[nextX][nextY][wall] = true;
                    depth[nextX][nextY] = depth[x][y] + 1;
                    q.offer(new int[] { nextX,nextY,wall});
                }

            }
        }
    }
}
