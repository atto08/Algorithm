package boj.bfs;

import java.io.*;
import java.util.*;

/*
NumberFormatException 발생
==> attachOne 메소드 사용시 int의 범위를 벗어나기 때문에 발생
try/catch 문을 사용하여 값이 넘어갈 경우를 캐치

틀렸습니다.
==> int 타입으로 받던 모든 변수의 타입을 long 타입으로 바꿔주고 try/catch 문을 제거
==> 통과
 */
public class Boj_16953 {
    static long A, B, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        count = -1;
        bfs();
        System.out.println(count);
    }

    private static void bfs() {
        Queue<long[]> q = new LinkedList<>();
        q.offer(new long[]{A, 1});

        while (!q.isEmpty()) {
            long[] aDepth = q.poll();

            long num1 = multiple(aDepth[0]);
            long num2 = attachOne(aDepth[0]);

            if (num1 == B || num2 == B) {
                count = aDepth[1] + 1;
                break;
            } else {
                if (num1 < B || num2 < B) {
                    q.offer(new long[]{num1, aDepth[1] + 1});
                    q.offer(new long[]{num2, aDepth[1] + 1});
                }
            }
        }
    }

    private static long multiple(long n) {
        return n * 2;
    }

    private static long attachOne(long n) {
        String str = String.valueOf(n) + '1';
        return Long.parseLong(str);
    }
}

