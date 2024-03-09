package boj.implementation;

import java.io.*;
import java.util.*;

/*
1) 런타임에러 (NullPointerException)
==> L & R 모두 0일때 덱의 크기가 2개를 뽑을 수 없을 경우를 고려하지 않음
size 크기가 2이상 일 때만 두 개 모두 뽑아 비교하는 형식으로 변경
 */
public class Boj_14719 {
    static int H, W, rain;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            int num = Integer.parseInt(st.nextToken());

            while (num > 0) {
                map[H - num][i] = 1;
                num--;
            }
        }

        bfs();

        System.out.println(rain);
    }

    private static void bfs() {
        Deque<Integer> deque = new ArrayDeque<>();

        int h = H - 1;
        while (h >= 0) {
            for (int num : map[h]) {
                deque.add(num);
            }

            int L = 0;
            int R = 0;
            while (!deque.isEmpty()) {
                if (L == 1 && R == 1) {
                    break;
                }

                if (L == 1 && R == 0) {
                    R = deque.pollLast();
                } else if (L == 0 && R == 1) {
                    L = deque.pollFirst();
                } else if (L == 0 && R == 0) {
                    L = deque.pollFirst();
                    if (deque.size() > 1){
                        R = deque.pollLast();
                    }
                }
            }

            if (deque.isEmpty()) {
                break;
            } else {
                while (!deque.isEmpty()) {
                    int num = deque.poll();
                    if (num == 0) {
                        rain++;
                    }
                }
            }
            h--;
        }
    }
}
