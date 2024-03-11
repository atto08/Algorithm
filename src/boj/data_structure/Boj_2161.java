package boj.data_structure;

import java.io.*;
import java.util.*;

public class Boj_2161 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            queue.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        int count = 1;
        while (!queue.isEmpty()) {
            if (count % 2 != 0) {
                sb.append(queue.poll()).append(" ");
            } else {
                queue.offer(queue.poll());
            }
            count++;
        }

        System.out.println(sb.toString().trim());
    }
}
