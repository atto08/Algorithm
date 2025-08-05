package boj.sort;

/*
 나무 자르기 - 실2
 소요시간 - 80분초과

 문제설명:
 절단한 나무들의 최소합 M이되는 높이 H의 최댓 값 구하기

 풀이설명:
 알고리즘 - 이분탐색 연습

 1차시도 - 시간초과(45%)
 깨달았다.
 아래 예제처럼 기준은 H로 이분탐색을 시도하면 됨.

 예제2)
 4 26 40 42 46 -> M = 20
 1) 0 ~ 46 & mid = 23
    3 + 17 + 19 + 23 -> 62 > M

 2) 24 ~ 46 & mid = 35
    5 + 7 + 11 --> 23 > M

 3) 36 ~ 46 & mid = 41
    1 + 5 => 6 < M

4) 36 ~ 40 & mid = 38
    2 + 4 + 8 --> 14 < M

5) 36 ~ 37 & mid = 36
    4 + 6 + 10 -> 20 = M

2차시도 - 틀렸습니다.
- H의 갱신의 조건이 틀렸음.
- cutM >= M일때는 계속 갱신해야하는데, cutM == M 일때 break를 걸어버림.
- 또한 cutM은 int타입을 넘어갈 수 있기 때문에 long으로 선언해줘야함.
*/

import java.util.*;
import java.io.*;

public class Boj_2805 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] heights = new int[N];
        for (int i = 0; i < N; i++) heights[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(heights);
        int H = binarySearch(heights, N, M);

        System.out.println(H);
    }

    private static int binarySearch(int[] heights, int N, int M) {

        int left = 0, right = heights[N - 1];
        int H = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            long cutM = 0;
            for (int i = N - 1; i >= 0; i--) {
                if (heights[i] > mid) {
                    cutM += heights[i] - mid;
                } else {
                    break;
                }
            }

            if (cutM >= M) {
                H = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return H;
    }
}
