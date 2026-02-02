package boj.greedy;

import java.io.*;
import java.util.*;
/*
신입 사원 - 실1
소요시간 - 1시간 초과

[문제설명]
- N명의 신입사원 지원자 중 채용할 인원은
- A의 서류, 면접 점수 중 B의 서류/면접 점수 중에 적어도 하나 이상 높아야함.
    ex)
    -> A의 서류 점수는 B보다 높지만 B의 면접 점수보다 높은건 합격.
    -> 하지만, A의 서류, 면접 점수 모두 B의 점수보다 낮으면 불합격.
    -> 상대적인 것. 그러한 인원이 1명이라도 존재하면 그인원은 채용하지 않음.
- 채용 가능한 신입사원의 최대수 구하기

p.s
- 문제는 ‘지원자 번호’ 문제가 아니라 ‘등수 좌표’ 문제인데, 사람 번호를 하나 더 붙여서 해석하면서 충돌이 발생함.
- 테스트 케이스 경우도 내가 이해한대로 사람번호를 붙여서 예시의 값이 똑같이 나오다보니 더더욱 문제를 파악하지 못함.

 */
public class Boj_1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] interviewRank = new int[N + 1];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int doc = Integer.parseInt(st.nextToken());
                int interview = Integer.parseInt(st.nextToken());

                interviewRank[doc] = interview;
            }

            int max = N + 1;
            int cnt = 0;
            for (int doc = 1; doc <= N; doc++) {
                int interview = interviewRank[doc];
                if (interview < max) {
                    max = interview;
                    cnt++;
                }
            }
            result.append(cnt).append("\n");
        }
        System.out.println(result);
    }
}
