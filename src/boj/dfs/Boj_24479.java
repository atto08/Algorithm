package boj.dfs;

import java.io.*;
import java.util.*;

public class Boj_24479 {
    static int N, M, R;
    static int count = 1;
    static boolean[] visited;
    static int[] result;
    static HashMap<Integer, HashSet<Integer>> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        result = new int[N + 1];

        HashSet<Integer> keySet;
        HashSet<Integer> valSet;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int key = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(key, k -> new HashSet<>()).add(val);
            map.computeIfAbsent(val, k -> new HashSet<>()).add(key);
        }

        try {
            dfs(map.get(R), R);
        } catch (NullPointerException ignored) {
            // 아예 연결되지 않으면 나는 set에 R값을 넣지않음. 그래서 set에 R값과 관련된 값이 존재하지 않아서 발생.
        }

        for (int i = 1; i <= N; i++) {
            bw.write(result[i] + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    public static void dfs(HashSet<Integer> set, int R) {

        visited[R] = true;
        result[R] = count;
        count++;

        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);

        for (int number : list) {
            if (!visited[number]) {
                dfs(map.get(number), number);
            }
        }
    }

    /*
    이건 메모리초과!. 배열 때문이지 않은가 싶다.
import java.io.*;
import java.util.StringTokenizer;

public class Baek_11724_2 {
    static StringTokenizer st;
    static int N, M, count;
    static boolean[] visited;
    static int[][] map;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        map = new int[N + 1][N + 1];
        result = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            map[key][val] = 1;
            map[val][key] = 1;
        }

        count = 1;
        try {
            result[R] = count++;
            dfs(R);
        } catch (NullPointerException ignored) {

        }

        for (int i = 1; i <= N; i++) {
            bw.write(result[i] + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    public static void dfs(int R) {
        visited[R] = true;

        for (int i = 1; i <= N; i++) {
            if (map[R][i] == 1 && !visited[i]) {
                result[i] = count++;
                dfs(i);
            }
        }
    }
}
     */
}
