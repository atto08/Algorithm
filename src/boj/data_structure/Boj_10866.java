package boj.data_structure;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj_10866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Deque<Integer> q = new LinkedList<>();
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            if (str.equals("push_front")) {
                int num = Integer.parseInt(st.nextToken());
                q.offerFirst(num);
            } else if (str.equals("push_back")) {
                int num = Integer.parseInt(st.nextToken());
                q.offerLast(num);
            } else if (str.equals("pop_front")) {
                if (q.isEmpty()) {
                    bw.write("-1\n");
                } else {
                    bw.write(q.pollFirst() + "\n");
                }
            } else if (str.equals("pop_back")) {
                if (q.isEmpty()) {
                    bw.write("-1\n");
                } else {
                    bw.write(q.pollLast() + "\n");
                }
            } else if (str.equals("size")) {
                bw.write(q.size() + "\n");
            } else if (str.equals("empty")) {
                if (q.isEmpty()) {
                    bw.write("1\n");
                } else {
                    bw.write("0\n");
                }
            } else if (str.equals("front")) {
                if (q.isEmpty()) {
                    bw.write("-1\n");
                } else {
                    bw.write(q.peekFirst() + "\n");
                }
            } else if (str.equals("back")) {
                if (q.isEmpty()) {
                    bw.write("-1\n");
                } else {
                    bw.write(q.peekLast() + "\n");
                }
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
