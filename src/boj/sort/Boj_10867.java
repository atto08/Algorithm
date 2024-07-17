package boj.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
중복빼고 정렬하기 - 실5
소요 시간: 8분
 */
public class Boj_10867 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        HashSet<Integer> visit = new HashSet<>();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (!visit.contains(num)) {
                visit.add(num);
                pq.offer(num);
            }
        }

        StringBuilder result = new StringBuilder();
        while (!pq.isEmpty()) result.append(pq.poll()).append(" ");

        System.out.println(result.toString().trim());
    }
}
