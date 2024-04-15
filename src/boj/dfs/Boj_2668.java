package boj.dfs;

import java.io.*;
import java.util.Arrays;
/*
숫자고르기 - 골5
소요 시간: 65분
이전에 문제를 접했을때는 key > val 로 접근
문제를 다시 접한 오늘은 문제가 그래프 탐색하는 과정으로 보였음
그래서
1. key > val 값을 갖고있는 이차원 배열 그래프를 생성
2. key(visited[0])와 val(visited[1])의 방문처리가 같으면 check1 배열 값을 true 변경
하는 방법으로 문제 해결
 */
public class Boj_2668 {
    static int N;
    static int[][] numbers;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new int[N + 1][N + 1];
        // i=0 >> key 배열  / 1 >> val 배열
        for (int i = 1; i <= N; i++) {
            int val = Integer.parseInt(br.readLine());
            numbers[i][val] = val;
        }

        boolean[] check1 = new boolean[N + 1];
        boolean trigger;
        for (int i = 1; i <= N; i++) {
            visited = new boolean[2][N + 1];
            visited[0][i] = true;
            trigger = false;
            dfs(i);

            boolean[] check2 = new boolean[N + 1];
            for (int j = 1; j <= N; j++) {
                if (visited[0][j] != visited[1][j]) {
                    trigger = true;
                    Arrays.fill(check2, false);
                    break;
                } else {
                    if (visited[0][j]) {
                        check2[j] = true;
                    }
                }
            }

            if (!trigger) {
                for (int j = 1; j <= N; j++) {
                    if (check2[j]) {
                        check1[j] = true;
                    }
                }
            }
        }

        int sum = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (check1[i]) {
                sum++;
                sb.append(i).append("\n");
            }
        }

        System.out.println(sum);
        System.out.println(sb);
    }

    private static void dfs(int node) {

        for (int i = 1; i <= N; i++) {
            int idx = numbers[node][i];
            if (idx != 0 && !visited[1][i]) {
                visited[1][idx] = true;
                visited[0][i] = true;
                dfs(i);
            }
        }
    }
}
