package programmers.lv3;
/*
디스크 컨트롤러 - lv3
소요시간 - 1시간 초과

풀이설명:
- 작업 별(수행완료시간 - 요청시간)의 평균 값 리턴
- 한번에 하나의 작업만 수행가능
- 작업번호, 작업의 요청시각, 작업 소요시간
- 작업 우선순위
    - 작업의 소요시간이 짧은거(l) > 작업의 요청시각이 빠른거(s) > 작업의 번호가 작은 순서(i)

1차시도 - TC(1~19 실패)
- 모든걸 넣어놓고 시작 -> 최소 소요시간 경우? -> 문제의 요구와 다르다.
- 그렇기때문에 작업번호 순서대로 확인하면서 넣기
    - 첫번째 작업은 넣고 시작.
    - 작업의 시작(s)시간이 현재 시간보다 낮은경우까지만 췤.
    -

2차시도 - TC(19-시간초과, 20-실패)
- 젤 처음 시작하는 시작시간을 갱신하지 않음 -> 대기큐에 아무것도 없는데 비어있는 시간대가 존재할때, 무한루프에 빠져버림

p.s
- 우선순위 정할때도 그렇고, while문에 조건문도 그렇고 참/거짓 기준이 헷갈릴때가 생겼다.
- 개념이 흔들려서 그런가 싶었는데, 그럴수도 있지만, 헷갈릴수도 있다고 생각한다.
    - 그래도 나름 빨리 이해하고 조건은 세웠다.
- 하지만, 엣지케이스를 찾아냈음에도 코드개선이 부족했다.
- 객체 활용도가 아쉬웠다. 너무 쓸데없이 여러개 만들었다는 느낌이랄까.
    - 우선순위 큐에서는 Comparator의 메서드를 활용해서 람다식을 작성해보자요.
즉, 간결하게, 깔끔하게, 논리정연하게 작성하자요~.
*/

import java.util.Comparator;
import java.util.PriorityQueue;

public class Program_42627 {
    public static int solution(int[][] jobs) {
        PriorityQueue<DiskCtrl> pq = new PriorityQueue<>(Comparator
                .comparingInt((DiskCtrl d) -> d.s)
                .thenComparingInt(d -> d.i));

        PriorityQueue<DiskCtrl> dc = new PriorityQueue<>();
        for (int i = 0; i < jobs.length; i++) {
            pq.offer(new DiskCtrl(i, jobs[i][0], jobs[i][1]));
        }

        int result = 0;
        int currentTime = 0;
        while (!pq.isEmpty() || !dc.isEmpty()) {
            while (!pq.isEmpty() && pq.peek().s <= currentTime) {
                dc.offer(pq.poll());
            }

            if (!dc.isEmpty()) {
                DiskCtrl now = dc.poll();
                if (currentTime < now.s) currentTime = now.s;
                currentTime += now.l;
                result += currentTime - now.s;
            } else {
                currentTime = pq.peek().s; // 작업이 하나도 없으면 시간 점프
            }
        }

        return result / jobs.length;
    }

    static class DiskCtrl implements Comparable<DiskCtrl> {
        int i, s, l;

        public DiskCtrl(int i, int s, int l) {
            this.i = i;
            this.s = s;
            this.l = l;
        }

        @Override
        public int compareTo(DiskCtrl other) {
            if (this.l != other.l) return Integer.compare(this.l, other.l);
            if (this.s != other.s) return Integer.compare(this.s, other.s);
            return Integer.compare(this.i, other.i);
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{0, 3}, {1, 9}, {2, 6}}));      // 9
        System.out.println(solution(new int[][]{{1, 4}, {7, 9}, {1000, 3}}));   // 5
    }
}
