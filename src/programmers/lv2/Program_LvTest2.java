package programmers.lv2;

import java.util.*;

/*
프로그래머스 스킬체크 레벨2
1) 게임 맵 최단거리 - lv2 (15분)
2) 기능개발 - lv2(40분)

p.s
- 둘다 풀어본적 있는 문제였다.
- 또한 기본적이기도 하고 제한조건 범위가 작기 때문에 크게 신경 쓸 부분이 존재하지 않았다.
- 주어진 시간내에 푸는 연습치고는 좋았다.
- 코딩테스트 잘하고싶다~
 */
public class Program_LvTest2 {

    static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};

    // 게임 맵 최단 거리
    public static int solution1(int[][] maps) {
        int answer = -1;

        int N = maps.length;
        int M = maps[0].length;
        boolean[][] visited = new boolean[N][M];

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 1});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == N - 1 && cur[1] == M - 1) {
                answer = cur[2];
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if (!visited[nx][ny] && maps[nx][ny] > 0) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny, cur[2] + 1});
                }
            }
        }

        return answer;
    }

    // 기능 개발
    public static int[] solution2(int[] progresses, int[] speeds) {

        int idx = 0; // 현재 작업 위치
        int len = progresses.length; // 작업 갯수
        int size = progresses.length; // 작업 갯수 소모용

        List<Integer> list = new ArrayList<>();
        // 현재 작업 기준 잔업량을 계산 & 그 일수만큼 곱해서 전부 더하기.
        while (size > 0) {
            int cnt = 0; // 현재 작업 완료 갯수
            // 현재 작업이 완료되지 않았을 때
            if (progresses[idx] < 100) {
                int day = 1;
                // 현재 작업량 완료를 위해서 걸리는 날짜 구하기.
                while (true) {
                    if (progresses[idx] + (speeds[idx] * day) >= 100) {
                        break;
                    } else {
                        day++;
                    }
                }
                // 남은 작업들에 현재 작업완료되는 날짜만큼 더하기.
                boolean isOver = true; // 현재 작업완료 가능여부
                for (int i = idx; i < len; i++) {
                    progresses[i] += speeds[i] * day;
                    if (progresses[i] >= 100 && isOver) {
                        cnt++;
                        idx++;
                        size--;
                    } else {
                        isOver = false;
                    }
                }
            }
            // 현재 작업이 완료된 경우
            else {
                for (int i = idx; i < len; i++) {
                    if (progresses[i] >= 100) {
                        cnt++;
                        idx++;
                        size--;
                        continue;
                    }
                    break;
                }
            }
            if (cnt > 0) list.add(cnt);
        }

        // list to Array
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) answer[i] = list.get(i);

        return answer;
    }


    public static void main(String[] args) {
        // 1) 테스트 케이스
        System.out.println(solution1(new int[][]{{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}}));
        System.out.println(solution1(new int[][]{{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 0}, {0, 0, 0, 0, 1}}));
        // 2) 테스트 케이스
        System.out.println(Arrays.toString(solution2(new int[]{93, 30, 55}, new int[]{1, 30, 5})));
        System.out.println(Arrays.toString(solution2(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1})));
    }
}
