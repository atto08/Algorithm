package boj.sort;

import java.util.*;

/*
베스트 셀러 - 실4
소요 시간: 24분

조건 1) 가장 많이 나온 단어를 출력
조건 2) 위 경우가 여러개 일때, 사전순으로 가장 앞 단어를 출력.

조건1을 우선순위 큐로 구현은 많이 해봤지만 사전순으로 정렬하는 경우를 몰랐음
>> GPT한테 물어보고 새로운 방법 습득.

 */
public class Boj_1302 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        HashMap<String, Integer> hashMap = new LinkedHashMap<>();
        for (int i = 0; i < N; i++) {
            String word = sc.next();
            if (hashMap.containsKey(word)) {
                hashMap.replace(word, hashMap.get(word) + 1);
                continue;
            }
            hashMap.put(word, 1);
        }

        PriorityQueue<Dictionary> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            pq.offer(new Dictionary(entry.getKey(), entry.getValue()));
        }

        int max = 0;
        String result = "";
        while (!pq.isEmpty()) {
            Dictionary now = pq.poll();

            if (max <= now.cnt) {
                result = now.word;
                max = now.cnt;
            } else {
                break;
            }
        }

        System.out.println(result);
    }

    static class Dictionary implements Comparable<Dictionary> {
        String word;
        int cnt;

        private Dictionary(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Dictionary other) {
            if (this.cnt == other.cnt) {
                return this.word.compareTo(other.word);
            }
            return Integer.compare(this.cnt, other.cnt);
        }
    }
}
