package boj.math;

import java.io.*;

// 17103번 골드바흐 추측 문제와 동일한 방법으로 시간초과 해결
public class Boj_6588 {
    static boolean[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        arr = new boolean[1000001];
        Eratosthenes();

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }

            int a = 0;
            int b = 0;
            for (int i = 2; i < n; i++) {
                if (!arr[i] && !arr[n - i] && (n - i) % 2 != 0) {
                    a = i;
                    b = n - i;
                    break;
                }
            }

            if (a + b == 0) {
                bw.write("Goldbach's conjecture is wrong.\n");
            } else {
                bw.write(n + " = " + a + " + " + b + "\n");
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }

    public static void Eratosthenes() {
        for (int i = 2; i < arr.length; i++) {
            for (int j = 2; i * j < arr.length; j++) {
                arr[i * j] = true;
            }
        }
    }
}
