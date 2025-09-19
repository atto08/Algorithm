package boj.implementation;

import java.io.*;
import java.util.*;

/*
회전 초밥 - 실1
소요시간 - 45분

[문제설명]
- 회전 초밥에서 손님이 먹을 수 있는 초밥 가짓수의 최댓값 구하기
- k개를 연속으로 먹으면 c번 초밥을 먹을 수 있는 쿠폰 무료 제공

[풀이설명]
- (i ~ i+k) 구간별로 슬라이딩 윈도우로 체크하기
- sushi[]에서 N을 넘어가는 구간은 end(i+k) - N 만큼 넘겨서 다시 확인하기

p.s
- 잘푼거 같은데, 범위 넘어갈 때, 규칙? 찾는데 시간을 많이 썼음.
- 그래도 단계별로 문제파악 -> 구현방법선택 -> 구현 순으로 잘 접근한거 같음.
*/
public class Boj_2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        // 벨트위 올라간 초밥
        int[] sushi = new int[N];
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int result = 0;
        boolean[] ateSushi;

        // 슬라이딩 윈도우로 k개 씩 측정
        for (int i = 0; i < N; i++) {
            int cnt = 0;
            ateSushi = new boolean[D + 1];
            int end = i + K;
            if (end - 1 >= N) {
                cnt += isAte(ateSushi, sushi, i, N);
                cnt += isAte(ateSushi, sushi, 0, end - N);
            } else {
                cnt += isAte(ateSushi, sushi, i, end);
            }

            // 쿠폰 초밥 먹었는지?
            if (!ateSushi[C]) cnt++;
            result = Math.max(result, cnt);
        }

        System.out.println(result);
    }

    private static int isAte(boolean[] ateSushi, int[] sushi, int start, int end) {
        int cnt = 0;
        for (int j = start; j < end; j++) {
            if (ateSushi[sushi[j]]) continue;
            ateSushi[sushi[j]] = true;
            cnt++;
        }

        return cnt;
    }
}