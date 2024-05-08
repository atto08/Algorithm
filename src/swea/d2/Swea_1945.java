package swea.d2;

import java.io.*;

/*
소인수분해 - D2
소요 시간: 16분
수학적 사고 구현문제
 */
public class Swea_1945 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] numbers = {2, 3, 5, 7, 11};
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            int[] numCnt = new int[5];
            int idx = 0;
            while (N != 1) {
                if (N % numbers[idx] == 0) {
                    N /= numbers[idx];
                    numCnt[idx]++;
                } else {
                    if (idx == 4) {
                        idx = 0;
                    } else {
                        idx++;
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numCnt.length; i++) {
                sb.append(numCnt[i]).append(" ");
            }

            bw.write("#" + test_case + " " + sb + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
