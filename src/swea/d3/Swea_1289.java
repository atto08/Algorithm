package swea.d3;

import java.io.*;
/*
원재의 메모리 복구하기 - D3
소요 시간: 25본

구현방법
1. 맨 앞자리 부터 순서대로 주어진 숫자와 같은 숫자인지 확인
2. 같지않다면 해당 위치부터 뒤에까지 전부 바꿈 > 바꾸게 된다면 min++
 */
public class Swea_1289 {
    static int min;
    static String number, start;
    static boolean[] visited;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            number = br.readLine();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < number.length(); i++) {
                sb.append(0);
            }

            start = sb.toString();
            visited = new boolean[number.length()];
            min = 0;

            dfs(0, 0);

            bw.write("#" + test_case + " " + min + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static void dfs(int node, int depth) {

        if (depth == number.length() - 1) {
            return;
        } else if (depth < number.length() - 1) {
            for (int i = node; i < number.length(); i++) {
                if (number.charAt(i) == start.charAt(i)) {
                    visited[i] = true;
                } else {
                    StringBuilder sb = new StringBuilder().append(start.substring(0, i));
                    for (int j = i; j < number.length(); j++) {
                        if (start.charAt(j) == '0') {
                            sb.append(1);
                        } else {
                            sb.append(0);
                        }
                    }
                    min++;
                    start = sb.toString();
                    visited[i] = true;
                    dfs(i + 1, depth + 1);
                }
            }
        }
    }
}