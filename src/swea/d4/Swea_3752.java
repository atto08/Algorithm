package swea.d4;

import java.io.*;
import java.util.StringTokenizer;

/*
가능한 시험 점수 - D4

1차 시도
소요 시간: 42분
1) 시간 초과
검출하는 과정에서 >> 2의 N의 경우의 수 * N 만큼의 시간이 돌아감 이 때문이라 생각됨
동점이 존재할때 (중복) 하는 경우는 배제해줘야 시간단축이 가능하다 생각됨
그렇다면 어떤 식으로 ? 기준을 인덱스가 아닌 값에 맞춰서?
>> 고민해볼 필요가 있음

2차 시도 - Retry
소요 시간: 21분
2) 시간 초과
원인: DP로 풀어야 하는 문제라 생각 됨
>> 하지만 어떤 점화식으로 dp를 만들어 구현해야할지 감이 안잡힘 [1시간 소요]

GPT에게 기존 시간초과 코드 제공 후 DP 알고리즘으로 풀 방향성을 제공받은 후 참고 -
1) 모든문제를 맞춘 경우의 수 까지의 길이를 갖는 boolean 타입의 dp 배열을 생성 >> dp배열을 boolean타입으로 설정할 생각을 해본적이 없었다.
2) 0~N-1까지의 문제를 돈다
3) totalSum ~ scores[i]가 될때까지 -1씩 돈다
    3-1) 돌면서 현재 위치 "j 혹은 j-scores[i] 점수가 나올 수 있다면 j는 나올 수 있는 값" >> 해당 점화식을 구현하지 못함

충분히 작성할 수 있는 코드를 생각해내지 못한 아쉬움이 큼
더 많이 풀어보기!
*/
public class Swea_3752 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int[] scores = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            int totalSum = 0;
            for (int i = 0; i < N; i++) {
                scores[i] = Integer.parseInt(st.nextToken());
                totalSum += scores[i];
            }

            boolean[] dp = new boolean[totalSum + 1]; // 1)
            dp[0] = true; // 모든 문제를 틀리면 0은 항상 나오기 때문
            for (int i = 0; i < N; i++) { // 2번
                for (int j = totalSum; j >= scores[i]; j--) { // 3번
                    dp[j] = dp[j] || dp[j - scores[i]]; // 3-1번
                    // 위에 줄 [함축버전] >> dp[j] |= dp[j-scores[i]];
                }
            }

            int result = 0;
            for (boolean possible : dp) {
                if (possible) {
                    result++;
                }
            }

            bw.write("#" + test_case + " " + result + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
