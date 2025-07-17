package programmers.lv1;
/*
키패드 누르기 - lv1
소요시간 - 57분

문제설명:
- 상하좌우 이동가능. 이동한칸 == 거리 1
- 1,4,7 왼엄지 / 3,6,9 오른엄지 / 2,5,8,0 더 가까운 엄지
    - 거리가 같으면 >> 왼손잡이 => 왼엄지, 오른손잡이 => 오른엄지
    - 맨처음 왼손은 *, 오른손은 #에 위치

풀이설명:
키패드
1(0,0) 2(0,1) 3(0,2)
4(1,0) 5(1,1) 6(1,2)
7(2,0) 8(2,1) 9(2,2)
*(3,0) 0(3,1) #(3,2)

- 현재 손가락 위치 실시간으로 갖고있기.
- numbers[] 순서대로 진행
    - 누른 번호에 대해서만 왼손과 오른손의 현재 위치 갖고 있기
    - 오른손 왼손 둘다 방문 가능해야함.
    - 1,4,7과 3,6,9에 대해서는 왼 오가 무조건.
*/

import java.util.*;

public class Program_67256 {
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static int[][] locations = {{3, 1}, {0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};
    static int[] locL = {3, 0}, locR = {3, 2};

    public static String solution(int[] numbers, String hand) {
        StringBuilder result = new StringBuilder();
        boolean[][][] visited;

        for (int num : numbers) {
            if (num == 1 || num == 4 || num == 7) {
                locL = locations[num];
                result.append('L');
            } else if (num == 3 || num == 6 || num == 9) {
                locR = locations[num];
                result.append('R');
            } else {
                visited = new boolean[4][3][2];
                char c = bfs(locL, locR, visited, num, hand);
                result.append(c);
            }
        }

        return result.toString();
    }

    private static char bfs(int[] l, int[] r, boolean[][][] visited, int target, String hand) {
        Queue<int[]> q = new LinkedList<>();
        // visited[][][0] ==> left
        // visited[][][1] ==> right

        q.offer(new int[]{l[0], l[1], 0, 0}); // (x, y, dist, dir)
        q.offer(new int[]{r[0], r[1], 0, 1});
        visited[l[0]][l[1]][0] = true;
        visited[r[0]][r[1]][1] = true;

        int L = Integer.MAX_VALUE;
        int R = Integer.MAX_VALUE;
        int X = locations[target][0], Y = locations[target][1];
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0], y = now[1], dis = now[2], dir = now[3];

            if (X == x && Y == y) {
                if (dir == 0) {
                    L = dis;
                } else {
                    R = dis;
                }
            }

            for (int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= 4 || ny >= 3) continue;

                if (!visited[nx][ny][dir]) {
                    visited[nx][ny][dir] = true;
                    q.offer(new int[]{nx, ny, dis + 1, dir});
                }
            }
        }
        if (L == R) {
            if (hand.equals("left")) {
                locL = locations[target];
                return 'L';
            }
            locR = locations[target];
            return 'R';
        } else if (L > R) {
            locR = locations[target];
            return 'R';
        }
        locL = locations[target];
        return 'L';
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, "right"));
        System.out.println(solution(new int[]{7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}, "left"));
        System.out.println(solution(new int[]{1,2,3,4,5,6,7,8,9,0},"right"));
    }
}
