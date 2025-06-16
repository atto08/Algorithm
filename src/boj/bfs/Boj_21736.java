package boj.bfs;

import java.util.*;
import java.io.*;

/*
 헌내기는 친구가 필요해 - 실2
 소요시간 - 17분

 풀이설명:
- I: 도연위치, P: 사람, O: 빈공간, X: 벽
- 도연이 위치파악할때는 어떤 접근이 가장 빠를까?
	-> 맵 정보 입력받음과 동시에 한칸 씩 I:도연인지 확인하기.

p.s
- 큐 사용해서 너비우선탐색하는 방식을 구현할 줄 아는가 에 대한 문제.
- 내일부턴 응용문제를 통해 머리를 굴려보자.
 */
public class Boj_21736 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        boolean[][] visited = new boolean[N][M];

        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'I') {
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int result = 0;
        int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if (!visited[nx][ny] && map[nx][ny] != 'X') {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                    if (map[nx][ny] == 'P') result++;
                }
            }
        }

        if (result == 0) {
            System.out.println("TT");
        } else {
            System.out.println(result);
        }
    }
}
