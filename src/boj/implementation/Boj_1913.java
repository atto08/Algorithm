package boj.implementation;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/*
달팽이 - 실3
소요 시간: 3 ~ 40분

출력을해야하는 문제에서
System,out.print 메서드로 일일히 출력하는 것과 BufferedWriter를 사용해 한번에 출력하는 속도의 차이를 다시한번 깨달았음.
4700ms -> 640ms 약 7배 빠름
 */
public class Boj_1913 {
    static int[][] map;
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1}, result = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int targetNum = Integer.parseInt(br.readLine());

        map = new int[N][N];

        inputNumbers(N, targetNum);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bw.write(map[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.write(result[0] + " " + result[1]);
        bw.flush();
    }

    private static void inputNumbers(int N, int targetNum) {

        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.offer(new Node(0, 0, N * N, 0));

        while (!q.isEmpty()) {
            Node current = q.poll();

            int dir = current.d;
            int num = current.n;

            if (targetNum == num) {
                result[0] = current.x + 1;
                result[1] = current.y + 1;
            }

            visited[current.x][current.y] = true;
            map[current.x][current.y] = num;

            int nx = current.x + dx[dir];
            int ny = current.y + dy[dir];

            if (num == 1) break;

            if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) {
                dir = (dir + 1) % 4;
                nx = current.x + dx[dir];
                ny = current.y + dy[dir];
            }

            visited[nx][ny] = true;
            map[nx][ny] = num--;
            q.offer(new Node(nx, ny, num, dir));
        }
    }

    static class Node {
        int x, y, n, d;

        private Node(int x, int y, int n, int d) {
            this.x = x;
            this.y = y;
            this.n = n;
            this.d = d;
        }
    }
}
