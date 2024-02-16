package boj.data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
뒤에서 빼고 뒤에다 넣고있었다(offer 만 사용). -- 메소드 잘 살펴보자..
 */
public class Boj_1021 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            deque.offer(i);
        }

        int count = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int number = Integer.parseInt(st.nextToken());
            int size = deque.size() / 2;
            int idx = deque.indexOf(number);

            if (idx > size) {
                for (int j = deque.size(); j > idx; j--) {
                    deque.addFirst(deque.pollLast());
                    count++;
                }
            } else {
                for (int j = 0; j < idx; j++) {
                    deque.addLast(deque.pollFirst());
                    count++;
                }
            }
            deque.poll();
        }

        System.out.println(count);
        br.close();
    }
}
