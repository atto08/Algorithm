package boj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
대각선 상 위치 확인을 dx, dy를 통해 어렵게 시도함
==> 백트래킹의 어려움을 겪음
블로그 참고
(열의 차이 == 행의 차이)  ==>> 두 퀸은 같은 위치에 있는 것으로 판정
 */
public class Boj_9663 {
    static int N, result;
    static int[] chessBoard;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        chessBoard = new int[N];
        dfs(0);

        System.out.println(result);
    }

    public static void dfs(int depth) {
        if (depth == N) {
            result++;
            return;
        }

        for (int i = 0; i < N; i++) {
            chessBoard[depth] = i;
            if (canGo(depth)) {
                dfs(depth + 1);
            }
        }
    }

    public static boolean canGo(int col) {

        for (int i = 0; i < col; i++) {
            // 같은 행의 존재하는 경우 or 대각선에 존재하는 경우
            if (chessBoard[col] == chessBoard[i] || col - i == Math.abs(chessBoard[col] - chessBoard[i])) {
                return false;
            }
        }
        return true;
    }
}
