package boj.str;

import java.util.PriorityQueue;
import java.util.Scanner;

/*
생일 - 실5
소요 시간 - 22분

우선순위 큐로 년, 월, 일을 비교 -> 간단 구현
 */
public class Boj_5635 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        PriorityQueue<Birth> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            String name = sc.next();
            int day = sc.nextInt();
            int month = sc.nextInt();
            int year = sc.nextInt();
            pq.offer(new Birth(name, day, month, year));
        }

        System.out.println(pq.poll().name);
        while (true) {
            if (pq.size() == 1) {
                System.out.println(pq.poll().name);
                break;
            }
            pq.poll();
        }
    }

    static class Birth implements Comparable<Birth> {
        int d, m, y;
        String name;

        private Birth(String name, int d, int m, int y) {
            this.d = d;
            this.m = m;
            this.y = y;
            this.name = name;
        }

        public int compareTo(Birth other) {
            if (this.y == other.y) {
                if (this.m == other.m) {
                    return Integer.compare(other.d, this.d);
                }
                return Integer.compare(other.m, this.m);
            }
            return Integer.compare(other.y, this.y);
        }
    }
}
