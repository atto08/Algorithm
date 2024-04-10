package boj.bfs;

import java.util.*;

/*
미로 만들기 - 골4
소요시간: 1시간 초과

풀이: 블로그 참고 - https://velog.io/@717lumos/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EB%8B%A4%EC%9D%B5%EC%8A%A4%ED%8A%B8%EB%9D%BCDijkstra-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98
흰방&검은방 모두 지나야 한다고 생각
>> 모두 방문하고 벽을 부순 경우를 카운트 하는 것은 무한루프가 걸림
구현 방도가 떠오르지 않아 블로그 참고함
최단 경로의 정점으 선택하는 >> 다익스트라 알고리즘 학습
 */
public class Boj_2665 {
    static int N;
    static int[][] map, dist;
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N + 1][N + 1];
        dist = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            String[] cArr = sc.next().split("");
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(cArr[j - 1]);
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        bfs();
        System.out.println(dist[N][N]);
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{1,1});
        dist[1][1] = 0;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            int x = now[0], y= now[1];
            for (int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx<1 || ny < 1 || nx>N|| ny >N){
                    continue;
                }

                if (dist[nx][ny] > dist[x][y]){
                    if (map[nx][ny] == 1){
                        dist[nx][ny] = dist[x][y];
                    } else{
                        dist[nx][ny] = dist[x][y] + 1;
                    }
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }
}
