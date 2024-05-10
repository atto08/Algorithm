package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
미로 탐색 - 실1
복습 - 재풀이
소요 시간: 20분
//풀이 2
public class Solution {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 1; j <= M; j++) {
				map[i][j] = c[j - 1] - '0';
			}
		}
		bfs();
	}

	private static void bfs() {
		Queue<XY> q = new LinkedList<>();
		q.offer(new XY(1, 1, 1));
		visited[1][1] = true;

		while (true) {
			XY now = q.poll();

			if (now.x == N && now.y == M) {
				System.out.println(now.m);
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 1 || ny < 1 || nx > N || ny > M) {
					continue;
				}

				if (!visited[nx][ny] && map[nx][ny] == 1) {
					visited[nx][ny] = true;
					q.offer(new XY(nx, ny, now.m + 1));
				}
			}
		}
	}

	static class XY {
		int x, y, m;

		private XY(int x, int y, int m) {
			this.x = x;
			this.y = y;
			this.m = m;
		}
	}
}

 */
// 풀이 1(이전풀이)
public class Boj_2178 {
    static int N, M;
    static int[][] maze, depth;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new int[N][M];
        depth = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                maze[i][j] = arr[j];
            }
        }
        bfs();

        System.out.println(depth[N-1][M-1]);
        br.close();
    }

    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        int x = 0;
        int y = 0;
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        depth[x][y] = 1;

        while (!q.isEmpty()) {
            int[] xy = q.poll();
            for (int i = 0; i < 4; i++) {
                x = xy[0] + dx[i];
                // '+' 가 '=' 으로 되어있었다. 오타 조심
                y = xy[1] + dy[i];

                if (x < 0 || y < 0 || x >= N || y >= M) {
                    continue;
                }

                if (!visited[x][y] && maze[x][y] == '1') {
                    q.offer(new int[]{x, y});
                    visited[x][y] = true;
                    depth[x][y] = depth[xy[0]][xy[1]] + 1;
                }
            }
        }
    }
}
