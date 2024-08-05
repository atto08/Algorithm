package programmers;

import java.util.*;

/*
여행 경로 - Level3
소요 시간: 1시간 초과

1) + 27분 (75점)
원인: 경로가 여러 개 존재할 때, 사전 순으로 가장 앞인 경로를 지정하지 않았음.

2) + 1시간 (")
원인: 시작 경로 설정을 덱에 ICN만 넣고 뒤에꺼를 갖고 있는 모든 항공권을 탐색했어야 했는데 ICN을 갖고 있는 항공권만 넣고 탐색함.
+ 테스트 케이스 1이 순서대로 전부 찾는가 에 대한 문제였음...
해결: 시작에 ICN만 넣어 놓고 경로 탐색을 하도록 함..

시작을 너무 어렵게 봤다. 중복된 항공권이 있을 수도 있었고, 모든 방문 체크를 해서 경우의 수를 체크해 보았을 때 비로소 비는 경우의 수가 있다는 걸 알게 됨..
 */
public class TravelRoute {

    static boolean[] visited;
    static String[][] ticketList;
    static List<String> routeList;

    public String[] solution(String[][] tickets) {
        ticketList = tickets;
        visited = new boolean[tickets.length];
        routeList = new ArrayList<>();

        // 티켓을 출발지 기준으로 정렬 (GPT한테 질문)
        Arrays.sort(ticketList, (a, b) -> {
            // a.compareTo(b); >> 문자열 a와 b를 비교하여 사전순으로 a가 b보다 앞서면 음수를 , 같다면 0, 뒤에 있으면 양수를 반환
            if (a[0].equals(b[0])) {            // 출발지가 동일한 경우
                return a[1].compareTo(b[1]);    // 도착지를 기준으로 정렬
            } else {                                // 출발지가 동일하지 않은 경우
                return a[0].compareTo(b[0]);        // 출발지를 기준으로 정렬
            }
        });

        Deque<String> spots = new ArrayDeque<>();
        spots.offer("ICN");
        travel("ICN", 0, spots);

        // 결과를 배열로 변환
        String[] route = new String[routeList.size()];
        return routeList.toArray(route);
    }

    private static boolean travel(String current, int depth, Deque<String> spots) {
        if (depth == ticketList.length) {
            routeList.addAll(new ArrayList<>(spots));
            return true;
        }

        for (int i = 0; i < ticketList.length; i++) {
            if (!visited[i] && ticketList[i][0].equals(current)) {
                visited[i] = true;
                spots.offer(ticketList[i][1]);

                if (travel(ticketList[i][1], depth + 1, spots)) return true;

                visited[i] = false;
                spots.pollLast();
            }
        }
        return false;
    }

    public static void main(String[] args) {

        TravelRoute travelRoute = new TravelRoute();
        // 순서를 지키는지 에 대한 예제 List
        String[][] flights1 = {{"ICN", "AAA"}, {"AAA", "ICN"}, {"ICN", "CCC"}, {"CCC", "ICN"}, {"ICN", "DDD"}, {"DDD", "AAA"}};
        String[][] flights2 = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        String[][] flights3 = {{"ICN", "A"}, {"A", "B"}, {"A", "C"}, {"B", "A"}, {"C", "A"}};
        System.out.println("Finish result : " + Arrays.toString(travelRoute.solution(flights1)));
        System.out.println("Finish result : " + Arrays.toString(travelRoute.solution(flights2)));
        System.out.println("Finish result : " + Arrays.toString(travelRoute.solution(flights3)));
    }
}
