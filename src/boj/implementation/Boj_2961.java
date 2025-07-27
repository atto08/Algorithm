package boj.implementation;

/*
도영이가 만든 맛있는 음식 - 실2
소요시간 - 71분

문제설명:
- 신맛과 쓴맛의 차이가 최소인 경우를 구하기.
    - 신맛 = 신맛1 * 신맛2 * .. * 신맛n
    - 쓴맛 = 쓴맛1 + 쓴맛2 + .. + 쓴맛n

풀이설명:
- 1 ~ n의 재료를 전부 사용했을때 값을 minDist로 설정
- 백트래킹으로 가능한 조합 방문하며 dist < minDist 경우에 갱신

p.s
1) 백트래킹 오랜만에 풀었음 -> 구현방식을 0에서 +1개씩 방향 vs 전체에서 -1개씩 방향 중 구현하면서도 고민함.
2) 중복되는 경우를 제거 조건 -> 가능한 조합을 구현하는데 오래걸렸음.
3) 전체 모두 제거하는 경우 조건문 빼먹음 -> depth == 0 을 빼면 모든재료를 사용하지 않는 경우가 포함됨. 이부분을 간과함.
*/

import java.util.*;
import java.io.*;

public class Boj_2961 {
    static int[][] SB;
    static int N, minDist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        SB = new int[N][2];
        SB[0][0] = Integer.parseInt(st.nextToken());
        SB[0][1] = Integer.parseInt(st.nextToken());

        int S = SB[0][0], B = SB[0][1];

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            SB[i][0] = Integer.parseInt(st.nextToken());
            SB[i][1] = Integer.parseInt(st.nextToken());
            S *= SB[i][0];
            B += SB[i][1];
        }

        minDist = Math.abs(S - B); // N개 재료 전체
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            visited[i] = true;
            dfs(i, N - 1, S, B);
        }

        System.out.println(minDist);
    }

    private static void dfs(int idx, int depth, int S, int B) {
        if (depth == 0) return;

        int nS = S / SB[idx][0], nB = B - SB[idx][1];
        int dist = Math.abs(nS - nB);
        if (dist < minDist) minDist = dist;

        for (int i = idx; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, depth - 1, nS, nB);
                visited[i] = false;
            }
        }
    }
}
