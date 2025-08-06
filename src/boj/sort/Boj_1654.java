package boj.sort;

/*
 랜선 자르기 - 실2
 소요시간 - 40분 초과

 문제설명:
 K개의 각기다른 길이의 랜선들로 N개 이상의 랜선을 만들 수 있는 최대길이 구하기

 풀이설명:
 - 이분탐색 풀이
 - 제일 큰 정렬된 lines[K - 1]이 가능한 제일 큰 수 & 1이 가능한 가장 작은 수
 - 중간 값을 구하면서 각 랜선을 중간값으로 나눠서 가능한 cntLine의 갯수를 비교하며 범위 줄여가기.

 1~3차시도
 1차 - (45% 틀렸습니다)
 2차 - (52% 틀렸습니다)
 3차 - (47% 시간초과)

 p.s
 - 결과적으로는 랜선의 길이 범위가 되는 left, right, mid, max와 cntLine의 타입을 Long으로 바꾸니까 됐다.
 - Y??
 👉 정수형 오버플로우로 인한 잘못된 mid 계산 → 탐색 범위 축소 실패 → 반복 증가 -> 시간 증가
 👉 long 변경 -> 정상적인 mid 계산 -> 탐색 범위 축소 -> 속도 정상

 - 너무 취약한 부분인걸 알고있지만, 제대로 공부하지않았기 때문에 발생한 문제라 생각함.
 - 이제 머리 굴리면서 도망다니지말고, 직면한 문제를 제대로 해결해서 성장하자.
*/

import java.util.*;
import java.io.*;

public class Boj_1654 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] lines = new int[K];
        for (int i = 0; i < K; i++) lines[i] = Integer.parseInt(br.readLine());

        Arrays.sort(lines);
        long left = 1, right = lines[K - 1];
        long max = 0;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long cntLine = 0;

            for (int line : lines) cntLine += line / mid;

            if (cntLine >= N) {
                max = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(max);
    }
}
