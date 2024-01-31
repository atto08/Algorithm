package boj.math;

import java.io.*;
import java.util.StringTokenizer;

public class Boj_1963 {
    static boolean[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        arr = new boolean[10000];
        Eratosthenes();
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int test_case = 0; test_case < T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            // 불가능한 경우 "Impossible" 출력
            // A => B 까지 증가하면서 한자리만 바뀐 소수일때만 count
            int count = 0;
            int a = A;
            for (int i = A; i <= B; i++) {
                if (!arr[i]) {
                    System.out.println("i = " + i);
                }
            }
            bw.write(count + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    public static boolean isCorrect(int a, int b) {
        String str1 = String.valueOf(a);
        String str2 = String.valueOf(b);

        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (str1.charAt(i) == str2.charAt(i)) {
                count++;
            }
        }

        if (count == 3) {
            System.out.println(str1 + " " + str2);
            return true;
        }

        return false;
    }

    public static void Eratosthenes() {
        for (int i = 2; i < arr.length; i++) {
            if (!arr[i]) {
                for (int j = 2; i * j < arr.length; j++) {
                    arr[i * j] = true;
                }
            }
        }
    }
}
