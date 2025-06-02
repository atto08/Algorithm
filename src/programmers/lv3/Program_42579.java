package programmers.lv3;

/*
베스트앨범 - lv3
소요시간 - 60분

풀이설명:
1. 장르 별로 / 2. 가장 많이 재생된 노래를 2개씩 모아 / 3. 베스트 앨범을 출시

우선순위 순서
- 속한 노래가 많이 재생된 장르 먼저 수록
- 장르 내에서 많이 재생된 노래 먼저 수록
- 장르 내에서 재생횟수 같으면 고유번호가 낮은(오름차순)
*/

import java.util.*;

public class Program_42579 {
    public static int[] solution(String[] genres, int[] plays) {

        HashMap<String, ArrayList<Song>> map = new HashMap<>(); // 장르별 리스트.
        HashMap<String, Integer> sum = new HashMap<>();         // 총합 카운트.
        for (int i = 0; i < plays.length; i++) {
            String genre = genres[i];
            int play = plays[i];

            // 장르별 총 재생횟수
            if (!sum.containsKey(genre)) {
                sum.put(genre, play);
            } else {
                int playCnt = sum.get(genre) + play;
                sum.replace(genre, playCnt);
            }

            // 장르별 재생횟수 리스트
            if (!map.containsKey(genre)) map.put(genre, new ArrayList<>());
            map.get(genre).add(new Song(play, i));
        }

        // 총 재생횟수가 많은 장르부터
        PriorityQueue<PlayCnt> pq = new PriorityQueue<>();
        for (String name : sum.keySet()) pq.offer(new PlayCnt(name, sum.get(name)));

        Queue<Integer> q = new LinkedList<>();
        // 총 재생 횟수가 높은 순서대로
        while (!pq.isEmpty()) {
            String genre = pq.poll().name;

            Collections.sort(map.get(genre)); // 내림차순 정렬
            int cnt = 0;
            for (Song s : map.get(genre)) {  // 장르별로 2개씩 넣기
                if (cnt >= 2) break;         // 2개 넘어가면 다음 장르 확인
                q.offer(s.i);
                cnt++;
            }
        }

        int L = q.size();
        int[] result = new int[L];
        for (int i = 0; i < L; i++) result[i] = q.poll();

        return result;
    }

    static class Song implements Comparable<Song> {
        int p, i;

        public Song(int p, int i) {
            this.p = p;
            this.i = i;
        }

        @Override
        public int compareTo(Song other) {
            if (this.p == other.p) return Integer.compare(this.i, other.i);
            return Integer.compare(other.p, this.p);
        }
    }

    static class PlayCnt implements Comparable<PlayCnt> {
        String name;
        int playCnt;

        public PlayCnt(String name, int playCnt) {
            this.name = name;
            this.playCnt = playCnt;
        }

        @Override
        public int compareTo(PlayCnt other) {
            return Integer.compare(other.playCnt, this.playCnt);
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 150, 800, 2500})));
    }
}