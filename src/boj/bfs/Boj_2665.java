package boj.bfs;

import java.util.*;

/*
미로 만들기 - 골4
소요시간: 1시간 초과

풀이: 블로그 참고
- 흰방&검은방 모두 지나야 한다고 생각
내 접근 방식 -> 모두 방문하고 벽을 부순 경우를 카운트 하는 것은 무한루프가 걸림
- 구현 방도가 떠오르지 않아 블로그 참고함 -> 다익스트라 알고리즘 풀이


재채점 - 메모리 초과 발생
이전 코드 구조:
Queue<int[]> + dist[x][y]를 기준으로 BFS 탐색
→ 방문 여부는 boolean이 아닌 dist 값으로 판단함
→ "이미 더 좋은 경로로 방문한 곳"도 조건만 맞으면 큐에 다시 들어감
→ 중복 삽입 많음 → 큐 길이 증가 → 메모리 초과 발생 가능


해결방법 - 우선순위 큐 + 다익스트라 구조 활용
핵심 전략:
- PriorityQueue<Maze>로 cost(부순 벽 개수)가 작은 것부터 먼저 탐색
- if (현재 cost > dist[x][y]) continue; 조건으로 중복 삽입 방지
- 즉, 이미 더 좋은 경로로 도달한 위치는 다시 큐에 안 들어감

장점:
- 중복 삽입 방지로 큐 크기 작아짐
- 우선순위에 따라 먼저 최소 비용 경로 탐색 가능 → 시간 단축
- 결과적으로 메모리 절약 + 성능 향상

p.s
- 응용하는거 너무 어렵다.
- 자료구조에서 메모리 시간 계산하는거 해도 해도 어렵다!
- 어쩌겠냐 ~ 해야지~ 될때까지~

- 아직 람다식 작성하는거 완벽히 응용못하고 있는데, 오늘 평소에 많이쓰던 우선순위 큐에서 간단 사용법 배웠다~!
*/
public class Boj_2665 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        char[][] map = new char[N][N];
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) map[i] = sc.next().toCharArray();
        for (int i = 0; i < N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);

        System.out.println(bfs(map, dist, N));
    }

    private static int bfs(char[][] map, int[][] dist, int N) {
        int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

        PriorityQueue<Maze> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.c));
        dist[0][0] = 0;
        pq.offer(new Maze(0, 0, 0));

        while (!pq.isEmpty()) {
            Maze now = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                // nc == 현재 위치를 거쳐서 다음 위치까지 가는 새로운 비용 (벽 부순 개수)
                int nc = now.c + (map[nx][ny] == '1' ? 0 : 1);
                // dist[nx][ny] == 지금까지 알고 있는 nx, ny까지 가는 최소 비용
                if (dist[nx][ny] > nc) {    // 다음 칸이 현재보다 더 비효율적이면
                    dist[nx][ny] = nc;      // 업데이트
                    pq.offer(new Maze(nx, ny, nc));
                }
            }
        }

        return dist[N - 1][N - 1];
    }

    static class Maze {
        int x, y, c;

        public Maze(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }
}
