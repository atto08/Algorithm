package boj.math;

import java.io.*;

public class Boj_3896 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean[] primal = new boolean[1299710];
        for (int i = 2; i < primal.length; i++) {
            if (!primal[i]) {
                for (int j = 2; i * j < primal.length; j++) {
                    primal[i * j] = true;
                }
            }
        }

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            if (!primal[N]) {
                bw.write("0\n");
            } else {
                int a = 0;
                int b = 0;

                for (int i = N + 1; i < primal.length; i++) {
                    if (!primal[i]) {
                        b = i;
                        break;
                    }
                }

                for (int i = N - 1; i >= 2; i--) {
                    if (!primal[i]) {
                        a = i;
                        break;
                    }
                }
                bw.write(b - a + "\n");
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
