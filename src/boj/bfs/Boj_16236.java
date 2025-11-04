package boj.bfs;

import java.io.*;
import java.util.*;

/*
 아기상어 - 골3
 소요시간 - 75분
 [문제설명]
  엄마상어에게 도움요청없이 물고기를 잡아먹을 수 있는 시간 구하기

 아기상어 사이즈
 - 2사이즈로 시작
 - 사이즈 크기 마리 만큼 물고기 잡아먹으면 사이즈업

 자신의 크기와 같은 수 의  물고기 **
 -> 크기가 2일때, 2마리 물고기섭취 -> 크기 증가

 [풀이설명]
 아기상어 위치에서 BFS 탐색
 - 자신보다 큰 사이즈 물고기가 있거나, 방문했거나, 맵을 벗어난 경우는 무시
 - 확인하는 것은 칸이 빈칸(0)이거나 현재 상어보다 같은 크기의 칸
 - 그 중 자기보다 작은 물고기를 찾으면 우선순위 큐에 넣어두기
 
 아기상어 클래스
 상어 위치 - x,y
 상어 크기 - size
 상어가 먹은 물고기 수 - ateFish
 총 소요시간 - eatTime -> ** result **

 물고기 클래스
 물고기 위치 - x,y
 물고기 먹으러오는데 걸린 시간(거리) - dist
 Comparable를 구현해 우선순위 큐 활용 -> compareTo 메서드 참조

 p.s
 - 이전 코드 보니까 풀다가 열받아서 블로그 참고한거 같다. 설명도 안적혀있다.
 - 그래서 1시간안에 푸는걸 목표로 했는데, 이건 실패.
 - 하지만 15분 추가해서 풀어냈다.
 - 뭔가 다른 문제 풀이에서 참고한 클래스에서 동작시키는 메서드를 활용한 것 같아서 맘에든다.
 - 그래도 좋은 코드인지는 확신은 못하겠다. 확신할 수 있는 코드 작성할때까지 또 나아가자고.
 */
public class Boj_16236 {

    static int N;
    static int[][] map;
    static BabyShark babyShark;
    static boolean helpMomShark;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    static class BabyShark {

        int x, y, size, ateFish, eatTime;

        public BabyShark(int x, int y) {
            this.x = x;
            this.y = y;
            this.size = 2;
            this.ateFish = 0;
            this.eatTime = 0;
        }

        public void sizeUp() {
            this.size++;
            this.ateFish = 0;
        }

        public void moveAndEatFish(Fish fish) {
            this.x = fish.x;
            this.y = fish.y;
            this.ateFish++;
            this.eatTime += fish.dist;

            if (this.ateFish == this.size) sizeUp();
        }
    }

    static class Fish implements Comparable<Fish> {

        int x, y, dist;

        public Fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        public int compareTo(Fish other) {
            if (this.dist != other.dist) return Integer.compare(this.dist, other.dist);
            if (this.x != other.x) return Integer.compare(this.x, other.x);
            return Integer.compare(this.y, other.y);
        }
    }

    private static boolean bfs() {
        // 아기상어 위치에서 새로운 탐색 시작시에 방문여부 체크
        boolean[][] visited = new boolean[N][N];
        Queue<Fish> q = new LinkedList<>();
        q.offer(new Fish(babyShark.x, babyShark.y, 0));
        visited[babyShark.x][babyShark.y] = true;

        PriorityQueue<Fish> pq = new PriorityQueue<>();
        while (!q.isEmpty()) {
            Fish now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if (!visited[nx][ny] && map[nx][ny] <= babyShark.size) {
                    visited[nx][ny] = true;
                    if (map[nx][ny] < babyShark.size && map[nx][ny] > 0) {
                        pq.offer(new Fish(nx, ny, now.dist + 1));
                    } else {
                        q.offer(new Fish(nx, ny, now.dist + 1));
                    }
                }
            }
        }

        if (!pq.isEmpty()) {
            Fish fish = pq.poll();
            map[fish.x][fish.y] = 0;
            babyShark.moveAndEatFish(fish);
//			System.out.println("상어 움직임");
//			System.out.println("위치:" + babyShark.x + "," + babyShark.y + "/ 총 이동시간:" + babyShark.eatTime);
//			System.out.println("크기:" + babyShark.size + " / 먹은 물고기 수:" + babyShark.ateFish);
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    babyShark = new BabyShark(i, j);
                    map[i][j] = 0;
                }
            }
        }

        helpMomShark = false;
        while (!helpMomShark) {
            helpMomShark = bfs();
        }

        System.out.println(babyShark.eatTime);
    }
}