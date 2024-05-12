package swea.d3;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
암호문1 - D3
소요 시간: 46분
원인: rotate 함수 첫번째 반복문 이후 명령에 있는 번호들을 넣고 종료하여 null인 경우가 발생
해결: N의 길이만큼 비어있따면 나머지를 q에서 삽임
 */
public class Swea_1228 {
    static int N, O; // 암호문 길이 & 명령어 횟수
    static Queue<Integer> q;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int test_case = 1; test_case <= 10; test_case++) {

            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            q = new LinkedList<>();
            for (int i = 0; i < N; i++) { // N개의 암호 나열
                int number = Integer.parseInt(st.nextToken());
                q.offer(number);
            }

            O = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < O; i++) { // N번
                String bar = st.nextToken();// 막대기
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                Queue<Integer> q2 = new LinkedList<>();
                for (int j = 0; j < y; j++) {
                    int number = Integer.parseInt(st.nextToken());
                    q2.offer(number);
                }
                rotate(x, y, q2);
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                sb.append(q.poll()).append(" ");
            }

            bw.write("#" + test_case + " " + sb + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static void rotate(int x, int y, Queue<Integer> q2) {

        Queue<Integer> result = new LinkedList<>();
        for (int i = 0; i < x; i++) {
            result.offer(q.poll());
        }

        for (int i = x; i < x + y; i++) {
            result.offer(q2.poll());
        }

        if (x + y < N) {
            for (int i = x + y; i < N; i++) {
                result.offer(q.poll());
            }
        }

        q = result;
    }
}