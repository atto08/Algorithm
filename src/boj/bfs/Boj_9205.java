package boj.bfs;

import java.util.*;
import java.io.*;

/*
맥주마시면서 걸어가기 - 골5
소요시간 - 90분

문제설명:
- 행복하게(맥주를 계속 마시면서) 페스티벌에 도착할 수 있는지 여부를 구하기.
- 1박스 = 맥주 20개 / 50미터당 맥주 1병소모
- 맥주는 편의점에서 리필 가능(최대 20개)
- 편의점에서 나온 직후에도 50미터 전에 맥주한병 소모해야함.

풀이설명:
- 헷갈리는 부분 존재.
	- 상근이네 집에서 출발할때, 20병에서 시작?
	- "" 아니면 20 - 1로 시작인가?
    --> 일단 50미터 도착했을때 한병씩 까는걸로.

- 1차시도 - 힙메모리 초과
- 2차원배열 만들어서 일일히 칸수마다 확인함. -> 맨해튼 거리(|x1 - x2| + |y1 - y2|) 이걸 놓쳤다.

2차시도 - gpt 어시스트
- 좌표가 음수가 포함된 경우에는 2차원배열을 사용하기 보다는 List, Map 등의 객체를 활용한 탐색 접근이 훨씬 효율적이다.(시간,메모리)

p.s
- 음수 좌표가 나올때 매번 음수 크기만큼 더한 크기로 접근을 하고 결국엔 해결 못한 문제가 많았다.
- 하지만 드디어 오늘 새로운 방법도 알게됐다.
- 고민되거나 헷갈리는 문구가 많아서 생각하는 시간이 좀 많았는데, 단순하게 생각하는 경우가 대부분 맞는경우 같다.
- Simple is Best.!!!
*/
public class Boj_9205 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            // 상근집 1 -> 편의점(N개) -> 펜타포트 1
            List<Point> points = new ArrayList<>();
            StringTokenizer st;
            for (int i = 0; i < N + 2; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                points.add(new Point(x, y));
            }

            bfs(points);
        }
    }

    private static void bfs(List<Point> points) {
        boolean[] visited = new boolean[points.size()];
        Queue<Integer> q = new LinkedList<>();
        visited[0] = true;
        q.offer(0);

        while (!q.isEmpty()) {
            int now = q.poll();
            Point p1 = points.get(now);

            for (int i = 0; i < points.size(); i++) {
                if (visited[i]) continue;

                Point p2 = points.get(i);
                int dist = Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);

                if (dist <= 1000) {
                    visited[i] = true;
                    q.offer(i);
                }
            }
        }

        if (visited[points.size() - 1]) {
            System.out.println("happy");
        } else {
            System.out.println("sad");
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
