package boj.twice;

import java.io.*;
import java.util.*;

/*
배열 돌리기 4 - 골4
소요시간 - 2시간 초과

[문제설명]
- N * M 크기의 2차원 배열과 K개의 회전 연산(r, c, s)이 주어짐.
- 회전 연산은 (r-s, c-s)부터 (r+s, c+s)까지의 정사각형 구간을 시계방향으로 한 칸씩 회전시키는 작업.
- K개의 회전 연산을 수행하는 모든 순서(경우의 수)를 탐색하여, 최종 '배열 A의 값(각 행의 합 중 최솟값)'이 가장 작게 나오는 결과를 출력.

[풀이설명]
- 주어진 구조에 맞게 흐름을 분리하여 구현
    1) 회전 연산(r, c, s)의 정보를 담을 커스텀 클래스(RC)를 만들고, 이를 배열로 구성하여 입력받음.
    2) DFS와 백트래킹을 활용하여 K! 개의 회전 순서(경우의 수)를 모두 탐색하는 메서드 구현.
    3) 선택된 회전 연산 정보(RC)를 바탕으로 실제 2차원 배열을 회전시키는 `rotateA` 메서드 구현.

p.s
- 전체적으로 설계가 흔들려서 우왕좌왕했던 것 같다.
    1) 회전 구간인 (r-s, c-s) ~ (r+s, c+s)가 무조건 '홀수 * 홀수' 형태의 정사각형 구조라는 점을 너무 늦게 파악했다.
    -> 불필요한 예외 케이스를 고려하느라 30분 정도 시간을 허비함.
    2) "기능 A와 기능 B를 각각 독립적으로 만들고 조립한다"는 원칙을 지키지 못했다.
    -> 전체 흐름을 완벽히 잡지 못한 채, A와 B 구현을 왔다 갔다 했다.
    3) `rotateA` 메서드 구현 중 다음 두 가지가 가장 헷갈렸고 시간을 많이 썼다.
        3-1) 매개변수 a를 그대로 사용했고 이에 원본 배열인 A가 훼손됨.
        -> 자바의 배열은 참조(메모리 주소)로 전달되기 때문에, 회전 테스트 시 원본 배열이 훼손되지 않도록 '깊은 복사(Deep Copy)'를 해야 한다는 점.
        3-2) r,c와 다음 위치값인 nr,nc의 위치를 2중반복문 내에 어디에 위치하며 갱신할지 시점을 헷갈려함.
        -> 회전 시 인덱스가 범위를 벗어나지 않게 체크하는 로직(방향 전환)과 다음 위치(r, c)를 갱신하는 시점을 while문 내부 어디에 둘지 헷갈림.

- 결과적으로 목표 시간(2시간)은 초과했지만, 복잡한 문제를 작은 기능별로 나누고 조립해 보는 아주 좋은 연습이 되었다.
- 앞으로도 꾸준하게, 설계부터 탄탄히 잡고 구현하는 연습을 하자!
 */
public class BOJ_17406 {
    static int N, M, K, min;
    static int[][] A;
    static RC[] rc;
    static boolean[] usedRC;
    static int[] dr = {1, 0, -1, 0}, dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N * M 과 회전연산 횟수 K
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N + 1][M + 1];
        // N * M 배열입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // K번의 회전연산
        rc = new RC[K + 1];
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            rc[i] = new RC(r, c, s);
        }

        min = Integer.MAX_VALUE;

        // K! 반복 수행
        for (int i = 1; i <= K; i++) {
            usedRC = new boolean[K + 1];
            usedRC[i] = true;
            dfs(i, 1, A);
        }

        System.out.println(min);
    }

    // 배열A 회전연산 수행시키는 메서드
    private static int[][] rotateA(int[][] a, RC nowRC) {
        int[][] nA = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                nA[i][j] = a[i][j];
            }
        }

        int sr = nowRC.r - nowRC.s, sc = nowRC.c - nowRC.s, er = nowRC.r + nowRC.s, ec = nowRC.c + nowRC.s;
        while (sr < er) {
            int r = sr, c = sc;
            int prev = nA[r][c]; // a[r-s][c-s];

            int dir = 0;
            while (true) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];

                if (nr == sr && nc == sc) {
                    nA[r][c] = prev;
                    break;
                }

                // 범위 벗어나면 방향전환
                if (nr < sr || nc < sc || nr > er || nc > ec) {
                    if (dir < 3) {
                        dir++;
                    } else {
                        dir = 0;
                    }
                    continue;
                }

                nA[r][c] = nA[nr][nc];
                r = nr;
                c = nc;
            }

            sr++;
            sc++;
            er--;
            ec--;
        }

        return nA;
    }

    private static void dfs(int node, int depth, int[][] a) {
        // node값 회전연산 수행
        int[][] rotateA = rotateA(a, rc[node]);

        if (depth == K) {
            for (int i = 1; i <= N; i++) {
                int sum = 0;
                for (int j = 1; j <= M; j++) {
                    sum += rotateA[i][j];
                }
                min = Math.min(min, sum);
            }
            return;
        }

        for (int i = 1; i <= K; i++) {
            if (!usedRC[i]) {
                usedRC[i] = true;
                dfs(i, depth + 1, rotateA);
                usedRC[i] = false;
            }
        }

    }

    static class RC {
        int r, c, s;

        public RC(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
}