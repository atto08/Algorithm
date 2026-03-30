package codetree;

import java.io.*;
import java.util.*;

/*
Ai 로봇 청소기 - 구현
소요시간 - 4시간 초과

[1차 시도] - 3시간 소요
- 결과: TC 3번 실패
- 원인: 청소(2) 진행 시, 벽(-1)이 있는 칸까지 0으로 만들어버리며 청소함.
- 해결: cleanRoom 메서드에 map[r][c] == -1 (벽)인 경우 무시하도록 방어 로직 추가.

[2차 시도] - +20분 소요
- 결과: TC 8번 실패
- 원인: 1~4번 과정의 엣지 케이스 점검 필요로 생각. -> 부분 놓친 문제 설명 상세히 점검.

[3차 시도] - 시간 초과 (총 4시간)
- 결과: TC 8번 실패 원인 파악 및 해결
- 원인: 청소기 방향 선정 시, 4방향의 '전체 먼지 합'을 기준으로 비교함.
      - 지문 명시 조건: "청소할 수 있는 먼지량이 가장 큰 방향 선택" (최대 20)
      - 즉, 먼지가 100이 쌓여 있어도 실제 청소량은 20이므로, 이를 초과하는 값을 더하면 최적의 방향을 잘못 판단하게 됨.
- 해결: getCleanableDust 메서드를 분리하여, 반환값과 기준 칸의 먼지량을 Math.min(먼지량, 20)으로 제한.
      - 실제로 빨아들일 수 있는 먼지량만 더하도록 로직 수정.

[회고]
- TC 8번 통과 실패후 놓친 부분이 있나 1~4번 동작 메서드 별 조건을 다시 확인하는데 남겨놓은 1시간을 전부 사용했다.
- 정리는 잘했는데, 문제의 멘트 한부분에서 디테일함을 놓쳤다고 생각한다.
- 오늘 같은 경우 최대 제거 가능값이 20인것도 인지했고, 4방향 중 가장 많은 먼지량을 제거할 수 있는 방향을 선택하는 부분도 인지했다.
- 하지만, 결정적으로 2가지를 합쳐서 가장 많은 제거가 가능한 경우를 벗어나게 구현했다는걸 인지하지 못했다.
- 앞으로는 주어진 조건이 어떠한 영향을 끼칠지 좀더 상세하게 정리해보자.
 */
public class Main_25년하반기오후1번 {
    static int N, K, L; // 격자 크기 = N, 로봇 청소기 개수 = K, 테스트 횟수 = L
    static int[][] map; // 격자 정보
    static Node[] robots; // 로봇 좌표
    static int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};

    static class Node {
        int r, c, n;

        public Node(int r, int c, int n) {
            this.r = r;
            this.c = c;
            this.n = n;
        }
    }

    // 1. 청소기 이동 - 청소기 번호 순서
    private static void moveRobot(Node robot) {

        boolean[][] visited = new boolean[N + 1][N + 1];
        Queue<Node> q = new LinkedList<>();
        // 좌표(r, c)와 거리(n)로 사용
        q.offer(new Node(robot.r, robot.c, 0));
        // 로봇 위치는 방문처리 함 -> 로봇이 위치한 곳은 지나갈 수 X
        for (Node rb : robots) {
            visited[rb.r][rb.c] = true;
        }

        // 가장 가까운 먼지있는 위치 찾기
        int minDist = Integer.MAX_VALUE;
        int mr = robot.r, mc = robot.c;
        while (!q.isEmpty()) {
            Node now = q.poll();

            // 최소거리가 현재 확인하는 칸보다 가까우면 종료
            if (minDist < now.n) break;

            // 현재 오염지역
            if (map[now.r][now.c] > 0) {
                // 가장 가까운
                if (now.n < minDist) {
                    mr = now.r;
                    mc = now.c;
                    minDist = now.n;
                }
                // 가장 가까운 다른 곳
                else if (now.n == minDist) {
                    // 행의 값이 더 작은 지역 우선
                    if (now.r < mr) {
                        mr = now.r;
                        mc = now.c;
                    } else if (now.r == mr) {
                        // 열의 값이 더 작은 지역 우선
                        if (now.c < mc) {
                            mc = now.c;
                        }
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];

                // 격자 밖 무시
                if (nr < 1 || nc < 1 || nr > N || nc > N) continue;

                // 벽 무시
                if (map[nr][nc] == -1) continue;

                // 방문한 적 있거나 로봇이 위치햇으면 무시
                if (visited[nr][nc]) continue;

                visited[nr][nc] = true; // 방문처리
                q.offer(new Node(nr, nc, now.n + 1));
            }
        }

        // 로봇청소기 이동
        robots[robot.n] = new Node(mr, mc, robot.n);
    }

    // 2. 청소 - 청소기 번호 순서
    private static void cleanDust(Node robot) {
        // 4방향 중 가장 먼지 많은 방향 선택
        int dir = getMaxDustDirectionFrom4D(robot.r, robot.c);

        // 4칸 청소 진행
        // 현재칸
        cleanRoom(robot.r, robot.c);
        // 현재칸 보는 방향 기준 위쪽
        cleanRoom(robot.r + dr[dir], robot.c + dc[dir]);

        // 현재칸 보는 방향 기준 좌우
        // 우 or 좌
        if (dir % 2 == 0) {
            cleanRoom(robot.r + dr[1], robot.c + dc[1]);
            cleanRoom(robot.r + dr[3], robot.c + dc[3]);
        }
        // 하 or 상
        else {
            cleanRoom(robot.r + dr[0], robot.c + dc[0]);
            cleanRoom(robot.r + dr[2], robot.c + dc[2]);
        }
    }

    // 칸 청소
    private static void cleanRoom(int r, int c) {
        // 격자를 벗어나면 무시합니다.
        if (r < 1 || c < 1 || r > N || c > N) return;

        // 벽도 무시합니다.
        if (map[r][c] == -1) return;

        if (map[r][c] >= 20) {
            map[r][c] -= 20;
        } else {
            map[r][c] = 0;
        }
    }

    // 특정 칸(r, c)에서 로봇이 실제로 청소할 수 있는 먼지량(최대 20)을 반환
    private static int getCleanableDust(int r, int c) {
        // (격자 밖이거나 벽인 경우 0 반환)
        // 격자 밖
        if (r < 1 || c < 1 || r > N || c > N) {
            return 0;
        }

        if (map[r][c] == -1) {
            return 0;
        }

        return Math.min(map[r][c], 20);
    }

    // 4방향 중 가장 먼지량이 큰 방향 리턴
    private static int getMaxDustDirectionFrom4D(int r, int c) {

        int dir = -1; // 먼지합 최댓값 갖는 방향
        int max = -1; // 먼지합 최댓값

        // 합이 같은 방향이 여러개 -> 우 하 좌 상 우선순위로 채택
        for (int i = 0; i < 4; i++) {
            // 기준 칸 먼지 더하기
            int sum = Math.min(map[r][c], 20);

            int nr = r + dr[i];
            int nc = c + dc[i];
            // 방향기준 위 쪽 먼지 더하기
            sum += getCleanableDust(nr, nc);

            // 우 or 좌
            if (i % 2 == 0) {
                // 상하 만 확인
                sum += getCleanableDust(r + dr[1], c + dc[1]);
                sum += getCleanableDust(r + dr[3], c + dc[3]);
            } else {
                // 좌우 만 확인
                sum += getCleanableDust(r + dr[0], c + dc[0]);
                sum += getCleanableDust(r + dr[2], c + dc[2]);
            }

            // 우-하-좌-상 우선순위 유지
            if (sum > max) {
                dir = i;
                max = sum;
            }
        }

        return dir;
    }

    // 3. 먼지 축적 - 동시
    private static void addDust() {

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] > 0) {
                    map[i][j] += 5;
                }
            }
        }
    }

    // 4. 먼지 확산 - 동시
    private static void spreadDust() {

        Queue<int[]> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 깨끗한 격자(빈칸)
                if (map[i][j] == 0) {
                    int sum = 0;    // 4방향 먼지 합 / 10 값을 넣어놓기
                    for (int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];

                        // 격자 밖 무시
                        if (nr < 1 || nc < 1 || nr > N || nc > N) continue;

                        // 벽 무시
                        if (map[nr][nc] == -1) continue;

                        sum += map[nr][nc];
                    }

                    sum /= 10;
                    if (sum > 0) {
                        q.offer(new int[]{i, j, sum});
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            int[] now = q.poll();

            int r = now[0], c = now[1], dust = now[2];
            map[r][c] += dust;
        }
    }

    private static int getResult() {
        int result = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] > 0) {
                    result += map[i][j];
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1) 입력 - 격자 크기 & 로봇 청소기 수 & 테스트 횟수
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        // 2) 입력 - 격자 정보(먼지 & 물건)
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 3) 입력 - 로봇 청소기 위치(좌표)
        robots = new Node[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            robots[i] = new Node(r, c, i);
        }

        // L회 반복
        while (L > 0) {

            // 1. 청소기 이동
            for (Node robot : robots) {
                moveRobot(robot);
            }

            // 2. 청소
            for (Node robot : robots) {
                cleanDust(robot);
            }

            // 3. 먼지 축적
            addDust();

            // 4. 먼지 확산
            spreadDust();

            // 5. 출력
            bw.write(getResult() + "\n");
            L--;
        }
        bw.flush();
        br.close();
        bw.close();
    }
}