package boj.implementation;

import java.io.*;
import java.util.StringTokenizer;

/*
1) 틀렸습니다.
단순 회전 문제기에 잘못 회전된 경우가 있다고 유추
테스트 케이스 중 startX와 endX 의 차이 & startY와 endY 의 차이 중 1일 때 까지만 돌려야 함
그런데 내 코드는 이를 지나서도 돌리고 있었음. - 오류발생
while 문에 조건문 수정
2) 통과
 */
public class Boj_16926 {
    static int N, M, R;
    static int[][] map;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rotation();

        bw.flush();
    }

    private static void rotation() throws IOException {
        for (int r = 0; r < R; r++) {
            int startX = 0, startY = 0, endX = N - 1, endY = M - 1;
            while (endX - startX > 0 && endY - startY > 0) {
                int num = map[startX][startY];
                //y-1
                for (int y = startY; y < endY; y++)
                    map[startX][y] = map[startX][y + 1];
                //x-1
                for (int x = startX; x < endX; x++)
                    map[x][endY] = map[x + 1][endY];
                //y+1
                for (int y = endY; y > startY; y--)
                    map[endX][y] = map[endX][y - 1];
                //x+1
                for (int x = endX; x > startX + 1; x--)
                    map[x][startY] = map[x - 1][startY];

                map[startX + 1][startY] = num;
                startX++;
                startY++;
                endX--;
                endY--;
            }
        }
        for (int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            bw.write(sb.toString().trim() + "\n");
        }
    }
}
