package boj.data_structure;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

/*
가운데를 말해요 - 골2
소요 시간 - 1시간 초과

문제 설명:
- 백준이 N번 정수를 외침 & 동생은 백준이 말한 N개의 수 중에서 중간 값을 새로운 정수가 들어올 때 마다 외쳐야함.
- N번 동안 N개의 숫자가 추가
 */
public class Boj_1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // 중앙값을 유지하기 위한 두 개의 힙
        PriorityQueue<Integer> leftHeap = new PriorityQueue<>(Collections.reverseOrder()); // 최대 힙
        PriorityQueue<Integer> rightHeap = new PriorityQueue<>(); // 최소 힙

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            if (leftHeap.isEmpty() || num <= leftHeap.peek()) {
                leftHeap.add(num);
            } else {
                rightHeap.add(num);
            }

            // 균형 맞추기 (leftHeap의 크기가 항상 크거나 같아야 함)
            if (leftHeap.size() > rightHeap.size() + 1) {
                rightHeap.add(leftHeap.poll());
            } else if (rightHeap.size() > leftHeap.size()) {
                leftHeap.add(rightHeap.poll());
            }

            // 중앙값 출력
            bw.write(leftHeap.peek() + "\n");
        }
        bw.flush();
    }
}
