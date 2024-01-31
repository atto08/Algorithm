package boj.dfs;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Boj_15681 {
    static StringTokenizer st;
    static int N, R, Q;
    static int[] subTreeSize;
    static boolean[] visited;
    static HashMap<Integer, HashSet<Integer>> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        map = new HashMap<>(N);
        visited = new boolean[N + 1];
        subTreeSize = new int[N + 1];

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(key, k -> new HashSet<>()).add(val);
            map.computeIfAbsent(val, v -> new HashSet<>()).add(key);
        }

        /*
         이렇게 미리 루트 노드를 넣고 모든 정점의 서브트리 배열을 구해놓으면
         중복되는 경우를 제거해 메모리 초과를 방지할 수 있음.
         1) 메모리 초과
         ==> 아래 반복문에서 dfs 형식으로 일일히 검사하는 방식을 사용했더니 메모리초과 발생

         루트 노드를 시작으로 한번 검사를 진행 후 U의 값에 따라 변하는 서브트리 값을 배열에 저장
         해당 U값에 대한 서브트리값을 출력.
         */

        calculateSubTreeSize(R);

        for (int i = 0; i < Q; i++) {
            int U = Integer.parseInt(br.readLine());
            bw.write(subTreeSize[U] + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    // 서브트리 구하기 (help Gpt)
    public static int calculateSubTreeSize(int node) {
        visited[node] = true;
        subTreeSize[node] = 1;

        for (int val : map.get(node)) {
            if (!visited[val]) {
                subTreeSize[node] += calculateSubTreeSize(val);
            }
        }
        return subTreeSize[node];
    }
}
