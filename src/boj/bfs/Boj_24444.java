package boj.bfs;

import java.io.*;
import java.util.*;

public class Boj_24444 {
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

            if (!map.containsKey(key) && !map.containsKey(val)) {
                keySet = new HashSet<>();
                valSet = new HashSet<>();
                keySet.add(val);
                valSet.add(key);
                map.put(key, keySet);
                map.put(val, valSet);
            } else if (!map.containsKey(key) && map.containsKey(val)) {
                keySet = new HashSet<>();
                keySet.add(val);
                map.put(key, keySet);
                valSet = map.get(val);
                valSet.add(key);
                map.replace(val, valSet);
            } else if (map.containsKey(key) && !map.containsKey(val)) {
                valSet = new HashSet<>();
                valSet.add(key);
                map.put(val, valSet);
                keySet = map.get(key);
                keySet.add(val);
                map.replace(key, keySet);
            } else {
                keySet = map.get(key);
                valSet = map.get(val);
                keySet.add(val);
                valSet.add(key);
                map.replace(key, keySet);
                map.replace(val, valSet);
            }
        }

        try {
            bfs(R);
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

    public static void bfs(int R) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(R);
        visited[R] = true;
        result[R] = count++;

        while (!queue.isEmpty()) {
            int key = queue.poll();
            List<Integer> list = new ArrayList<>(map.get(key));
            Collections.sort(list);

            for (int val : list) {
                if (!visited[val]) {
                    queue.offer(val);
                    visited[val] = true;
                    result[val] = count++;
                }
            }
        }
    }
}
