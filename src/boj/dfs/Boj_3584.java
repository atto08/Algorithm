package boj.dfs;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
조상을 찾는 문제라 자손의 ArrayList에 부모의 값만 넣음
방문을 했는지를 초기화 하지않아 두번째 노드가 dfs로 돌아갈 때,
visited[node]가 true면 해당 노드가 가장 가까운 공통 조상으로 판별.

1) 틀렸습니다 (25%)
==> 비교하는 두노드간 부모 자식 관계인 경우 때문에 dfs가 시작할때 부터 방문여부를 확인하는 것으로 수정
통과
 */
public class Boj_3584 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            N = Integer.parseInt(br.readLine());
            visited = new boolean[N + 1];
            graph = new ArrayList<>(N + 1);
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 1; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int k = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph.get(v).add(k);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= 2; i++) {
                int node = Integer.parseInt(st.nextToken());
                dfs(node);
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }

    public static void dfs(int node) throws IOException {
        if (!visited[node]) {
            visited[node] = true;

            for (int num : graph.get(node)) {
                if (!visited[num]) {
                    dfs(num);
                } else {
                    bw.write(num + "\n");
                }
            }
        } else {
            bw.write(node + "\n");
        }
    }
}
