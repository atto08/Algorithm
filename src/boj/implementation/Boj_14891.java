package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
톱니 바퀴 - 골5
소요 시간: 2시간

원인:
1) 처음에 N과 S는 서로 상극이기에 같은 녀석이 만나면 돌아간다고 생각함.
>> 맞닿은 모서리가 같다면 움직이지 않고, 다르다면 반대 방향으로 움직인다.
2) 하나씩 돌고 난 후에 만난 2시 6시 자리 극이 서로 다를때라고 생각함.
>> 하나씩 돌리고 확인하지 않고 지정된 톱니바퀴 부터 전부 각 2번 6번에 다른 값을 갖고있는지 확인 후 가진 녀석들만 체크함

결론 -
재밌는 구현 문제였다. 재귀함수로 구현할지 너비우선탐색으로 구현할지 고민해봤고
기능별로 메소드를 분리하면서 기능별 메소드 구현에 장점을 알게 됨.
고민 하는 과정에서 평소보다 많은 시간을 투자했지만 재밌는 문제!
 */
public class Boj_14891 {
    static char[][] gears = new char[4][4];
    static boolean[] visited;
    static int[] directions;
    static int[] dg = {-1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) gears[i] = br.readLine().toCharArray();

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gear = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            visitGear(gear, dir);
        }

        int result = 0;
        for (int i = 0; i < 4; i++) {
            if (gears[i][0] == '1') {
                result += (int) Math.pow(2, i);
            }
        }

        System.out.println(result);
    }

    private static void visitGear(int gear, int dir) {

        visited = new boolean[4];
        visited[gear] = true;
        directions = new int[4];

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{gear, dir});

        while (!q.isEmpty()) {
            int[] current = q.poll();

            int currentGear = current[0], currentDir = current[1];
            directions[currentGear] = currentDir;

            for (int i = 0; i < 2; i++) {
                int nowGear = current[0] + dg[i];

                if (nowGear < 0 || nowGear >= 4) continue;

                if (!visited[nowGear]) {

                    if (i == 0) { // 왼쪽에 있는 톱니
                        if (gears[currentGear][6] != gears[nowGear][2]) {
                            visited[nowGear] = true;
                            q.offer(new int[]{nowGear, -currentDir});
                        }

                    } else { // 오른 쪽에 있는 톱니
                        if (gears[currentGear][2] != gears[nowGear][6]) {
                            visited[nowGear] = true;
                            q.offer(new int[]{nowGear, -currentDir});
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            if (!visited[i]) continue;

            rotateGear(i);
        }
    }

    private static void rotateGear(int gear) {
        if (directions[gear] > 0) { // 시계 방향
            char temp = gears[gear][7];
            for (int i = 7; i > 0; i--) {
                gears[gear][i] = gears[gear][i - 1];
            }
            gears[gear][0] = temp;
        } else { // 반시계 방향
            char temp = gears[gear][0];
            for (int i = 0; i < 7; i++) {
                gears[gear][i] = gears[gear][i + 1];
            }
            gears[gear][7] = temp;
        }
    }
}
