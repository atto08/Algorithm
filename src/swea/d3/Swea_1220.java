package swea.d3;

import java.io.*;
import java.util.StringTokenizer;

/*
Magnetic - D3
소요 시간: 80분 초과
원인: 동작 구현에 신경쓰다가 구현하지 못함
해결: 문제를 자세히 보면 각 열마다 12가 되는 (교착상태) 것만 세주면 되는 문제였음
 */
public class Swea_1220 {
    static int[][] map;
    static int N, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int test_case = 1; test_case <= 10; test_case++) {

            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            result = 0;
            for (int y = 0; y < N; y++) {
                magnetic(y);
            }

            bw.write("#" + test_case + " " + result + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static void magnetic(int y) {

        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < N; x++) {
            if (map[x][y] != 0) {
                sb.append(map[x][y]);
            }
        }

        for (int i = 0; i < sb.length()-1; i++) {
            if (sb.toString().substring(i, i + 2).equals("12")) {
                result++;
            }
        }
    }
}
