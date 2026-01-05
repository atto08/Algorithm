package boj.greedy;

import java.io.*;
import java.util.*;

public class Boj_1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        // 테스트케이스 수 만큼
        while (T > 0) {
            int N = Integer.parseInt(br.readLine());

            User[] pos = new User[N];
            int[] rank1 = new int[N];   // 서류 등수
            int[] rank2 = new int[N];   // 면접 등수
            for (int num = 0; num < N; num++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int r1 = Integer.parseInt(st.nextToken()) - 1;
                int r2 = Integer.parseInt(st.nextToken()) - 1;
                pos[num] = new User(r1, r2);
                rank1[r1] = num;
                rank2[r2] = num;
            }

            Stack<Integer> stack = new Stack<>();
            for (int n : rank1) stack.push(n);
            int max = N;

            while (!stack.isEmpty()) {
                int n = stack.pop();
                int r2 = pos[n].r2;
                if (r2 == 0) continue;

                int target = rank2[r2 - 1];
                if (stack.contains(target)) max--;
            }
            result.append(max).append("\n");
            T--;
        }
        System.out.println(result);
    }

    static class User {
        int r1, r2;

        public User(int r1, int r2) {
            this.r1 = r1;
            this.r2 = r2;
        }
    }
}
