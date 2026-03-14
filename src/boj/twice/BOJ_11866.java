package boj.twice;

import java.io.*;
import java.util.*;
/*
요세푸스 문제 0 - 실4
소요시간 - 19분

[풀이설명]
- 1~N의 숫자가 순차적으로 원형으로 앉아있음. -> 큐에 오름차순으로 들어감
- 숫자 하나씩 K번째에 존재하는 숫자를 추출 -> 큐가 빌때까지 K번째의 숫자를 제거 및 출력

p.s
- 출력 조건 <~, ~ , ~> 에 당황함.
    -> StringBuilder 클래스 내부를 살펴 delete와 deleteCharAt 메서드를 확인하고 인자 값에 맞춰 사용함.

- 문제는 큐의 FIFO 특성을 알고 활용가능한지 보는 문제로 파악됨.
- 그치만 N이나 K의 범위가 조건인 1000이하 보다 높은 숫자로 증가하면 시간복잡도가 증가할 것으로 예상됨.
- 더 큰 숫자가 주어진다면 트리구조를 활용하는 것이 방안
 */
public class BOJ_11866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        for (int n = 1; n <= N; n++) {
            q.offer(n);
        }

        StringBuilder result = new StringBuilder();
        result.append("<");
        while (!q.isEmpty()) {
            int k = K;

            while (k > 1) {
                q.offer(q.poll());
                k--;
            }
            result.append(q.poll()).append(',').append(' ');
        }
        result.delete(result.length() - 2, result.length());
        result.append(">");

        System.out.println(result);
    }
}