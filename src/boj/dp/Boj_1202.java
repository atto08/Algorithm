package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
보석 도둑 - 골2
소요 시간: 27분

1) 틀렸습니다. (3%)
반례:
ex)
3 3
20 12
0 20
16 16
17
14
7

res > 20
ans > 36 (17 <- 16짜리 & 14 <- 20짜리)

소요 시간: +10분
2) 시간 초과 (3%)
원인: 가치 순서대로 나오게 하는게 아니라 무게 순서대로 해야됨
해결:
compare 메소드의 우선순위 비교대상을 가치에서 무게로 변경하고 백팩을 배열로 변경후 정렬함

+ 새로 배운 코드작성 방법을 통해 위에서 시도한 아래 문제를 단번에 해결할 수 있게됨.(GPT)
1. 주얼리를 모두 검사했다가 백팩에 넣은 주얼리가 아니면 다시 집어 넣는 작업을 시도함
2. 1작업은 시간초과를 야기함 넣었다가 빠진 주얼리를 다시 우선순위 큐에 넣는 작업으로 인해 시간이 증가하기 때문
 */

public class Boj_1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Jewels> jewels = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            jewels.offer(new Jewels(m, v));
        }

        int[] backPack = new int[K];
        for (int i = 0; i < K; i++) {
            backPack[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(backPack); // 가방을 무게 순으로 정렬

        PriorityQueue<Integer> availableJewels = new PriorityQueue<>(Collections.reverseOrder());
        long maxValues = 0;

        for (int i = 0; i < K; i++) {
            int C = backPack[i];

            while (!jewels.isEmpty() && jewels.peek().m <= C) { // 현재 가방에 넣을 수 있는 보석 모두 추가 >> 이런식으로 사용 가능한지 몰랐음 새로 학습한 부분
                availableJewels.offer(jewels.poll().v);
            }

            if (!availableJewels.isEmpty()) { // 가장 가치가 큰 보석을 현재 가방에 넣음
                maxValues += availableJewels.poll();
            }
        }

        System.out.println(maxValues);
    }

    static class Jewels implements Comparable<Jewels> {
        int m, v;

        private Jewels(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Jewels other) {
            return Integer.compare(this.m, other.m);
        }
    }
}
