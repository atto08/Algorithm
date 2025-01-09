package boj.implementation;

import java.io.*;
import java.util.*;

// 프린터 큐 - 실3
// 소요 시간 - 43분
// 반복문 안에서 조건 거는데 헤맴.
public class Boj_1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Queue<int[]> q = new LinkedList<>();
            int[] imp = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                imp[i] = Integer.parseInt(st.nextToken());
                q.offer(new int[]{i, imp[i]});
            }

            Arrays.sort(imp);
            int idx = N - 1;
            while (!q.isEmpty()) {
                int[] now = q.poll();

                if (now[1] == imp[idx]) {
                    if (now[0] == M) {
                        bw.write(N - idx + "\n");
                        break;
                    }
                    idx--;
                } else {
                    q.offer(now);
                }
            }
        }

        bw.flush();
    }
}
