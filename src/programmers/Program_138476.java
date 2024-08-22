package programmers;

import java.util.HashMap;
import java.util.PriorityQueue;
/*
귤 고르기 - level2
소요 시간: 40분

map의 key 값을 가져오는 메소드를 까먹었음
keySet() 기억하기.
 */
public class Program_138476 {
    public int solution(int k, int[] tangerine) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int key : tangerine) {
            if (!map.containsKey(key)) {
                map.put(key, 1);
            } else {
                map.replace(key, map.get(key) + 1);
            }
        }

        PriorityQueue<Tan> pq = new PriorityQueue<>();
        for (Integer key : map.keySet()) {
            int val = map.get(key);
            pq.offer(new Tan(key, val));
        }

        int result = 0, cnt = k;
        while (cnt > 0) {
            cnt -= pq.poll().c;
            result++;
        }

        return result;
    }

    private static class Tan implements Comparable<Tan> {
        int k, c;

        private Tan(int k, int c) {
            this.k = k;
            this.c = c;
        }

        @Override
        public int compareTo(Tan other) {
            if (other.c == this.c)
                return Integer.compare(other.k, this.k);

            return Integer.compare(other.c, this.c);
        }
    }
}
