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
-

ex)
머리 - 캡,비니,선글라스
몸 - 패딩,자켓
신발 - 부츠,구두,운동화
3+2+3 = 8
6 + 6 + 9 = 21
3*2*3 = 18

 */

public class Boj_9375 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            Map<String, Integer> cntByType = new HashMap<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                st.nextToken(); // item name (unused)
                String type = st.nextToken();
                cntByType.put(type, cntByType.getOrDefault(type, 0) + 1);
            }

            long ans = 1;
            for (int c : cntByType.values()) {
                ans *= (c + 1); // wear one of them OR wear none
            }
            ans -= 1; // exclude "wear nothing"

            sb.append(ans).append('\n');
        }

        System.out.print(sb);
    }
}