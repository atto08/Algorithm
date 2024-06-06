package boj.data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/*
탑 - 골5
소요 시간: 33분

1) 메모리 초과(3%)
원인: stack을 두번 사용해서 그런거 같음
해결: 탑의 높이를 입력받는 즉시 앞에 있는 탑의 높이를 확인하는 형식으로 전환
(질의응답 창 & gpt 참고)

소요 시간: +15분
2) 통과
 */
public class Boj_2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] heights = new int[N];
        int[] answer = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                answer[i] = stack.peek() + 1;
            } else {
                answer[i] = 0;
            }

            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}
