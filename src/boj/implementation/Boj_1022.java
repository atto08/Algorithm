package boj.implementation;

import java.io.*;
import java.util.StringTokenizer;

/*
1) 출력 형식이 잘못 됨
소요 시간: 90분
원인 : 조건 5와 6을 놓침
[조건]
5. 모든 숫자의 길이(앞에 붙는 공백을 포함)는 같아야 한다.
6. 만약 수의 길이가 가장 길이가 긴 수보다 작다면, 왼쪽에서부터 공백을 삽입해 길이를 맞춘다.
소요 시간: + 27분
조건 끝까지 읽고 구현하기!
 */
public class Boj_1022 {
    static int[] dx = {0, -1, 0, 1}, dy = {1, 0, -1, 0}, rc = {0, 0, 0, 0};
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 0 = r1, 1 = c1, 2 = r2, 3 = c2
        for (int i = 0; i < 4; i++) {
            rc[i] = Integer.parseInt(st.nextToken());
        }

        int N = Math.abs(rc[2] - rc[0]) + 1, M = Math.abs(rc[3] - rc[1]) + 1;
        map = new int[N][M];
        visited = new boolean[N][M];

        // 제일 큰수의 자릿 수
        int len = rotate(N, M);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int num = map[i][j];
                int size = String.valueOf(num).length();
                for (int k = 0; k < len - size; k++) {
                    bw.write(" ");
                }
                bw.write(num + " ");
            }
            bw.write("\n");
        }
        bw.flush();
    }

    private static int rotate(int n, int m) {
        int T = n * m;
        int r = -rc[0], c = -rc[1];
        // 오름차순 숫자, 방향 번호, 방향 조절, 제일 큰 길이
        int number = 1, rot = 1, dir = 0, maxLen = 0;
        if (r >= 0 && c >= 0 && r < n && c < m) {
            map[r][c] = number;
            T--;
        }

        while (T > 0) {
            // 오 > 위 > 왼 > 아래 > 오 ... 2번씩
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < rot; j++) {
                    r = r + dx[dir];
                    c = c + dy[dir];
                    number++;
                    if (r >= 0 && c >= 0 && r < n && c < m) {
                        map[r][c] = number;
                        T--;
                        if (maxLen == 0) {
                            maxLen = number;
                        } else if (maxLen < number) {
                            maxLen = number;
                        }
                    }
                }
                if (dir < 3) {
                    dir++;
                } else if (dir == 3) {
                    dir = 0;
                }
            }
            rot++;
        }

        return String.valueOf(maxLen).length();
    }
}
