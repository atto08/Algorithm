package boj.data_structure;

import java.io.*;
import java.util.*;

/*
최솟값 찾기 - 플5
소요 시간: 30분

1) 시간초과(3%)
원인: N * (i-L+1 ~ i) 의 시간동안 우선순위큐 생성 및 데이터 삽입 삭제 과정에 시간복잡도 증가 예상
해결: 덱을 사용한 슬라이딩 윈도우 기법 사용 - 출처 gpt
- 동작 순서
현재 인덱스가 윈도우 범위를 벗어난 경우, 덱의 앞부분에서 제거
현재 값보다 큰 값을 덱의 뒷부분에서 제거하여 덱의 앞부분에 항상 최솟값이 위치하도록 유지
덱에 현재 인덱스를 추가
결과 문자열에 덱의 앞부분에 위치한 값을 추가

소요 시간: +26분
2) 통과
 */
public class Boj_11003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] D = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            D[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder result = new StringBuilder();
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < N; i++) {

            if (!deque.isEmpty() && deque.peekFirst() <= i - L) { // 현재 인덱스가 윈도우 크기 범위를 벗어난 경우 제거
                deque.pollFirst();
            }

            while (!deque.isEmpty() && D[deque.peekLast()] > D[i]) { // 현재 값보다 큰 값들은 모두 제거 (덱의 뒷부분에서부터)
                deque.pollLast();
            }

            deque.offerLast(i); // 현재 인덱스를 덱에 추가

            result.append(D[deque.peekFirst()]).append(" "); // 결과에 현재 윈도우의 최솟값 추가
        }

        System.out.println(result.toString().trim());
    }
}
