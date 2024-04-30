package swea.d2;

import java.io.*;
import java.util.StringTokenizer;
/*
날짜 계산기 - D2
소요 시간: 20분
단순 날짜 계산문제
 */
public class Swea_1948 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] day = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int month1 = Integer.parseInt(st.nextToken());
            int day1 = Integer.parseInt(st.nextToken());
            int month2 = Integer.parseInt(st.nextToken());
            int day2 = Integer.parseInt(st.nextToken());

            int result = 0;
            if (month1 == month2) {
                result += day2 - day1 + 1;
            } else {
                result += day[month1 - 1] - day1 + 1;
                for (int i = month1; i < month2 - 1; i++) {
                    result += day[i];
                }
                result += day2;
            }

            bw.write("#" + test_case + " " + result + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
