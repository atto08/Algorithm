package swea.d2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/*

패턴 마디의 길이 - D2
소요 시간: 36분
패턴 마디 길이가 10인 경우부터 했을때 오답인 경우가 존재
>> 1인 경우부터 체크로 변경
 */
public class Swea_2007 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            String str = br.readLine();
            int result = 0;

            int depth = 1;
            while (depth <= 10) {
                String str1 = str.substring(0, depth);
                String str2 = str.substring(depth, 2 * depth);

                if (str1.equals(str2)) {
                    result = str1.length();
                    break;
                }

                depth++;
            }

            bw.write("#" + test_case + " " + result + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
