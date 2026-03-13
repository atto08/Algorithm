package boj.twice;

import java.io.*;
/*
달팽이 - 실3
소요시간 - 39분

메모리 / 시간
57MB / 508ms

[풀이 설명]
규칙1. 좌표 (1,1)에는 N^2 값이 고정
규칙2. 벽(x or y == 0)이나 방문한 곳(arr[x][y] > 0)을 만나면 회전
규칙3. num = N^2 ~ 1이 될 때까지만 반복

위 3가지 규칙만 지키고 다음과 같이 코드를 작성하면 문제 풀이 가능
 */
public class BOJ_1913 {
    static int N, targetNum;
    static int[][] arr;
    static int[] dx = { 1, 0, -1, 0 }, dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        targetNum = Integer.parseInt(br.readLine());

        arr = new int[N + 1][N + 1];

        int x = 1, y = 1, d = 0;
        int num = N * N;

        int[] targetXY = new int[2];

        while (num > 0) {
            if (num == targetNum) {
                targetXY[0] = x;
                targetXY[1] = y;
            }

            // 현재 위치에 숫자 집어넣기
            arr[x][y] = num--;
            // 숫자 감소 & 좌표 위치 ++
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 좌표 값이 벗어나거나 방문한 위치면 회전
            if ((nx < 1 || ny < 1 || nx > N || ny > N) || arr[nx][ny] > 0) {
                d = rotateDirection(d);
            }
            x += dx[d];
            y += dy[d];
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                bw.write(arr[i][j] + " ");
            }
            bw.write("\n");
        }

        bw.write(targetXY[0] + " " + targetXY[1]);
        bw.flush();
    }

    private static int rotateDirection(int dir) {
        if (dir < 3) {
            return dir + 1;
        }
        return 0;
    }
}