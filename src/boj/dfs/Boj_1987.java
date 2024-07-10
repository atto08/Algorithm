package boj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
알파벳 - 골4
소요 시간: 20분

1) 통과
풀이: set을 이용해 겹치는 문자를 체크 & 방문 체크를 통해 백 트래킹 코드로 구현함

최근에 사용한 set을 이용한 string 중복 체크를 통해 빠르고 쉽게 풀 수 있었음.
DFS 내부 반복문의 조건을 깔끔하게 작성하여 별 다른 조건없이 빠르게 풀이 가능했음.
*/
public class Boj_1987 {
    static int R, C, max;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    static HashSet<Character> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) map[i] = br.readLine().toCharArray();

        visited[0][0] = true;
        set.add(map[0][0]);
        dfs(0, 0);

        System.out.println(max);
    }

    private static void dfs(int x, int y) {

        if (set.size() > max) max = set.size();

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;

            if (!visited[nx][ny] && !set.contains(map[nx][ny])) {
                visited[nx][ny] = true;
                set.add(map[nx][ny]);
                dfs(nx, ny);
                visited[nx][ny] = false;
                set.remove(map[nx][ny]);
            }
        }
    }
}
