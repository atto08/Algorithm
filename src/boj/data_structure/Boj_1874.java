package boj.data_structure;

import java.io.*;
import java.util.Stack;
/*
스택 수열 - 실2
소요시간 - 37분

[문제설명]
- 1~N의 숫자를 순차적으로 쌓고(push) 뽑으며(pop) 주어진 수열(sequence)순으로 출력 가능여부 확인하기
- 가능하면 push & pop 과정을 출력
- 불가능하면 "NO" 출력


[풀이설명]
- 1~N의 숫자를 순차적으로 push
    - 스택에 가장 최신으로 쌓여있는 숫자가 목표(goal[i]) 숫자이면 아닐때 까지 pop
    - i(goal[] 인덱스)는 N이되면 전부 빠진거임
- for문 종료 후 스택에 숫자가 남아있다면, 수열은 생성 불가한 것으로 확정

p.s
- 어제 풀다가 집중못하고 못 푼 문제.
- 풀이를 정리하기 전에, 문제를 내가 이해했는지 손으로 이해한내용대로 적어보는 것이 중요한걸 다시 느낌.
- 오늘 문제설명은 내가 손으로 정리해놓은걸 깔끔하게 중복되는 말 제거해서 정리한 것.
- 앞으로도 문제를 나의 언어로 정리 -> 풀이방법 고민 순으로 제대로 하자.
- 문제 나의 언어로 정리하는 과정이 번거롭다고 자꾸 건너뛰는 나쁜습관이 다시 생기는데, 정신차려라.
- 좋은 코드를 작성할 수 있는 지름길은 제대로 이해했는지를 점검하는 것 부터다.
 */
public class Boj_1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] goal = new int[N];
        for (int i = 0; i < N; i++) goal[i] = Integer.parseInt(br.readLine());

        int idx = 0;
        Stack<Integer> stack = new Stack<>();
        for (int num = 1; num <= N; num++) {

            stack.push(num);
            result.append("+\n");

            while (goal[idx] == stack.peek()) {
                stack.pop();
                idx++;
                result.append(("-\n"));
                if (idx == N || stack.isEmpty()) break;
            }
        }
        if (!stack.isEmpty()) result = new StringBuilder("NO");

        System.out.println(result);
    }
}
