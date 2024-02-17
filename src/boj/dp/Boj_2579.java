package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
조건에서 어려움을 느낌
1. 계단을 오를 때 한 계단, 또는 두 계단을 오를 수 있다.
2. 연속된 3개의 계단을 밟으면 안된다. (즉, 한 계단씩 올라갈 때 최대 연속으로 2번만 한계단씩 오를 수 있다는 의미)
3. 마지막 계단은 '반드시' 밟아야 한다.

최대 경우이기에 Math.max 메소드를 사용할 생각까진 함
그러나 안에서 비교할 대상을 조건에 적용하지 못함.

* 일주일 뒤에 다시 풀어봄 - 제한사항에 관한 규칙 찾기 실패.
dp[i] = Math.max(dp[i - 2], dp[i - 3] + stair[i - 1]) + stair[i];
ㄴ 이게 이제는 이해완료. 그래도 생각하지 못해낸게 아쉬움.
 */
public class Boj_2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        int[] stair = new int[N + 1];


        for (int i = 1; i <= N; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        }

        // index = 0 은 시작점이다.
        dp[1] = stair[1];

        // N 이 1이 입력될 수도 있기 때문에 예외처리를 해줄 필요가 있다.
        if (N >= 2) {
            dp[2] = stair[1] + stair[2];
        }

        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + stair[i - 1]) + stair[i];
        }

        System.out.println(dp[N]);
        br.close();
    }
}
