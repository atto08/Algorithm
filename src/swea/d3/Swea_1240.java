package swea.d3;

import java.io.*;
import java.util.StringTokenizer;
/*
단순 2진 암호코드 - D3
1) Fail
소요 시간: 80분
원인: 암호코드가 아닌 코드에서 암호코드까지 도달할때 무조거 7비트씩 끊어야하는줄앎. idx+=7
해결: 암호코드 0~9에 해당하는 암호코드를 찾으면 그때부터 7비트 * 8번 세는 방법으로 수정

2) 9/10개 통과
소요 시간: +30분
원인: 앞에서부터 접근해서 암호코드를 찾으면 시작하는 걸로 했음 >> 이 방법은 암호코드가 맞으면서 아닌 경우가 발생할 수 잇음
해결: 모든 암호코드는 1로 끝남 >> 뒤에서부터 접근

한줄평 - 못풀고 안풀리는 날이 있을수도 있다. 초조해 하지말자.
 */
public class Swea_1240 {
    static int N, M;
    static String[] numbers = { "0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011",
            "0110111", "0001011" };

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            String code = "";
            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                if (str.contains("1")) {
                    code = str;
                }
            }

            int result = 0;
            int odd = 0, even = 0;
            int num = 8, idx = M;

            while (num > 0) {
                String str = code.substring(idx - 7, idx);
                int index = 10;
                for (int i = 0; i < 10; i++) {
                    if (str.equals(numbers[i])) {
                        index = i;
                        break;
                    }
                }

                if (index < 10) {
                    if (num % 2 != 0) {
                        odd += index;
                    } else {
                        even += index;
                    }
                    num--;
                    idx -= 7;
                } else {
                    idx--;
                }
            }

            int number = odd * 3 + even;
            if (number % 10 == 0) {
                result = odd + even;
            }
            bw.write("#" + test_case + " " + result + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}