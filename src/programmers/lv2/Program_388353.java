package programmers.lv2;

import java.util.*;
/*
지게차와 크레인 - lv2
소요시간 - 51분

[풀이설명]
- 컨테이너 c인 모든 경우
    - 지게차 경우
        1) bfs로 외부와 연결되어 있는지 확인
        2) 연결된 컨테이너 위치만 q에 담아 한번에 처리
    - 크레인 경우
        - 전부 꺼냄 처리

p.s
- 더 빠르게 구현해라. 의심하지마라.
 */
public class Program_388353 {
    static int N, M;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    // 해당 컨테이너가 바깥과 연결되어있는지 체크
    private static boolean bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.offer(new int[]{x, y});
        visited[x][y] = true;

        boolean isOut = false;  // 외부연결 여부 판별
        while (!q.isEmpty() && !isOut) {    // 확인 가능한 위치가 없거나 외부연결 확인되었으면 루프 종료
            int[] now = q.poll();
            // 상하좌우 확인
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                // 외부와 연결된 경우
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    isOut = true;   // 외부연결 확인
                    break;
                }
                // 방문 x + 컨테이너가 나간 자리면 외부와 연결되었을 수 있음
                if (!visited[nx][ny] && map[nx][ny] == '0') {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        return isOut;
    }

    private static void initSet(String[] storage) {
        N = storage.length;
        M = storage[0].length();
        map = new char[N][M];
        for (int i = 0; i < N; i++) map[i] = storage[i].toCharArray();
    }

    public static int solution(String[] storage, String[] requests) {
        // 세팅
        initSet(storage);
        // requests 수만큼 동작
        for (String req : requests) {
            char c = req.charAt(0);
            Queue<int[]> q = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 해당 컨테이너면
                    if (c == map[i][j]) {
                        if (req.length() == 2) { // 크레인
                            map[i][j] = '0';    // 컨테이너 꺼내기
                        } else {                // 지게차
                            if (bfs(i, j)) {  // 컨테이너 외부연결 여부 확인
                                q.offer(new int[]{i, j});   // 외부연결 컨테이너 쌓기
                            }
                        }
                    }
                }
            }

            if (req.length() == 2) continue;
            // 지게차는 바깥과 연결된 컨테이너 꺼냄
            while (!q.isEmpty()) {
                int[] now = q.poll();
                map[now[0]][now[1]] = '0';
            }
        }
        // 남은 컨테이너 수 세기
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != '0') {
                    result++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"AZWQY", "CAABX", "BBDDA", "ACACA"}, new String[]{"A", "BB", "A"}));
        System.out.println(solution(new String[]{"HAH", "HBH", "HHH", "HAH", "HBH"}, new String[]{"C", "B", "B", "B", "B", "H"}));
    }
}
