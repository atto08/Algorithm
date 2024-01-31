package boj.dfs;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1240 {
    static StringTokenizer st;
    static int N, M, sp, ep, result;
    static boolean[] visited;
    static int[][] map;

    /*
    1) 틀렸습니다.
    ex)
    5 3            ans)
    4 5 1            1    
    3 5 1            1
    2 1 1            4
    1 3 1          output)
    1 2              1
    1 3              2
    4 2              4

    2) 틀렸습니다.
    ex)
    7 6            ans)
    1 2 2            2    
    1 4 3            3
    4 3 2            2
    4 7 1            3
    2 5 2            5
    2 6 3            4
    2 5            output)
    2 6             2
    1 2             3
    1 4             2
    1 3             3
    1 7             7
                    6
                   
위 테스트 케이스 생각하면 기억날듯                   
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            map[u][v] = map[v][u] = s;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            result = 0;
            sp = Integer.parseInt(st.nextToken());
            ep = Integer.parseInt(st.nextToken());

            dfs(sp, 0);
            bw.write(result + "\n");
            Arrays.fill(visited, false);
        }

        bw.flush();
        br.close();
        bw.close();
    }

    public static void dfs(int node, int count) {
        visited[node] = true;

        if (!visited[ep] && map[node][ep] != 0) {
            count += map[node][ep];
            result = count;
        } else {
            for (int i = 1; i <= N; i++) {
                if (!visited[i] && map[node][i] != 0) {
                    if (ep == i) {
                        result = count;
                        break;
                    } else {
                        dfs(i, count + map[node][i]);
                    }
                }
            }
        }
    }
}
