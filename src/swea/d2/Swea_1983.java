package swea.d2;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
조교의 성적 매기기 - D2
소요 시간: 64분
문제: 우선순위 큐를 구현하면서 compareTo 메소드의 리턴 값을 double로 던져야 하는데 오버라이드 하는 메소드는 리턴 값이 int 였음
해결 >> Double.compare 메소드를 이용해 정수형 값으로 리턴 값 지정하는법을 배움
 */
public class Swea_1983 {
    static String[] grades = {"", "A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int limitNum = N / 10;

            PriorityQueue<Rank> pq = new PriorityQueue<>();
            for (int r = 1; r <= N; r++) {
                st = new StringTokenizer(br.readLine());
                double mScore = Integer.parseInt(st.nextToken()) * 0.35;
                double fScore = Integer.parseInt(st.nextToken()) * 0.45;
                double aScore = Integer.parseInt(st.nextToken()) * 0.2;

                pq.offer(new Rank(r, mScore + fScore + aScore));
            }

            int x = 1, y = 0;
            while (!pq.isEmpty()) {
                Rank r = pq.poll();

                if (y < limitNum) {
                    y++;
                } else {
                    y = 1;
                    x++;
                }

                if (r.number == K) {
                    bw.write("#" + test_case + " " + grades[x] + "\n");
                    break;
                }
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }

    static class Rank implements Comparable<Rank> {
        int number;
        double score;

        private Rank(int number, double score) {
            this.number = number;
            this.score = score;
        }

        @Override
        public int compareTo(Rank other) {
            return Double.compare(other.score, this.score);
        }
    }
}
