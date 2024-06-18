package boj.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
N번째 큰 수 - 실2
소요 시간: 6분

해결 방법: 모든 수를 우선순위 큐에 넣고 큰 순서대로 뽑다가 N번 째에 뽑히는 수에서 멈추면 됨
 */
public class Boj_2075 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                pq.offer(Integer.parseInt(st.nextToken()));
            }
        }

        int cnt = 0;
        int result = 0;
        while (cnt < N) {
            result = pq.poll();
            cnt++;
        }
        System.out.println(result);
    }
}
