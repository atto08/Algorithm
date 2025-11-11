package boj.implementation;

import java.util.*;
import java.io.*;

/*
스위치 켜고 끄기 - 실4
소요시간 - 47분

[풀이설명]
코드 보이는대로. 딱히 설명할게 없다.

p.s
범위가 벗어난 조건문에 N을 포함시켜서 10~20분정도 날린 듯.
*/
public class Boj_1244 {
    static int N;
    static int[] switches;

    private static void turnOnOrOffLights(int sex, int num) {
        if (sex == 1) {
            for (int i = num; i <= N; i += num) {
                switches[i] = switches[i] > 0 ? 0 : 1;
            }
        } else {
            switches[num] = switches[num] > 0 ? 0 : 1;
            int pm = 1;
            while (true) {
                int nm = num - pm;
                int np = num + pm;
                // 범위를 벗어나면
                if (nm <= 0 || np > N) break;
                // 대칭이면
                if (switches[nm] != switches[np]) break;

                switches[nm] = switches[nm] > 0 ? 0 : 1;
                switches[np] = switches[np] > 0 ? 0 : 1;
                pm++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        switches = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }

        int O = Integer.parseInt(br.readLine()); // 명령 수
        for (int i = 0; i < O; i++) {
            st = new StringTokenizer(br.readLine());
            int sex = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            turnOnOrOffLights(sex, num);
        }

        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (i % 20 != 0) {
                result.append(switches[i]).append(" ");
            } else {
                result.append(switches[i]).append("\n");
            }
        }

        System.out.println(result.toString().trim());
    }
}
