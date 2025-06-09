package boj.implementation;

import java.io.*;
import java.util.StringTokenizer;
/*
부분합 - 골4
소요시간 - 1시간 초과

풀이설명:
1차시도 - 메모리 초과
전체합을 구해놓고 좌우를 하나씩 줄여보는 형식으로 접근함.
- 좌우 줄이기 -> 큐로 구현
- 중복제거 -> HashSet 사용

*** 투 포인터 구현방식 ***
- start, end == 0 (start <= end)
- 매 루프마다 항상 두 포인터 중 하나는 1씩 증가.

p.s
수열은 최대 100,000개니까 당연한 결과였다 생각함..
투 포인터 알고리즘 구현방식을 모르고 접근했음.
너무 알고있는 지식내에서 구현하려한 죄다.

 */
public class Boj_1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        int start = 0, end = 0, sum = 0, result = Integer.MAX_VALUE;

        while (true) {
            if (sum >= S) { // 1) sum이 S 이상일 때, 윈도우를 줄여보기
                result = Math.min(result, end - start);
                sum -= arr[start++];
            }
            else if (end < N) { // 2) sum이 S 미만일 때, end를 늘려보기
                sum += arr[end++];
            }
            else {  // 3) end가 끝까지 갔고 sum < S인 경우 종료
                break;
            }
        }
        System.out.println(result == Integer.MAX_VALUE ? 0 : result);
    }
}
