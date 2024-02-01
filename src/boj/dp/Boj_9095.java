package boj.dp;

import java.io.*;
import java.util.ArrayList;

public class Boj_9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            int result = 0;
            if (N < 4)
                result++;

            ArrayList<ArrayList<Integer>> dp = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                dp.add(new ArrayList<>());
                if (i != 0 && i < 4) {
                    dp.get(0).add(i);
                }
            }

            for (int i = 1; i < N; i++) {
                for (int j = 0; j < dp.get(i - 1).size(); j++) {
                    for (int k = 1; k <= 3; k++) {
                        int num = dp.get(i - 1).get(j) + k;
                        if (num <= N) {
                            dp.get(i).add(num);
                            if (num == N) {
                                result++;
                            }
                        }
                    }
                }
            }

            bw.write(result + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
