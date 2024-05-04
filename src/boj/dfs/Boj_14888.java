package boj.dfs;
import java.io.*;
import java.util.StringTokenizer;
/*
 연산자 끼워넣기 - 실1
 소요 시간: 72분
 잘한 부분: 백트래킹을 사용한 완전탐색 문제인것을 빠르게 유추함
 못한 부분 -
 1. ArrayIndex예외가 서로 다른 두개의 배열을 하나의 반복문으로 작성하여 발생해 예상보다 많은 시간을 소요함
 2. N의 범위가 더크다면 실패했을 것
 */
public class Boj_14888 {
    static int N, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    static int[] A, operator;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) { // 수열A
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] operatorCnt = new int[4]; // 연산자 배열
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operatorCnt[i] = Integer.parseInt(st.nextToken());
        }

        operator = new int[N - 1]; // 사용할 연산자 나열 배열
        int idx = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < operatorCnt[i]; j++) {
                operator[idx] = i;
                idx++;
            }
        }

        for (int i = 0; i < N - 1; i++) { // 완전탐색
            visited = new boolean[N - 1];
            visited[i] = true;
            dfs(i, 0, new StringBuilder().append(operator[i]));
        }

        System.out.println(max + "\n" + min);
    }

    private static void dfs(int node, int depth, StringBuilder sb) {
        if (depth == N - 2) {
            int number = calculate(sb);
            if (number > max) {
                max = number;
            }

            if (number < min) {
                min = number;
            }

            return;
        }

        for (int i = 0; i < N - 1; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sb.append(operator[i]);
                dfs(i, depth + 1, sb);
                visited[i] = false; // 백트래킹
                sb.delete(sb.length() - 1, sb.length());
            }
        }
    }

    private static int calculate(StringBuilder sb) {
        String[] cArr = sb.toString().split("");

        int num = A[0];

        for (int i = 0; i < N - 1; i++) {
            int idx = Integer.parseInt(cArr[i]);
            if (idx == 0) {
                num += A[i + 1];
            } else if (idx == 1) {
                num -= A[i + 1];
            } else if (idx == 2) {
                num *= A[i + 1];
            } else {
                num /= A[i + 1];
            }
        }
        return num;
    }
}

