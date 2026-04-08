package boj.implementation;

import java.io.*;
import java.util.*;

/*
체스판 다시 칠하기 - 실3
소요시간 - 41분

[문제설명]
- N*M 크기의 보드 중 8*8이 될 수 있는 경우 중
- 체스판을 구성할 수 있는 칸의 값을 바꾸는 횟수의 최솟값 구하기

[풀이설명]
- 체스판이 될 수 있는 구조는 B W B W ~~ or W B W B ~~ 로 시작하는 2가지 경우
- 각각 chess1, chess2로 8*8 배열 생성 후 N*M 중 8*8이 되는 모든 구간을 탐색하며,
- chess1, 2 배열과 값이 다른 경우를 모두 세어 둘 중 더 작은 경우로 갱신
 */
public class BOJ_1018 {
    static int N, M, result;
    static char[][] map, chess1, chess2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        chess1 = new char[8][8];
        chess2 = new char[8][8];
        for (int i = 0; i < 8; i++) {
            chess1[0][i] = i % 2 == 0 ? 'B' : 'W';
            chess2[0][i] = chess1[0][i] == 'B' ? 'W' : 'B';
        }

        for (int i = 1; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chess1[i][j] = chess2[i - 1][j];
                chess2[i][j] = chess1[i - 1][j];
            }
        }

        result = 64;
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                findMinChange(i, j);
            }
        }

        System.out.println(result);
    }

    static void findMinChange(int x, int y) {
        int diff1 = 0, diff2 = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chess1[i][j] != map[i + x][j + y]) {
                    diff1++;
                }
                if (chess2[i][j] != map[i + x][j + y]) {
                    diff2++;
                }
            }
        }

        result = Math.min(result, Math.min(diff1, diff2));
    }
}
