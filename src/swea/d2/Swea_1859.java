package swea.d2;

import java.io.*;
import java.util.StringTokenizer;

/*
백만장자 프로젝트 - D2
총 소요 시간: 76분

소요 시간: 31분
1) 시간 초과
원인: 이중 for문으로 인한 N* N-1 * ... * 1의 시간이 걸림

해결방법: 마지막 날 >> 첫째 날 순서로 max값을 dp 배열에 저장해놓음 >> 시간단축
소요 시간: +45분
2) 테스트 케이스 10 중 7개 정답
원인: 최대 이익 값의 범위가 int 타입의 최대 범위를 넘어갈 수 있음

>> resultd의 타입을 long 타입으로 변경
소요 시간: +1분
3) 통과
 */
public class Swea_1859 {
    static int N;
    static long result;
    static int[] price;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());

            System.out.println(test_case);
            price = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                price[i] = Integer.parseInt(st.nextToken());
            }

            result = 0;
            int[] dp = new int[N];
            dp[N - 1] = price[N - 1];
            for (int i = N - 2; i >= 0; i--) {
                if (price[i] > dp[i + 1]) {
                    dp[i] = price[i];
                } else {
                    dp[i] = dp[i + 1];
                }
            }

            for (int i = 0; i < N; i++) {
                result += dp[i] - price[i];
            }
            bw.write("#" + test_case + " " + result + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
