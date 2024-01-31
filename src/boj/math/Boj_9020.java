package boj.math;

import java.io.*;

public class Boj_9020 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            boolean[] arr = new boolean[N + 1];

            for (int i = 2; i < arr.length; i++) {
                for (int j = 2; i * j < arr.length; j++) {
                    arr[i * j] = true;
                }
            }

            int a = 0;
            int b = 0;
            int r = N;
            for (int i = 2; i < arr.length; i++) {
                if (!arr[i]) {
                    int num = N - i;

                    if (!arr[num] && num != 1 && i != N) {
                        if (i < num) {
                            // 이 조건 간편하게 하려다가 시간잡아먹고 번거로웠다.
                            if (num - i < r) {
                                a = i;
                                b = num;
                                r = num - i;
                            }
                        } else {
                            if (i - num < r) {
                                a = num;
                                b = i;
                                r = i - num;
                            }
                        }
                    }
                }
            }
            bw.write(a + " " + b + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
