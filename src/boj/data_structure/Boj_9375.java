package boj.data_structure;

import java.io.*;
import java.util.*;
/*
패션왕 신해빈 - 실3
소요시간 - 시간초과

[문제설명]
- 옷 종류에 따라서 조합가능한 입을 수 있는 조합의 수 구하기
- (최소 한벌 입어야함)

[풀이설명]
- 해시맵 자료구조를 사용해 부위별로 갯수 갖기

p.s
- 프로그래머스 - 의상(lv2)와 같은 문제
- 8개월전에는 8분안에 풀었지만, 오늘 풀지못한 이유는 무엇일까.
- 조합만들기 집착해버렸다.
- 경우의 수만 구하면 되는 문제였음..
- 문제의 의도를 제대로 파악해야 빠르고 효율적인 접근이 가능하다.
- 열심히 연습해왔으니, 집중해서 판별할 수 있는 연습을 많이하자.

 */

public class Boj_9375 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            Map<String, Integer> cntByType = new HashMap<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                st.nextToken(); //
                String type = st.nextToken();
                cntByType.put(type, cntByType.getOrDefault(type, 0) + 1);
            }

            long ans = 1;
            for (int c : cntByType.values()) {
                ans *= (c + 1);
            }
            ans -= 1;

            result.append(ans).append('\n');
        }

        System.out.print(result);
    }
}