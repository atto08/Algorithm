package boj.implementation;
/*
부등호 - 실1
소요 시간: 1시간

구현 문제
구현 방법
1. 백트래킹으로 0~9의 숫자를 N의 길이만큼 붙여서 0~9로 만들 수 있는 N 길이의 조합을 모두 만들기
2. 모든 N의 길이가 되는 경우 끝에서 부등호를 사이사이에 넣어보고 해당 값이 조건 식이 맞다면 최댓 값 최솟 값 확인 후 변경해주기.

직면 문제
1. 메모리를 최소한으로 쓰기 위해 부등호를 char 배열로 저장시키려다가 오래 걸림 -> 쓸 데 없는 시간 낭비
2. 자바에서는 부등호를 이어서 붙였을때 조건식으로 인식하지않고 문자열 자체로 인식함 >> 이는 조건식을 판별할 수 가 없게 됨.
>> 부등호 '<' == 0 / '>' == 1로 저장 후 조건식 구현 후 진행
3. 될 수 있는 최대 최솟 값은 0을 포함한 10자리 수이기 때문에 정수형의 최대값을 벗어남 >> 문제 발생1
>> max, min 값을 Long 타입으로 변경
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2529 {
    static String maxStr, minStr;
    static long max, min;
    static int N;
    static int[] sign;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sign = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            String now = st.nextToken();

            if (now.equals("<")) {
                sign[i] = 0;
            } else {
                sign[i] = 1;
            }
        }

        min = Long.MAX_VALUE;
        minStr = String.valueOf(min);
        maxStr = String.valueOf(0);
        for (int i = 0; i < 10; i++) {
            visited = new boolean[10];
            visited[i] = true;
            StringBuilder sb = new StringBuilder(N + 1);
            sb.append(i);
            dfs(sb, 1);
        }
        System.out.println(maxStr + "\n" + minStr);
    }

    private static void dfs(StringBuilder sb, int depth) {

        if (depth == N + 1) {
            boolean isNot = false;
            for (int i = 0; i < N; i++) {
                boolean now;
                if (sign[i] == 0) {
                    now = sb.charAt(i) < sb.charAt(i + 1);
                } else {
                    now = sb.charAt(i) > sb.charAt(i + 1);
                }

                if (!now) {
                    isNot = true;
                    break;
                }
            }

            if (!isNot) {
                long num = Long.parseLong(sb.toString());

                if (max < num) {
                    max = num;
                    maxStr = sb.toString();
                }

                if (min > num) {
                    min = num;
                    minStr = sb.toString();
                }
            }
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (!visited[i]) {
                sb.append(i);
                visited[i] = true;
                dfs(sb, depth + 1);
                visited[i] = false;
                sb.delete(sb.length() - 1, sb.length());
            }
        }
    }
}
