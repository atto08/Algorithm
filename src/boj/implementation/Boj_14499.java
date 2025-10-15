package boj.implementation;

import java.util.*;
import java.io.*;

/*
주사위 굴리기 - 골4
소요시간 - 85분

[문제설명]
- 주사위 처음 위치 좌표 및 이동 명령어
- 이동할때마다 윗면에 적힌 숫자 출력
- 주사위는 지도 밖으로 이동할 수 없다. -> 바깥이동 명령어 무시 + 출력 x
- 이동 칸에 적힌 수가 0이면 -> 주사위 바닥면 수가 칸으로 복사
- 이동 칸에 적힌 수가 0이 아니면 -> 칸에 적힌 수가 주사위 바닥면으로 복사

[풀이 및 규칙]
기준
  2
4 1 3
  5
  6
** 주사위 값 변환 **
동쪽으로 이동(1)
  2
1 3 6
  5
  4
3 -> 1
1 -> 4
4 -> 6
6 -> 3

서쪽으로 이동(2)
  2
6 4 1
  5
  3
4 -> 1
1 -> 3
3 -> 6
6 -> 4

북쪽으로 이동(3)
  6
4 2 3
  1
  5
2 -> 1
1 -> 5
5 -> 6
6 -> 2

남쪽으로 이동(4)
  1
4 5 3
  6
  2
5 -> 1
1 -> 2
2 -> 6
6 -> 5

p.s
2주만에 풀었는데도 확실히 설계하고 구현하니까 편하다. 앞으로도 이렇게 하도록
 */

public class Boj_14499 {

    // 지도 세로, 지도 가로, 명령 수, 현재 좌표 R, 현재 좌표 C
    static int N, M, K, R, C;

    // 주사위가 움직일 지도
    static int[][] map;

    // 움직임
    static int[] dr = { 0, 0, -1, 1 }, dc = { 1, -1, 0, 0 };

    // 주사위 값
    static int[] dicePos;

    private static void changeDiceVal(int k) {

        if (k == 0) {
            int prefix = dicePos[3];
            dicePos[3] = dicePos[6];
            dicePos[6] = dicePos[4];
            dicePos[4] = dicePos[1];
            dicePos[1] = prefix;
        }
        else if (k == 1) {
            int prefix = dicePos[4];
            dicePos[4] = dicePos[6];
            dicePos[6] = dicePos[3];
            dicePos[3] = dicePos[1];
            dicePos[1] = prefix;
        }
        else if (k == 2) {
            int prefix = dicePos[2];
            dicePos[2] = dicePos[6];
            dicePos[6] = dicePos[5];
            dicePos[5] = dicePos[1];
            dicePos[1] = prefix;
        }
        // 남쪽으로 이동(4) 5 -> 1 1 -> 2 2 -> 6 6 -> 5
        else {
            int prefix = dicePos[5];
            dicePos[5] = dicePos[6];
            dicePos[6] = dicePos[2];
            dicePos[2] = dicePos[1];
            dicePos[1] = prefix;
        }
    }

    // 1) 주사위 이동
    private static boolean moveDice(int k) {
        int nr = R + dr[k];
        int nc = C + dc[k];

        // 범위를 벗어나면 이동 x
        if (nr < 0 || nc < 0 || nr >= N || nc >= M)
            return false;

        // 주사위 이동 !!
        changeDiceVal(k);

        if (map[nr][nc] == 0) {
            // 움직인 지도 바닥에 주사위 바닥 값이 복사됨
            map[nr][nc] = dicePos[1];
        } else if (map[nr][nc] > 0) {
            // 지도 바닥 값이 주사위 바닥에 복사됨
            dicePos[1] = map[nr][nc];
            map[nr][nc] = 0;
        }

        // 주사위 위치 변경
        R = nr;
        C = nc;

        return true;
    }

    private static void inputData(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 지도 초기화
        map = new int[N][M];

        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < M; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        // 주사위 6면 초기화
        dicePos = new int[7];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        inputData(br);

        StringTokenizer st = new StringTokenizer(br.readLine());
        // K번 동안
        for (int k = 1; k <= K; k++) {
            int order = Integer.parseInt(st.nextToken());
            // 1) 주사위 이동
            boolean isMove = moveDice(order - 1);
            if (!isMove)
                continue;
            // 2) 윗면 출력
            bw.write(dicePos[6] + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}