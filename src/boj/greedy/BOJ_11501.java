package boj.greedy;

import java.io.*;
import java.util.*;

/*
주식 - 실2
소요시간 - 1시간 초과

[문제설명]
- 최대 이익 구하기

매일 아래 중 1개 수행
- 주식 구매(1개만 가능)
- 주식 판매(수 제약X)
- 아무것도 하지않음(구매, 판매 X)

[풀이설명]

- arr[] : 일별 주식 값
- pq : 일별 주식 값을 갖고 있는 내림차순 우선순위 큐

1차시도(4% 틀렸습니다)
- 우선순위 큐에 일별 주식값을 모두 넣어 arr[]를 순방향으로 탐색하며,
    - pq.peek() 값과 같으면(최대 판매 가능 값) pq.poll()
    - " 다르면 sum += pq.peek() - arr[i];

반례
ex) 4 5 2
- 1일차 주식 구매 sum += max - arr[i] (5 - 4)
- 2일차 주식 판매 pq.poll = 5. pq 중 max = 4로 갱신
- 3일차 주식 구매 sum += max - arr[i] (4 - 2)
-> 3이 출력됨. 하지만, 조건에 따르면 정상 출력은 1이 답이다.

또한, N의 최댓값은 100만이다. 즉, T * N 만큼 우선순위 큐에 삽입 or 제거 하는 동작은 시간초과를 발생시킬 수 있다.

2차시도
- arr[N-1] 값을 max로 지정하고, 역방향으로 진행하며
    - max < arr[i] --> max 갱신
    - max > arr[i] --> sum += max - arr[i]

[회고]
- 처음에 최대값을 찾을 수 있는 최선의 방식을 확립하지 못했다고 생각한다.
- 주어진 조건에서 우선순위 큐가 초래할 문제에 대해서도 완벽히 인지하지 못했고, 1차시도 코드의 반례도 빠르게 못찾았다.
- 순방향 역방향 탐색 중 어떤게 더 효율적인 판단이였을지 생각했다면 더 빨리 풀 수 있었을 것 같다.
 */
public class BOJ_11501 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            long sum = 0;
            int max = arr[N - 1];
            for (int i = N - 2; i >= 0; i--) {
                if (max > arr[i]) {
                    sum += max - arr[i];
                } else if (max < arr[i]) {
                    max = arr[i];
                }
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }
}
