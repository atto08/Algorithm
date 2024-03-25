package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
내려가기 - 골5
1) 틀렸습니다.
==> dp에 현재 행 인덱스의 값 + 이전 행의 위 옆 인덱스 값을 비교한 값
    이 아닌 이전 행 인덱스 값 + 현재 행 인덱스 값을 더하고 있었음
2) 통과
 */
public class Boj_2096 {
    static StringBuilder sb = new StringBuilder();
    static int[][] map;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][3];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        downStair();

        System.out.println(sb.toString());
    }

    private static void downStair() {
        int[] dpMax = new int[3];
        int[] dpMin = new int[3];

        for (int i = 0; i < 3; i++) {
            dpMax[i] = map[0][i];
            dpMin[i] = map[0][i];
        }

        int cnt = 1;
        while (cnt < N) {
            int[] max = new int[3];
            int[] min = new int[3];
            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    max[i] = map[cnt][i] + Math.max(dpMax[i], dpMax[i + 1]);
                    min[i] = map[cnt][i] + Math.min(dpMin[i], dpMin[i + 1]);

                } else if (i == 2) {
                    max[i] = map[cnt][i] + Math.max(dpMax[i - 1], dpMax[i]);
                    min[i] = map[cnt][i] + Math.min(dpMin[i - 1], dpMin[i]);

                } else {
                    max[i] = map[cnt][i] + Math.max(dpMax[i], Math.max(dpMax[i - 1], dpMax[i + 1]));
                    min[i] = map[cnt][i] + Math.min(dpMin[i], Math.min(dpMin[i - 1], dpMin[i + 1]));
                }
            }
            for (int i = 0; i < 3; i++) {
                dpMax[i] = max[i];
                dpMin[i] = min[i];
            }
            cnt++;
        }

        Arrays.sort(dpMax);
        Arrays.sort(dpMin);
        sb.append(dpMax[2]).append(" ").append(dpMin[0]);
    }
}
