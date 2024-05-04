package swea.d3;

import java.io.*;
import java.util.StringTokenizer;
/*
진기의 최고급 붕어빵 - D3
소요 시간: 34분
1) Fail (998/1000)
>> 초가 제일 오래걸리는 초를 제외하고 있었음
'<' 를 '<=' 으로 변경
2) Pass
 */
public class Swea_1860 {
    static int N, M, K;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            String result = "Possible";
            int maxSec = 0;

            int[] arrivalSec = new int[11112];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());
                arrivalSec[num]++;
                if (num > maxSec) {
                    maxSec = num;
                }
            }

            int fishBread = 0, sec = 0;
            while (sec <= maxSec) { // 손님들이 다 구매하면

                if (sec != 0 && sec % M == 0) { // M초에 K개의 붕어빵 만듦
                    fishBread += K;
                }

                fishBread -= arrivalSec[sec]; // 붕어빵 차감

                if (fishBread < 0) { // 붕어빵 갯수가 모자라서 기다려야한다면
                    result = "Impossible";
                    break;
                }

                sec++;
            }
            bw.write("#" + test_case + " " + result + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}