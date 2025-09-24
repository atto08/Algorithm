package codetree;

/*

1. 탐사 진행
5 * 5 격자내에서
- 3 * 3 격자 선택 -> 회전 가능(90, 180, 270) 시계방향
- 선택된 격자는 항상 회전 진행

회전 목표
- 1) 유물 1차 획득 가치를 최대화 하기
    - 획득 가치 최대인 경우가 여러 경우인 때,
    - 2) 회전각도가 가장 작은 방법 선택
        - 같은 회전각도가 여러개 일때,
        - 3) 회전 중심 좌표의 열이 가장 작은 구간 선택
            - 4) 열이 같다면 행이 가장 작은 구간 선택

2. 유물 획득
- 상하좌우로 인접한 같은 종류 유물 조각
    -> 3개 이상이 연결된 경우 => 유물이 되어 조각은 사라짐.
EX) 3 + 4 -> 7

1~7 사이 숫자 M개 존재
유적 조각이 사라지면, 아래 순서대로 채워짐
- 1) 열번호가 작은 순서대로
- 2) 행번호가 큰 순서대로
- 주의) 벽면 숫자는 충분함.


3. 탐사 반복
- K번 진행
- K번 진행하지 않았는데, 유물을 획득 방법이 존재하지 않는다면?
    -> 즉시 종료.
*/

import java.io.*;
import java.util.*;

public class Main_24년상반기오전1번 {

    // 탐사 반복 횟수, 벽면에 적힌 유물 조각 갯수, 유물 가치의 총합
    static int K, M, result;

    // 유물 조각 정보, 유물 가치가 가장 높은 맵의 경우
    static int[][] map;

    // 임시 배열
    static int[][] copyMap;

    // 최대 값을 갖는 상태 배열
    static int[][] maxCondition;
    // 최솟 값을 가져야하는 X, Y, D (행,열,각도)최댓값을 갖는 유물 가치합
    static int minX, minY, minD, maxVal;

    // M 의 정보
    static int[] yumuls;

    // yumuls의 인덱스
    static int addMIdx;

    // 방문 체크
    static boolean[][] visited;

    // 상하좌우 확인용
    static final int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    // 채워야할 유물 좌표
    static PriorityQueue<Pair> pq;

    // 유물 우선순위 매기는 타입
    static class Pair implements Comparable<Pair> {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair other) {
            if (this.y != other.y)
                return Integer.compare(this.y, other.y);
            return Integer.compare(other.x, this.x);
        }
    }

    // 상하좌우 인접 체크
    private static int countConnected(int x, int y, int val) {

        int cnt = 1;
        Queue<Pair> q = new LinkedList<>();
        visited[x][y] = true;
        q.offer(new Pair(x, y));

        while (!q.isEmpty()) {
            Pair now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;

                if (!visited[nx][ny] && copyMap[nx][ny] == val) {
                    visited[nx][ny] = true;
                    q.offer(new Pair(nx, ny));
                    cnt++;
                }
            }
        }

        if (cnt >= 3) return cnt;
        return 0;
    }

    private static int[][] makeCopyMap() {
        int[][] copy = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }

    // 최댓값인 경우의 유적지 저장
    private static void saveMaxCondition() {
        maxCondition = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                maxCondition[i][j] = copyMap[i][j];
            }
        }
    }

    // 회전한 유적지(copyMap) 유물 확인
    private static void checkConnected(int x, int y, int d) {

        // 유물 여부 체크
        int nowVal = 0;
        visited = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!visited[i][j]) {
                    int cnt = countConnected(i, j, copyMap[i][j]);

                    if (cnt >= 3) nowVal += cnt;
                }
            }
        }

        // (유물이 없음 == 연결된 경우가 없음)-> 무시
        if (nowVal == 0) {
            return;
        }

        // 우선순위에 따라서 값 교체
        if (nowVal > maxVal) {
            minX = x;
            minY = y;
            minD = d;
            maxVal = nowVal;
            saveMaxCondition();
        }
        // 최대 유물가치가 같은 경우
        else if (maxVal == nowVal) {
            // 각도가 가장 작은 방법
            if (d < minD) {
                minX = x;
                minY = y;
                minD = d;
                saveMaxCondition();
            }
            // 같은 각도 경우
            else if (d == minD) {
                if (y < minY) {
                    minX = x;
                    minY = y;
                    saveMaxCondition();
                }
                // 열이 같은 경우
                else if (y == minY) {
                    if (x < minX) {
                        minX = x;
                        saveMaxCondition();
                    }
                }
            }
        }
    }

    // ** 1) 탐사 시작 **
    private static void findYumul() {

        // 초기 값 복사하기
        copyMap = makeCopyMap();

        // 초기 값 가능한 수보다 조금 높게 지정
        minX = 5;
        minY = 5;
        minD = 360;
        maxVal = 0;

        // 초기 유적지 유물 여부 체크
        checkConnected(5, 5, 360);

        // 완전 탐색 (1,1) ~ (3,3)
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                // 맵 초기화
                copyMap = makeCopyMap();

                // 90, 180, 270도 확인하기
                for (int c = 1; c <= 3; c++) {

                    // 2번 돌려야 시계방향 90도 회전
                    for (int k = 0; k < 2; k++) {
                        rotate(i, j);
                    }

                    // 회전 한 상태 유적지 BFS 탐색
                    checkConnected(i, j, (90 * c));
                }
            }
        }
    }

    private static void bfsAndBomb(int x, int y, int val) {

        int cnt = 1;
        Queue<Pair> q = new LinkedList<>();
        visited[x][y] = true;
        q.offer(new Pair(x, y));

        Stack<Pair> stack = new Stack<>();
        while (!q.isEmpty()) {
            Pair now = q.poll();
            stack.add(now);

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5)
                    continue;

                if (!visited[nx][ny] && maxCondition[nx][ny] == val) {
                    visited[nx][ny] = true;
                    q.offer(new Pair(nx, ny));
                    cnt++;
                }
            }
        }

        if (cnt < 3) {
            stack.clear();
        } else {
            while (!stack.isEmpty()) {
                Pair now = stack.pop();
                pq.offer(new Pair(now.x, now.y));
            }
        }
    }

    // ** 2) 유물 획득 **
    private static void addYumul() {

        // val != 0(터트릴 곳이 없을때 까지) -> 반복
        int val = -1;
        while (val != 0) {
            // 유물 3개 이상인 곳 중 유물 가치 제일 큰 경우 찾아서 터트리기
            pq = new PriorityQueue<>();
            visited = new boolean[5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (!visited[i][j]) {
                        bfsAndBomb(i, j, maxCondition[i][j]);
                    }
                }
            }
            val = pq.size();
            result += val;

            // 연쇄 유물 획득 & 제거
            while (!pq.isEmpty()) {
                Pair now = pq.poll();
                maxCondition[now.x][now.y] = yumuls[addMIdx++];
            }
        }
    }

    // 3 * 3 회전
    private static void rotate(int x, int y) {

        // System.out.println("start - (" + x + "," + y + ")");
        int prev = copyMap[x - 1][y - 1];

        // System.out.println("prev : " + prev);
        // 좌측 위로
        for (int i = x - 1; i < x + 1; i++) {
            copyMap[i][y - 1] = copyMap[i + 1][y - 1];
            // System.out.println(i + "," + (y - 1) + "/ & /"+ (i + 1) + "," + (y - 1));
        }
        // 아래 좌로
        for (int j = y - 1; j < y + 1; j++) {
            copyMap[x + 1][j] = copyMap[x + 1][j + 1];
            // System.out.println((x + 1)+ "," + j + "/ & /"+ (x + 1) + "," + (j + 1));
        }
        // 우측 아래로
        for (int i = x + 1; i > x - 1; i--) {
            copyMap[i][y + 1] = copyMap[i - 1][y + 1];
            // System.out.println(i + "," + (y + 1) + "/ & /"+ (i - 1) + "," + (y + 1));
        }
        // 위 우로
        for (int j = y + 1; j > y; j--) {
            copyMap[x - 1][j] = copyMap[x - 1][j - 1];
            // System.out.println((x - 1)+ "," + j + "/ & /"+ (x - 1) + "," + (j - 1));
        }

        copyMap[x - 1][y] = prev;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[5][5];
        yumuls = new int[M];
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            yumuls[i] = Integer.parseInt(st.nextToken());
        }
        // 입력 값 종료.

        // 초기화
        addMIdx = 0;

        // K번 탐사
        for (int test = 1; test <= K; test++) {
            // 유물 가치 초기화
            result = 0;
            // ** 1. 탐사 진행 **
            findYumul();
            // ** 2. 유물 획득 **
            addYumul();
            map = maxCondition; // 초기화
            if (result == 0) break;

            bw.write(result + " ");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}

/*
6시간 내외 걸린 듯 하다.
4시간안에 2문제인데 이대로면 너무 아쉬울 거 같다.

이번엔 전체적으로 나쁘지 않았다.
- 회전 시키기
- 3개 이상 연결된(인접한) 조각 찾기
-

그렇다면, 어디서 헤맸을까??
- 첫 번째로 한번에 처리하려는 습관이 있는 것 같다.
    -> 1) 3개 이상 인접한 곳을 찾으면서
    -> 2) 인접한 곳의 좌표를 갖고 있으려고 했다.
- 과한 모듈화 + 여러개 변수(배열, 정수 등)를 사용하다보니 변수명이 헷갈렸다.
    -> 변수의 네이밍을 짧게 말고 알아볼 수 있게 접근하자.
- bfs 메서드 구현시, 원하는 동작이 아니란 것에 의심했다.
    -> 나에 대한 확신이 부족한거 같다. 나를 믿자.
- 디버깅? System.out.println()을 많이 찍어봄.
    -> 나쁘지않아. 대신 헤매지마라. 빠르게 다시 흐름을 찾아라.

우선순위 매기기 -> 우선순위 큐 or 조건문 활용

화이팅! 나야.
 */