package boj.greedy;

import java.util.*;
import java.io.*;

/*
한 줄로 서기 - 실2
소요시간 - 약 40분

[풀이설명]
- 자료구조 덱을 사용(2개)
    - 기준 인원보다 앞선 경우 left
    - 뒤에 있는 경우 right
- N -> 1 키 순서로 탐색
    --> 무조건 i 기준 (i+1) ~ N  즉, i보다 키큰 인원들만 존재하는 상황
- 앞선 인원들(left) == arr[i]이 될 때까지 탐색
    - 맞는다면 진행
    - 인원이 적으면 뒤에서 앞으로 데려오기
    - 인원이 많으면 앞에서 뒤로 보내기
p.s
- 짦은 시간에 집중력이 저하됐다.
- 그래도 예전에 사용한 자료구조로 이런느낌이 있던게 기억나서 자료구조 선택은 1~20분안에 한 거 같다.
- N -> 1 순서로 가면 무조건 큰 수 만 있으니까 이렇게 해도되는데 막상 예제를 풀어 써보기 전까지는 긴가민가 했다.
 */
public class Boj_1138 {

    static int N;
    static Deque<Integer> left, right;

    private static void takeLine(int num, int[] arr) {

        if (arr[num] != left.size()) {
            while (left.size() != arr[num]) {
                if (left.size() > arr[num]) {
                    right.addFirst(left.pollLast());
                } else {
                    left.addLast(right.pollFirst());
                }
            }
        }
        left.add(num);
    }

    private static void printResult() {
        StringBuilder result = new StringBuilder();
        while (!left.isEmpty()) {
            result.append(left.pollFirst()).append(" ");
        }
        while (!right.isEmpty()) {
            result.append(right.pollFirst()).append(" ");
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());

        left = new ArrayDeque<>();
        right = new ArrayDeque<>();
        left.add(N);
        for (int i = N - 1; i > 0; i--) takeLine(i, arr);

        printResult();
    }
}
