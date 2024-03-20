package boj.implementation;

import java.io.*;
import java.util.*;

/*
테트로미노 - 골4
1) 틀렸습니다
틀린 이유) 대칭되는 경우의 도형들도 생각해야 됨
ㄴ 모양 네가지 경우의 대칭모양과
ㄹ 모양 두가지 경우의 대칭모양 추가
2) 통과
소요 시간: 74분
 */
public class Boj_14500 {
    static int N, M, max;
    static int[][] map;
    static int[][] calX = {{0, 0, 0}, {1, 2, 3}
            , {1, 2, 2}, {0, 0, 1}, {0, 0, -1}, {0, 1, 2}
            , {0, -1, -2}, {0, 0, 1}, {1, 1, 1}, {0, 1, 2}
            , {0, -1, -1}, {1, 1, 2}, {0, 1, 1}, {1, 0, -1}
            , {0, 0, -1}, {0, 0, 1}, {-1, 0, 1}, {-1, 0, 1}
            , {0, 1, 1}};
    static int[][] calY = {{1, 2, 3}, {0, 0, 0}
            , {0, 0, 1}, {1, 2, 0}, {1, 2, 2}, {1, 1, 1}
            , {1, 1, 1}, {1, 2, 2}, {0, 1, 2}, {1, 0, 0}
            , {1, 1, 2}, {0, 1, 1}, {1, 1, 2}, {0, 1, 1}
            , {1, 2, 1}, {1, 2, 1}, {1, 1, 1}, {0, 1, 0}
            , {1, 0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < M; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                bfs(x, y);
            }
        }

        System.out.println(max);
    }

    private static void bfs(int x, int y) {
        List<Integer> sumList = new ArrayList<>();

        for (int cnt = 0; cnt < calX.length; cnt++) {
            int[] X = calX[cnt];
            int[] Y = calY[cnt];
            int sum = map[x][y];
            for (int i = 0; i < 3; i++) {
                int nextX = x + X[i];
                int nextY = y + Y[i];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    sum = map[x][y];
                    break;
                }
                sum += map[nextX][nextY];
            }

            if (sum != map[x][y]) {
                sumList.add(sum);
            }
        }

        Collections.sort(sumList);
        int maxVal = sumList.size() - 1;
        if (sumList.size() > 0) {
            if (max < sumList.get(maxVal)) {
                max = sumList.get(maxVal);
            }
        }
    }
}
