package boj.twice;

import java.io.*;
import java.util.*;

/*
마법사 상어와 파이어볼 - 골4
소요시간 - 2시간초과

[문제설명]
- N = 격자의 크기(N*N) / M = 파이어볼의 갯수 / K = 이동 명령 횟수
- (r,c) = 파이어볼 좌표 / m = 파이어볼 질량 / s = 파이어볼 속도 / d = 파이어볼 방향
- K번의 명령이 끝난 후, 남아있는 파이어볼의 질량의 합을 구하기

[풀이설명]
- ArrayList<FireBall> 타입의 이중배열에 여러개의 파이어볼의 질량(m), 속도(s), 방향(d) 값을 가질 수 있도록 자료구조 구현
- 파이어볼이 존재하는 구역만 담은 Queue<Spot> 사용

K번의 이동명령을 규칙에 맞춰 수행
1) 파이어볼 이동
2) 2개 이상의 파이어볼이 위치한 곳의 파이어볼 분할
상세 내용 주석확인

[회고]
- 문제를 처음 읽을때 제대로 읽고 문제 요구사항 및 제한사항을 똑바로 기록하자.
- "격자의 행과 열은 1번부터 N번까지 번호가 매겨져 있고, 1번 행은 N번과 연결되어 있고, 1번 열은 N번 열과 연결되어 있다." 위 멘트를 놓치고
    - (1,1) ~ (N,N) 범위를 벗어나면 파이어볼이 사라지도록 구현했다.
    - 추가로 넘어간 이후에 값을 범위내로 다시 들여오는 nr, nc 값을 갱신하는 규칙을 찾다가 헤맨시간이 가장 많이 소요됐다.
- * 중요 *
    -> 배열리스트는 앞쪽에 있는 값이 사라지면 그만큼 빈칸을 채운다는 특징! -> 헷갈려서 배열리스트에 값 2개 넣어보고 확인해봤다.
 */
public class BOJ_20056 {
    static int N, M, K;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}, dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static Queue<Spot> spots;
    static ArrayList<FireBall>[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        // 파이어볼 위치 담은 큐
        spots = new LinkedList<>();

        // M개의 파이어볼 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            map[r][c].add(new FireBall(m, s, d, 0));
            spots.offer(new Spot(r, c));
        }

        int count = 0;
        // K번 이동
        while (count < K) {

            // 1) 파이어볼 이동
            moveFireBall(count);

            // 2) 2개 이상 파이어볼 위치한 칸 계산
            divideFireBall(count + 1);

            count++;
        }

        int sumM = 0;
//		System.out.println("질량 합 구하기");
        // 남은 파이어볼 질량 계산
        while (!spots.isEmpty()) {
            Spot s = spots.poll();

//			System.out.println(s.r + "," + s.c);
//			System.out.println("갯수 : " + map[s.r][s.c].size());

            for (int i = 0; i < map[s.r][s.c].size(); i++) {
                sumM += map[s.r][s.c].get(i).m;
            }
        }

        System.out.println(sumM);
    }

    // 2) 2개이상의 파이어볼 계산
    private static void divideFireBall(int k) {
        int size = spots.size();

        for (int i = 0; i < size; i++) {
            // 칸 위치 정보를 추출
            Spot now = spots.poll();
            int r = now.r, c = now.c;

            // 파이어볼 좌표복구
            spots.offer(now);

            // 파이어볼 갯수 1개는 계산X
            if (map[r][c].size() < 2) continue;

//			System.out.println(r + "," + c);
//			for (int j = 0; j < map[r][c].size(); j++) {
//				FireBall fb = map[r][c].get(j);
//				System.out.println("질량:" + fb.m + "/속도:" + fb.s + "/방향:" + fb.d + "/회차:" + fb.k);
//			}
//			System.out.println("시작전에 끝");

            // 현재 k번째 이동한 파이어볼 갯수
            int fs = map[r][c].size();

            // 질량 = 파이어볼 질량 합 / 5
            // 속도 = 파이어볼 속도 합 / 파이어볼 갯수
            int nm = 0, ns = 0;
            boolean sameDir = true;
            int dir = map[r][c].get(0).d % 2;
            for (int j = 0; j < fs; j++) {
                FireBall fb = map[r][c].get(j);
                nm += fb.m;
                ns += fb.s;

                if (dir != fb.d % 2) {
                    sameDir = false;
                }
            }

            nm /= 5;
            ns /= fs;
            // 파이어볼의 질량이 0보다 큰경우
            if (nm > 0) {
                for (int j = 0; j < 4; j++) {
                    // 다르면 1,3,5,7
                    int nd = j + (j + 1);
                    if (sameDir) {  // 같으면 0,2,4,6 방향
                        nd -= 1;
                    }

                    map[r][c].add(new FireBall(nm, ns, nd, k));
                }
            }

            // 나눈 파이어볼 제거
            for (int j = 0; j < fs; j++) {
                map[r][c].remove(0);
            }

//			for (int j = 0; j < map[r][c].size(); j++) {
//				FireBall fb = map[r][c].get(j);
//				System.out.println("질량:" + fb.m + "/속도:" + fb.s + "/방향:" + fb.d + "/회차:" + fb.k);
//			}
        }
//		System.out.println();
    }

    // 1) 파이어볼 이동
    private static void moveFireBall(int k) {
        // 현재 파이어볼이 존재하는 칸 수
        int size = spots.size();
//		System.out.println(k + "번째 움직임");

        boolean[][] visited = new boolean[N + 1][N + 1];

        // 파이어볼이 존재하는 칸 개수 만큼
        for (int i = 0; i < size; i++) {
            // 칸 위치 정보를 추출
            Spot now = spots.poll();
            int r = now.r, c = now.c;

//			System.out.println("(" + r + "," + c + ")");
//			System.out.println(map[r][c].size());
            // 해당 칸 파이어볼들 이동
            while (map[r][c].size() > 0) {
                // 파이어볼 정보 (질량, 속도, 방향)
                FireBall fb = map[r][c].get(0);

                // 이건 이동이 끝난 파이어볼
                if (fb.k > k) {
                    break;
                }

                // 파이어볼 새로운 위치로 이동
                int nr = r + (dr[fb.d] * fb.s);
                int nc = c + (dc[fb.d] * fb.s);

                // 1행에서 좌측연결된 부분 N번행 & 1열에서 위로 연결된 부분 N번열
                if (nr < 1 || nc < 1 || nr > N || nc > N) {
                    if (nr < 1) {
                        nr = N + nr % N;
                    } else {
                        nr = nr % N == 0 ? N : nr % N;
                    }

                    if (nc < 1) {
                        nc = N + nc % N;
                    } else {
                        nc = nc % N == 0 ? N : nc % N;
                    }
                }
                map[nr][nc].add(new FireBall(fb));
                // 같은 위치를 2회이상 갖고있는건 불필요하기 때문
                if (!visited[nr][nc]) {
                    spots.add(new Spot(nr, nc));
                    visited[nr][nc] = true;
                }

                // 파이어볼 현재위치에서 삭제
                map[r][c].remove(0);
            }
        }
//		System.out.println();
    }

    static class Spot {
        int r, c;

        public Spot(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class FireBall {
        int m, s, d, k;

        public FireBall(int m, int s, int d, int k) {
            this.m = m;
            this.s = s;
            this.d = d;
            this.k = k;
        }

        public FireBall(FireBall fb) {
            this.m = fb.m;
            this.s = fb.s;
            this.d = fb.d;
            this.k = fb.k + 1;
        }
    }
}