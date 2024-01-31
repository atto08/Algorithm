package boj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Boj_13023 {
    static StringTokenizer st;
    static HashMap<Integer, HashSet<Integer>> map;
    static boolean[] visited;
    static int N, result;
    /*
    1) 틀렸습니다
    전역변수 visited 를 매개변수로 받아, 모든 경우의 수를 확인하려 함.
    ex)
    dfs(val, count+1, visited); 만 했더니 모든 경우의 수를 돌지않음.
    ==> 이유는 나왔을 때, 해당 visited[val] = false 처리를 다시 해줬어야했다.

    2) 시간초과
    map 과 visited 를 매개변수로 받던 것을 다시 전역변수로 선언
    해당 node 와 인접한 노드이고 방문하지 않은 노드라면
    dfs(val, count+1) 를 통해 경우를 체크
    위에서 나와서 해당 노드와 인접하고 방문하지않은 노드를 모두 체크한 후
    검사한 노드의 방문을 false 로 바꾸면 전역변수로 선언해도 확인이 가능해지고 시간도 단축 됨.
    visited[node] = false; 
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        result = 0;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        map = new HashMap<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(key, k -> new HashSet<>()).add(val);
            map.computeIfAbsent(val, v -> new HashSet<>()).add(key);
        }
        for (int i = 0; i < N; i++) {
            if (result != 1) {
                dfs(i, 1);
                Arrays.fill(visited, false);
            }
        }

        System.out.println(result);
        br.close();
    }

    public static void dfs(int node, int count) {

        if (count == 5) {
            result = 1;
            return;
        }

        if (!visited[node]) {
            visited[node] = true;

            try {
                for (int val : map.get(node)) {
                    if (!visited[val]) {
                        dfs(val, count + 1);
                    }
                }
            } catch (NullPointerException ignored){

            }
            visited[node] = false;
        }
    }
}
