package swea.d4;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
길찾기 - D4
소요 시간: 43분
단순 그래프 탐색 문제
아쉬운 실수? > 상수로 선언한 result 값을 초기화하지 않은걸 초반에 캐치하지 못해서 40분 걸림
 */

public class Swea_1219 {

    static int T, cnt, result;
    static int[] route1, route2;
    static boolean[] second;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        for (int test_case = 1; test_case <= 10; test_case++) {

            result = 0;
            route1 = new int[100];
            route2 = new int[100];
            second = new boolean[100];

            st = new StringTokenizer(br.readLine());
            T = Integer.parseInt(st.nextToken());
            cnt = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < cnt; i++) {
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                if (!second[u]) {
                    second[u] = true;
                    route1[u] = v;
                } else {
                    route2[u] = v;
                }
            }

            bfs();
            bw.write("#" + T + " " + result + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static void bfs() {

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);

        while (!q.isEmpty()) {
            int node = q.poll();

            if (node == 99) {
                result = 1;
                break;
            }

            if (route1[node] != 0) {
                q.offer(route1[node]);
            }

            if (route2[node] != 0) {
                q.offer(route2[node]);
            }
        }
    }
}
