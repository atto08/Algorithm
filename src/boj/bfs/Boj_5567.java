package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
결혼식 - 실2
소요시간 - 45분
원인 -
1로 시작해서 연결점을 모두 도는 형식으로 접근함
>> 어떤 하나를 놓쳐서 재방문 경우 발생
>> 모든 노드를 기준으로 1번 노드를 dist = 2 안으로 만나는지 체크하는 방향으로 우회

문제의도 - 특정 거리의 노드만 셀 수 있는가 였다고 생각.
 */
public class Boj_5567 {
    static int N, M, result;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(k).add(v);
            graph.get(v).add(k);
        }

        for (int i = 2; i <= N; i++) bfs(i);
        System.out.println(result);
    }

    private static void bfs(int node) {
        boolean[] visit = new boolean[N + 1];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{node, 0});
        visit[node] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            if (now[0] == 1) {
                if (now[1] > 0 && now[1] < 3) {
                    result++;
                }
                break;
            }

            for (int n : graph.get(now[0])) {
                if (!visit[n]) {
                    visit[n] = true;
                    q.offer(new int[]{n, now[1] + 1});
                }
            }
        }
    }
}
