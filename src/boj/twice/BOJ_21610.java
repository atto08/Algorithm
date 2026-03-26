package boj.twice;

import java.io.*;
import java.util.*;

/*
마법사 상어와 비바라기 - 골5
소요시간 - 117분

[문제설명]
- M번의 구름 이동이 끝난 후 남아있는 물의 총합 구하기

- N * N 격자내 물 바구니 존재

구름 이동
1) 모든 구름 이동
2) 비 내림 -
3) 구름 소멸
4) 물 복사 버그 - 비 내림(2번)이 일어난 칸들에
    - 각 칸 기준 대각(2,4,6,8)방향에 물이 있는 바구니 수 만큼 물 추가(최소 0 ~ 최대 4)
    * 경계 바깥은 취급 X
5) 구름 생성
    - 바구니 물 >= 2 를 충족하는 칸에는 구름생성 (구름 생성시 바구니에 있는 물 -2)
    * 3번 에서 구름이 소멸된 칸은 제외.

[풀이설명]
- 구름의 위치정보 배열리스트 - List<Cloud>
- 격자내 바구니(칸)별 물 값 이차원배열 - A[r][c]
- 방향 1 ~ 8 - dr, dc

동작별 메서드 구현
1. 구름 이동
    - 격자 내 바깥 범위는 nr, nc 가 음수 양수 여부에 따라 돌아오는 칸을 계산하도록 규칙 구현
2. 비 내림, (구름 소멸), 물 복사 버그
    - (2) 비 내림 == A[r][c] + 1
    - (4) 물 복사 버그 == A[r][c] + (격자 내 대각에 물이있는 칸 갯수)
3. 구름 생성
    - 격자 내 이전에 구름이 소멸한칸 or A[r][c] < 2 인 칸을 제외하고 구름을 생성

M번의 이동 명령 동안 1~3 메서드를 수행함.
1, 3 메서드는 수행하며 바뀐 구름들의 좌표를 바꾸기 위해 배열리스트 타입으로 정함.

[회고]
- 어제 다른 문제풀때 집중을 못했는데, 오늘은 그래도 비교적 다시 정신을 차렸다.
- 구현 과정도 나쁘지 않았다고 생각한다.
- 문제를 옮겨적으려 하지말자. 정확하게 읽고 이해한 내용을 정리하자.
 */
public class BOJ_21610 {
    static int N, M;
    static int[][] A;
    static List<Cloud> clouds;
    static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1}, dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    static class Cloud {
        int r, c;

        public Cloud(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    // 1. 구름 이동
    private static List<Cloud> moveCloud(int d, int s) {

        List<Cloud> movedClouds = new ArrayList<>();

        for (Cloud now : clouds) {
            int nr = now.r + dr[d] * s;
            int nc = now.c + dc[d] * s;

            // 경계 밖은 내부로 들어오도록 계산합니다.
            if (nr < 1 || nc < 1 || nr > N || nc > N) {
                if (nr < 1) {
                    nr = N + nr % N;
                } else {
                    nr = nr % N;
                }

                if (nc < 1) {
                    nc = N + nc % N;
                } else {
                    nc = nc % N;
                }

                nr = nr > 0 ? nr : N;
                nc = nc > 0 ? nc : N;
            }

            movedClouds.add(new Cloud(nr, nc));
        }

        return movedClouds;
    }

    // 2. 비 내림, 구름 소멸, 물복사 버그
    private static void rain() {

        // 비 내림
        for (Cloud now : clouds) A[now.r][now.c]++;

        int[] addWater = new int[clouds.size()]; // 대각선에 물이있는 바구니 수
        // 물복사 버그 계산 & 구름 소멸
        for (int i = 0; i < clouds.size(); i++) {
            Cloud now = clouds.get(i);

            for (int j = 1; j <= 4; j++) {
                int d = j * 2;
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                // 범위가 벗어나면 무시합니다.
                if (nr < 1 || nc < 1 || nr > N || nc > N) continue;

                // 물이 없으면 무시합니다.
                if (A[nr][nc] == 0) continue;

                addWater[i]++;
            }
        }

        // 물 복사 버그
        for (int i = 0; i < clouds.size(); i++) {
            Cloud now = clouds.get(i);
            A[now.r][now.c] += addWater[i];
        }
    }

    // 3. 구름 생성
    private static List<Cloud> makeCloud() {

        boolean[][] visited = new boolean[N + 1][N + 1]; // 이전 구름이 소멸한 칸

        for (Cloud cloud : clouds) visited[cloud.r][cloud.c] = true;

        List<Cloud> bornClouds = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 앞서 구름이 소멸한 칸은 무시합니다.
                if (visited[i][j]) continue;

                // 바구니 물이 2미만인 칸은 무시합니다.
                if (A[i][j] < 2) continue;

                // 새로운 구름 삽입
                bornClouds.add(new Cloud(i, j));
                A[i][j] -= 2; // 구름 생성시 물 -2
            }
        }

        return bornClouds;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 구름 위치 입력
        clouds = new ArrayList<>();
        for (int i = N; i >= N - 1; i--) {
            clouds.add(new Cloud(i, 1));
            clouds.add(new Cloud(i, 2));
        }

        // M번의 이동 명령 수행
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            // 1. 구름 이동
            clouds = moveCloud(d, s);
            // 2. 비 내림, 물복사 버그
            rain();
            // 5. 구름 생성
            clouds = makeCloud();
        }

        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sum += A[i][j];
            }
        }
        System.out.println(sum);
    }
}