package swea.d3;

import java.io.*;
import java.util.StringTokenizer;
/*
다솔이의 월급상자 - D3
소요 시간: 12분
원인: 문제 이해하는데에 대부분의 시간을 사용함
구현을 시작할때는 이렇게 간단한게 맞나 싶었음
 */
public class Swea_6692 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            StringTokenizer st;
            double avg = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                double P = Double.parseDouble(st.nextToken());
                int money = Integer.parseInt(st.nextToken());

                double number = P * money;
                avg += number;
            }
            bw.write("#" + test_case + " " + avg + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
