package boj.implementation;

import java.io.*;
import java.util.StringTokenizer;

/*
풀이시간 : 36분
 */
public class Boj_1051 {
    static int N, M, result;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        if (N >= M) {
            result = M;
        } else {
            result = N;
        }

        boolean check = false;
        while (result != 1) {
            for (int i = 0; i < N - result + 1; i++) {
                for (int j = 0; j < M - result + 1; j++) {
                    int num = result - 1;
                    if (map[i][j] == map[i + num][j] && map[i][j] == map[i][j + num] && map[i][j] == map[i + num][j + num]) {
                        check = true;
                        break;
                    }
                }
            }
            if (check) {
                result = result * result;
                break;
            }
            result--;
        }

        System.out.println(result);
    }
}
