package boj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
스타트와 링크 - 실1
소요 시간: 60분

1. 집중 안함
2. 백트래킹에서 실수
>> false로 수정 후 통과

집중하자. 이런식으로 코딩 테스트보면 광탈이다.
자만, 안도하지말자 아직 한참 부족한 사람이 나다.
 */
public class Boj_14889 {
    static int N, result = Integer.MAX_VALUE;
    static int[][] startLink;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        startLink = new int[N + 1][N + 1];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                startLink[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N / 2; i++) {
            visited = new boolean[N + 1];
            visited[i] = true;
            dfs(i, 1);
        }

        // 결과적으로 스타트 팀과 링크 팀 능력치 차이의 최솟값 출력
        System.out.println(result);
    }

    private static void dfs(int node, int depth) {

        if (depth == N / 2) {
            // 스타트 팀의 능력치 & 링크 팀의 능력치
            int startSum = 0, linkSum = 0;
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) { // 링크팀
                    for (int j = 1; j <= N; j++) {
                        if (i != j && !visited[j]) {
                            linkSum += startLink[i][j];
                        }
                    }
                } else { // 스타트 팀
                    for (int j = 1; j <= N; j++) {
                        if (i != j && visited[j]) {
                            startSum += startLink[i][j];
                        }
                    }
                }
            }

            int num = Math.abs(startSum - linkSum);
            if (num < result) {
                result = num;
            }
            return;
        }

        for (int i = node + 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, depth + 1);
                visited[i] = false;
            }
        }
    }
}
