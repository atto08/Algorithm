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
            List<User> pos = new ArrayList<>();
            int N = Integer.parseInt(br.readLine());
            int[] rank1 = new int[N];   // 서류 등수
            int[] rank2 = new int[N];   // 면접 등수
            for (int num = 0; num < N; num++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int i1 = Integer.parseInt(st.nextToken()) - 1;
                int i2 = Integer.parseInt(st.nextToken()) - 1;
                pos.add(new User(i1, i2));

                rank1[i1] = num;
                rank2[i2] = num;
            }

            // 1등부터 N등까지 확인하기
            boolean[] visited = new boolean[N];
            int max = 0;
            for (int i = 0; i < N; i++) {
                int r1 = rank1[i];
                if (visited[r1]) continue;   // 이미 탈락임
                //
                max++;
                // 현재 서류등수보다 낮은등수 지원자들 번호 방문처리
                for (int j = i + 1; j < N; j++) visited[rank1[j]] = true;
                // 현재 서류 상위인 녀석의 면접 등수
                int r2 = pos.get(r1).r2;
                // 현재 지원자 보다 면접 등수가 높은 애들은 다시 살리기
                for (int j = r2 - 1; j >= 0; j--) visited[rank2[j]] = false;
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
