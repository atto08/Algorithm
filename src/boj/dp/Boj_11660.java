package boj.dp;

import java.io.*;
import java.util.StringTokenizer;

/*
1) 시간초과
==> dp여도 단순 누적합이라고 생각해서 그냥 계산하는 식으로 풀었음
2) 미리 누적합을 저장해놓고 찾는 형식으로 해결

원인 - 아직 dp의 장점 활용을 잘 못하는 거 같다.
       값에 알맞은 경우를 미리 저장한 dp 배열 or list를 만들어 놓고 출력 하는 방향으로 익숙해지도록 많은 문제풀이 ㄱㄱ
 */
public class Boj_11660 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][N + 1];
        int[][] dp = new int[N + 1][N + 1];

        // 배열 입력 받기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + map[i][j];
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int result = dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1];
            bw.write(result + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
