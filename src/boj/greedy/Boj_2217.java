package boj.greedy;

import java.io.*;
import java.util.*;

/*
로프 - 실4
소요 시간 - 25분

[문제설명]
- N개의 로프 존재
- 여러 개의 로프를 병렬로 연결하여 각각의 로프에 걸리는 중량을 나눌 수 있음
- k개의 로프 사용하여 중량 w인 물체를 들어올릴 때, 각각의 로프에는 모두 고르게 w/k만큼의 중량이 걸리게 됨
- 각 로프들이 들어올릴 수 있는 무게가 주어졌을때, 최대 들어올릴 수 있는 최대 중량을 구하기
- ** 모든 로프를 사용할 필요는 없다. 임의로 몇개 만 골라서 사용 가능

[풀이설명]
- N개의 로프 정보를 배열로 갖고 오름 차순으로 정렬하기
- 작은 수 부터 차례대로 위로 있는 숫자 갯수 만큼의 배수를 계산하며 최댓값 갱신

p.s
- 올해는 나의 것
- 꾸준하게 머리만 사용해보자.
- 최대한 자바의 장점을 살려보자 ~
 */
public class Boj_2217 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);

        int max = arr[N - 1];
        for (int i = 0; i < N; i++) {
            // 가장 큰 경우만 구하면 됨
            // 그렇기 때문에, i ~ (N-i)경우를 다 볼 필요가 없고 가장 큰 아래 경우만 체크하면 됨
            // 시간 4.6s -> 256ms
            // 메모리 110MB -> 26MB
            int num = arr[i] * (N - i);
            max = Math.max(max, num);
        }
        System.out.println(max);
    }
}
