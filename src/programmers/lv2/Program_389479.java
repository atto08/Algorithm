package programmers.lv2;

/*
서버 증설 횟수 - lv2
소요시간 - 45분

문제설명:
- 하루동안 서버를 최소 몇번 증설해야하는지?
- 이용인원 m명증가 -> 서버 1대추가 필요
ex) n * m <= (n + 1) * m --> n대의 증설된 서버 운영 중 + 한번 증설된 서버는 k시간동안 운영 후 반납

풀이설명:
- 구현
- 증설된 서버의 갯수를 갖고있는 servers[] 배열을 만들어서 i ~ (i+k) - 1만큼의 인덱스까지 증설된 서버의 갯수를 늘려가기 -> 서버의 시간을 의미

정수 result = 증설횟수
각 증설된 서버는 k시간이 지나면 없어져야함.

m미만의 인원은 0인상태 즉 기존서버로만 수용가능.
0은 1이라 보면 됨.
1) 게임 이용자 수가 m명을 넘는지 체크
    - 넘는다X -> 증설X
    - 넘는다O -> 증설O  --> (players[i] / m) 나눈 수가 되어야함.

2) 현재 서버 갯수로 수용 가능한지 체크
    - 안되면 증가
*/
public class Program_389479 {
    public static int solution(int[] players, int m, int k) {
        int result = 0;

        int[] servers = new int[players.length];
        // 시간별 유저 현황 대로
        for (int i = 0; i < players.length; i++) {
            int user = players[i];

            // 증설없이 수용 가능한 경우
            if (user < m) continue;

            // 증설 해야하는 서버 갯수
            int server = user / m;

            // 현재 서버갯수로 수용 가능한 경우
            if (server <= servers[i]) continue;

            // 서버 증설해야하는 경우
            int addServer = server - servers[i];
            if (addServer > 0) result += addServer;

            for (int j = i; j < i + k; j++) {
                if (j == players.length) break; // 범위 벗어나면 멈춤
                servers[j] += addServer;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5}, 3, 5));
        System.out.println(solution(new int[]{0, 0, 0, 10, 0, 12, 0, 15, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0, 11, 0, 8, 0, 0, 0}, 5, 1));
        System.out.println(solution(new int[]{0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1}, 1, 1));
    }
}
