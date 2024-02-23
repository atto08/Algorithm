package boj.data_structure;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
문제를 잘못 이해해서 블로그 풀이보고 문제를 파악
시간초과 발생
스택인 경우를 제외하고 큐인 경우만 숫자빼기 놀이를 해줌.
 */
public class Boj_24511 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        String[] dataStructure = br.readLine().split(" ");
        String[] qs = br.readLine().split(" ");

        Deque<Integer> queueStack = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (dataStructure[i].equals("0")) {
                queueStack.add(Integer.parseInt(qs[i]));
            }
        }

        int M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            queueStack.addFirst(num);

            bw.write(queueStack.pollLast() + " ");
        }
        bw.flush();
    }
}
