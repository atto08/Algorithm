package boj.greedy;

/*
ATM - 실4
소요시간 - 12분

풀이설명:
- 우선순위 큐로 소요되는 시간 p가 적은 순서대로 인출하기.
- min은 누적되는 소요시간 / sum은 회차별 min의 합 = 최종 시간.
 */
import java.io.*;
import java.util.*;

public class Boj_11399 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 사람번호 1 ~ N
        // p1 ~ pn은 돈을 인출하는데 소요되는 시간(분)
        PriorityQueue<Bank> q = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            int p = Integer.parseInt(st.nextToken());
            q.offer(new Bank(i, p));
        }

        int sum = 0;
        int min = 0;
        while (!q.isEmpty()) {
            Bank now = q.poll();
            min += now.p;
            sum += min;
        }
        System.out.println(sum);
    }

    static class Bank implements Comparable<Bank> {
        int i, p;

        public Bank(int i, int p) {
            this.i = i;
            this.p = p;
        }

        @Override
        public int compareTo(Bank other) {
            return Integer.compare(this.p, other.p);
        }
    }
}