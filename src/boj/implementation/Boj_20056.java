package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
마법사 상어와 파이어볼 - 골4
소요 시간: 70분
1) 1시간 초과(미제출)
조건1. 격자의 행과 열은 1번부터 N번까지 번호가 매겨져 있고, 1번 행은 N번과 연결되어 있고, 1번 열은 N번 열과 연결되어 있다.
>> 인덱스 벗어나는 경우가 없다! 라는 걸 문제를 읽고 파악하지 못해서 예제의 출력값이 이해되지않았음

소요 시간: +60분
2) 1시간 초과(미제출)
격자 넘어가는 경우를 한번에 이동 시킨 후 이전 인덱스와 현재 인덱스의 차이 만큼의 계산식을 구하려시도함

소요 시간: +80분
블로그 참고 - https://tussle.tistory.com/985

- 구현하지 못한 부분 -
1. 파이어볼 리스트를 갖는 이차원 배열을 만드는 방법을 생각하지 못함
2. 파이어볼 이동 후 인덱스 범위가 맵을 넘어갈 때(조건1), 해당 오차범위 만큼 계산할 방법의 계산식을 생각해내지 못함

- 느낀점 -
 위 두 포인트를 블로그를 통해 새로 배웠다.
 다양한 자료구조 사용방법과 계산식을 구현하는 사고방식을 높이자.
 */
public class Boj_20056 {
    static int N, M, K;
    static ArrayList<FireBall>[][] map; // Point1) 파이어볼 리스트를 이차원 배열로 구성하는 방법 - 블로그 참조
    static ArrayList<FireBall> fireBallList = new ArrayList<>();
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 격자의 폭 & 높이
        M = Integer.parseInt(st.nextToken()); // 파이어볼의 갯수
        K = Integer.parseInt(st.nextToken()); // 이동 명령 횟수
        map = new ArrayList[N][N]; // 1,1 ~ N,N이 있음

        // 각 칸마다 파이어볼의 배열 리스트 생성
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        // 초기 파이어볼 정보
        for (int i = 0; i < M; i++) {
            int[] fb = new int[5];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                fb[j] = Integer.parseInt(st.nextToken());
            }
            fireBallList.add(new FireBall(fb[0] - 1, fb[1] - 1, fb[2], fb[3], fb[4]));
        }

        // K번 이동 & 분열 명령 수행
        while (K > 0) {
            moveFireBall();
            divideFireBall();
            K--;
        }

        // 남은 파이어볼의 질량의 합
        int resultM = 0;
        for (FireBall fb : fireBallList) {
            resultM += fb.m;
        }
        System.out.println(resultM);
    }

    // 1. 이동
    private static void moveFireBall() {

        for (FireBall fb : fireBallList) {  // 모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동
            // Point2) N으로 전체 값을 나눈 나머지 값으로 인덱스의 범위 조정 (음수가 나올 경우를 위해 + N) - 블로그 참조
            int nr = (fb.r + N + dr[fb.d] * (fb.s % N)) % N;
            int nc = (fb.c + N + dc[fb.d] * (fb.s % N)) % N;
            fb.r = nr;
            fb.c = nc;
            map[fb.r][fb.c].add(fb);
        }
    }

    // 2. 분열
    private static void divideFireBall() {

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c].size() < 2) { // 파이어볼의 갯수가 2미만일 때
                    map[r][c].clear(); // 이동한 파이어볼 정보 제거
                    continue;
                }

                int mSum = 0, sSum = 0, odd = 0, even = 0, size = map[r][c].size();

                for (FireBall fb : map[r][c]) {
                    mSum += fb.m;
                    sSum += fb.s;
                    if (fb.d % 2 == 0) {
                        even++;
                    } else {
                        odd++;
                    }
                    fireBallList.remove(fb); // 합쳐진 파이어볼 제거
                }
                map[r][c].clear();
                mSum /= 5;  // 질량 = (합친 파이어볼 질량의 합) / 5
                if (mSum == 0) {  // 질량이 0인 파이어볼은 소멸
                    continue;
                }
                sSum /= size;  // 속력 = (합친 파이어볼 속력의 합) / (합친 파이어볼의 개수)
                // 파이어볼은 4등분
                if (odd == size || even == size) { // 합친 파이어볼 방향이 모두 홀수/짝수이면 방향은 0, 2, 4, 6
                    for (int dir = 0; dir < 8; dir += 2) {
                        fireBallList.add(new FireBall(r, c, mSum, sSum, dir));
                    }
                } else {  // 그렇지 않으면 1, 3, 5, 7
                    for (int dir = 1; dir < 8; dir += 2) {
                        fireBallList.add(new FireBall(r, c, mSum, sSum, dir));
                    }
                }
            }
        }
    }

    static class FireBall {
        int r, c, m, s, d;

        private FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}
