package programmers.lv2;

/*
피로도 - lv2
소요시간 - 1시간 30분
분류 - 완전탐색
소개 - 주어진 현재 피로도 k와 배열 dungeons을 갖고 유저가 클리어할 수 있는 최대 던전 수를 구하는 문제
    - 현재 피로도: k, 던전 정보: dungeons[][]
    - 던전 idx = 0) 최소 필요 피로도 & idx = 1) 소모 피로도
풀이
- 가능한 조합의 수가 필요 -> 백트래킹으로 모든 던전을 탐색하는 경우 구현
- 플레이 가능 여부 판단 -> 플레이가 가능하다면 던전의 갯수까지 확인 or 그렇지 않다면 리턴

 */
public class Program_87946 {
    static int result, N;
    static boolean[] visited;
    static int[][] duns;

    public int solution(int k, int[][] dungeons) {
        N = dungeons.length;        // 던전 개수 저장
        duns = dungeons;            // 던전 정보를 전역 변수로 저장
        visited = new boolean[N];   // 각 던전의 방문 여부 체크 배열

        // 모든 던전에 대해 첫 탐색 시도
        for (int i = 0; i < N; i++) {
            visited[i] = true;      // 현재 던전 방문 처리
            visit(i, 1, k);  // 탐색 시작 (현재 탐험한 던전 개수: 1)
            visited[i] = false;     // 백트래킹: 다른 경우의 수 탐색을 위해 원복
        }

        return result;
    }

    // 해당 던전을 클리어할 수 있는지 확인
    private static boolean canPlay(int node, int k) {
        int minT = k - duns[node][0];  // 최소 필요 피로도 충족 여부 확인
        int useT = k - duns[node][1];  // 소모 피로도가 충분한지 확인
        if (minT < 0 || useT < 0) return false;  // 둘 중 하나라도 부족하면 탐험 불가
        return true;
    }

    // 백트래킹을 이용한 모든 경우의 수 탐색
    private static void visit(int node, int depth, int k) {
        if (!canPlay(node, k)) return;       // 현재 던전을 탐험할 수 없다면 종료
        if (depth > result) result = depth;  // 최대 탐험 가능한 던전 개수 갱신
        if (depth == N) return;              // 모든 던전을 탐험한 경우 종료

        // 다음 탐색할 던전 선택
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {                      // 아직 방문하지 않은 던전이면 탐색
                visited[i] = true;                  // 방문 처리
                visit(i, depth + 1, k - duns[node][1]);  // 피로도 차감 후 다음 던전 탐험
                visited[i] = false;                 // 백트래킹: 다른 경우의 수를 탐색하기 위해 복구
            }
        }
    }

    public static void main(String[] args) {
        Program_87946 pg = new Program_87946();
        System.out.println(pg.solution(80, new int[][]{{80, 20}, {50, 40}, {30, 10}}));
    }
}
