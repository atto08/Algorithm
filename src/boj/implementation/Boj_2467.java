package boj.implementation;

import java.io.*;
import java.util.StringTokenizer;

/*
 용액 - 골5
 소요시간 - 60분

 풀이설명:
 - 알칼리성(양수) 용액과 산성(음수) 용액 존재.
 - 두 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만드는 경우의 용액들을 출력.
 - 같은 종류의 두 용액끼리가 있을수도 있음 -> 구분해서 놓을 필요 X

 1차시도 - 실패
 - 범위를 줄여오는 조건(start++, end-- 하는)의 기준을 이전에 나온 합으로 두었음.
 - -> 이게 큰 문제였다 생각함.
 - 문제에서 요구하는 것 처럼 0과 가까워지는 조건문을 작성했어야함.

 p.s
 - 투포인터의 구현 방식만 배웠을뿐. 정작 조건을 어떤 방식으로 걸어야하는지에 대한 문제 파악이 부족하다.
 - 더 많이 풀어보는 수 밖에. 생각을 하면서. 성장하도록. 무럭무럭.
 */
public class Boj_2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int start = 0, end = N - 1, dis = Integer.MAX_VALUE, w1 = 0, w2 = 0;
        while (start != end) {
            int sum = arr[start] + arr[end];
            int abs = Math.abs(sum);

            if (abs < dis) {
                dis = abs;
                w1 = arr[start];
                w2 = arr[end];
            }

            if (sum < 0) {
                start++;
            } else {
                end--;
            }
        }
        System.out.println(w1 + " " + w2);
    }
}
