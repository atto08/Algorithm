package swea.d2;

import java.io.*;
import java.util.StringTokenizer;
/*
수도 요금 겨쟁 - D2
소요 시간: 12분
 */
public class Swea_1284 {
    static int P, Q, R, S, W;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            P = Integer.parseInt(st.nextToken());
            Q = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            int Ba = 0;
            if (W > R) {
                Ba = W - R;
            }

            int fee = Math.min(P * W, Q + (S * Ba));
            bw.write("#" + test_case + " " + fee + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}